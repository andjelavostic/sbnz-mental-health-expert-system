package com.ftn.sbnz.service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.assessment.UserAssessmentEvent;
import com.ftn.sbnz.model.decision.FinalDecision;
import com.ftn.sbnz.model.decision.FinalState;
import com.ftn.sbnz.model.decision.Severity;
import com.ftn.sbnz.model.events.BurnoutEmergenceEvent;
import com.ftn.sbnz.model.events.CognitiveDegradationEvent;
import com.ftn.sbnz.model.events.CrisisBuildUpEvent;
import com.ftn.sbnz.model.events.EmotionalVolatilityBurstEvent;
import com.ftn.sbnz.model.events.SocialCollapseEvent;
import com.ftn.sbnz.model.events.StressEscalationEvent;
import com.ftn.sbnz.model.features.CognitiveFeatures;
import com.ftn.sbnz.model.features.EmotionalFeatures;
import com.ftn.sbnz.model.features.EnvironmentalFeatures;
import com.ftn.sbnz.model.features.SleepFeatures;
import com.ftn.sbnz.model.features.SocialFeatures;
import com.ftn.sbnz.model.features.TemporalFeatures;
import com.ftn.sbnz.service.dto.FinalDecisionDTO;
import com.ftn.sbnz.service.entity.AssessmentEntity;
import com.ftn.sbnz.service.entity.FinalDecisionEntity;
import com.ftn.sbnz.service.repo.AssessmentRepository;
import com.ftn.sbnz.service.repo.FinalDecisionRepository;

@Service
public class RuleEngineService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private FeatureCalculatorService featureService;

    @Autowired
    private AssessmentRepository repo;

    @Autowired
    private FinalDecisionRepository finalDecisionRepository;

    public FinalDecision evaluate(UserAssessment input) {
        AssessmentEntity entity = new AssessmentEntity(input);
        entity.setTimestamp(LocalDateTime.now());
        repo.save(entity);

        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        List<AssessmentEntity> history = repo.findByUserIdAndTimestampAfter(
                input.getUserId(), LocalDateTime.now().minusDays(7));

        for (AssessmentEntity oldData : history) {
            kieSession.insert(new UserAssessmentEvent(oldData));
        }

        // 1. FEATURE LAYER (Java)
        EmotionalFeatures ef = featureService.calculateEmotional(input);
        SleepFeatures sf = featureService.calculatePhysical(input);
        CognitiveFeatures cf = featureService.calculateCognitive(input);
        SocialFeatures sof = featureService.calculateSocial(input);
        TemporalFeatures tf = featureService.calculateTemporal(input);
        EnvironmentalFeatures env = featureService.calculateEnvironmental(input);

        // 2. UBACIVANJE U DROOLS
        kieSession.insert(ef);
        kieSession.insert(sf);
        kieSession.insert(cf);
        kieSession.insert(sof);
        kieSession.insert(tf);
        kieSession.insert(env);

        // 3. KORISNICKI INPUTT
        kieSession.insert(input);

        // 5. VRATI FINAL DECISIONS
        kieSession.setGlobal("featureService", featureService);
        int fired = kieSession.fireAllRules();
        System.out.println("RULES FIRED: " + fired);

        List<FinalDecision> results = new ArrayList<>();
        boolean stressEscalationEvent = false;
        boolean burnoutEmergenceEvent = false;
        boolean emotionalVolatilityBurstEvent = false;
        boolean cognitiveDegradationEvent = false;
        boolean socialCollapseEvent = false;
        boolean crisisBuildUpEvent = false;

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof FinalDecision fd) {
                results.add(fd);
            }
            stressEscalationEvent = stressEscalationEvent || obj instanceof StressEscalationEvent;
            burnoutEmergenceEvent = burnoutEmergenceEvent || obj instanceof BurnoutEmergenceEvent;
            emotionalVolatilityBurstEvent = emotionalVolatilityBurstEvent || obj instanceof EmotionalVolatilityBurstEvent;
            cognitiveDegradationEvent = cognitiveDegradationEvent || obj instanceof CognitiveDegradationEvent;
            socialCollapseEvent = socialCollapseEvent || obj instanceof SocialCollapseEvent;
            crisisBuildUpEvent = crisisBuildUpEvent || obj instanceof CrisisBuildUpEvent;
        }

        kieSession.dispose();

        Optional<FinalDecision> bestDecision = results.stream()
                .max(Comparator.comparingDouble(FinalDecision::getScore));

        FinalDecision decision = bestDecision.orElse(null);
        if (decision == null) {
            decision = new FinalDecision(
                    FinalState.LOW_RISK,
                    Severity.LOW,
                    "Nije detektovan značajan rizik",
                    "Rezultati ne ukazuju na zabrinjavajuće obrasce.",
                    new ArrayList<>(),
                    0.1);
        }

        if (isCepDecision(
                decision.getFinalState(),
                stressEscalationEvent,
                burnoutEmergenceEvent,
                emotionalVolatilityBurstEvent,
                cognitiveDegradationEvent,
                socialCollapseEvent,
                crisisBuildUpEvent)) {
            List<String> patterns = new ArrayList<>(decision.getTriggeredPatterns());
            if (!patterns.contains("CEP")) {
                patterns.add("CEP");
            }
            decision.setTriggeredPatterns(patterns);
        }

        FinalDecisionEntity decisionEntity = new FinalDecisionEntity(decision, input.getUserId());

        finalDecisionRepository.save(decisionEntity);
        return decision;
    }

    private boolean isCepDecision(
            FinalState finalState,
            boolean stressEscalationEvent,
            boolean burnoutEmergenceEvent,
            boolean emotionalVolatilityBurstEvent,
            boolean cognitiveDegradationEvent,
            boolean socialCollapseEvent,
            boolean crisisBuildUpEvent) {
        return switch (finalState) {
            case SEVERE_BURNOUT_STATE -> burnoutEmergenceEvent && socialCollapseEvent;
            case EMOTIONAL_NUMBNESS_STATE -> emotionalVolatilityBurstEvent && cognitiveDegradationEvent;
            case MOTIVATIONAL_COLLAPSE -> socialCollapseEvent;
            case SEVERE_DEPRESSIVE_RISK_PATTERN -> socialCollapseEvent && emotionalVolatilityBurstEvent
                    && crisisBuildUpEvent;
            case ANXIETY_ESCALATION_STATE, PANIC_ATTACK_PATTERN -> stressEscalationEvent
                    && emotionalVolatilityBurstEvent;
            case GENERALIZED_ANXIETY_TENDENCY -> stressEscalationEvent;
            case MENTAL_FATIGUE_SYNDROME -> cognitiveDegradationEvent && burnoutEmergenceEvent;
            case CONCENTRATION_DECLINE_PATTERN -> cognitiveDegradationEvent;
            case DECISION_FATIGUE_STATE -> cognitiveDegradationEvent && emotionalVolatilityBurstEvent;
            case SOCIAL_WITHDRAWAL_SYNDROME, SOCIAL_ISOLATION_PROGRESSION -> socialCollapseEvent;
            case INTERPERSONAL_DETACHMENT_PATTERN -> socialCollapseEvent && emotionalVolatilityBurstEvent;
            case SEVERE_PSYCHOLOGICAL_DETERIORATION -> crisisBuildUpEvent && cognitiveDegradationEvent
                    && socialCollapseEvent;
            case EMOTIONAL_COLLAPSE_RISK -> emotionalVolatilityBurstEvent && crisisBuildUpEvent;
            case CRISIS_INTERVENTION_RECOMMENDED -> crisisBuildUpEvent;
            default -> false;
        };
    }

    public List<FinalDecisionDTO> getHistory(Long userId) {

        return finalDecisionRepository
                .findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(FinalDecisionDTO::new)
                .toList();
    }
}
