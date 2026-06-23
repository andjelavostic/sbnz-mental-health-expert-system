package com.ftn.sbnz.service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.assessment.UserAssessmentEvent;
import com.ftn.sbnz.model.decision.FinalDecision;
import com.ftn.sbnz.model.decision.FinalState;
import com.ftn.sbnz.model.decision.Severity;
import com.ftn.sbnz.model.evidence.EvidenceLink;
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
import com.ftn.sbnz.service.dto.BackwardCheckDTO;
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
        KieSession kieSession = prepareSession(input, true);

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
                    "Nije detektovan znacajan rizik",
                    "Rezultati ne ukazuju na zabrinjavajuce obrasce.",
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

    public BackwardCheckDTO checkHypothesis(UserAssessment input, FinalState targetState) {
        KieSession kieSession = prepareSession(input, false);
        BackwardHypothesis hypothesis = backwardHypothesis(targetState);

        if (hypothesis == null) {
            kieSession.dispose();
            return new BackwardCheckDTO(
                    targetState,
                    false,
                    false,
                    "Za ovu hipotezu jos nije definisan backward query.",
                    List.of());
        }

        Map<String, List<String>> evidenceGraph = collectEvidenceGraph(kieSession);
        List<String> confirmedEvidence = hypothesis.evidence()
                .stream()
                .filter(evidence -> isRecursivelySupported(evidenceGraph, targetState.name(), evidence, new HashSet<>()))
                .toList();
        boolean confirmed = evidenceGraph.containsKey(targetState.name()) && !confirmedEvidence.isEmpty();
        kieSession.dispose();

        String explanation = confirmed
                ? "Hipoteza je potvrdjena rekurzivnom backward proverom kroz lanac dokaza."
                : "Hipoteza nije potvrdjena jer rekurzivna backward provera nije pronasla potrebne dokaze.";

        return new BackwardCheckDTO(
                targetState,
                confirmed,
                hypothesis.cepBased(),
                explanation,
                confirmedEvidence);
    }

    private Map<String, List<String>> collectEvidenceGraph(KieSession kieSession) {
        Map<String, List<String>> graph = new HashMap<>();
        for (Object obj : kieSession.getObjects(new ClassObjectFilter(EvidenceLink.class))) {
            EvidenceLink link = (EvidenceLink) obj;
            graph.computeIfAbsent(link.getGoal(), key -> new ArrayList<>()).add(link.getEvidence());
        }
        return graph;
    }

    private boolean isRecursivelySupported(
            Map<String, List<String>> graph,
            String goal,
            String evidence,
            Set<String> visited) {
        if (!visited.add(goal)) {
            return false;
        }

        List<String> directEvidence = graph.getOrDefault(goal, List.of());
        if (directEvidence.contains(evidence)) {
            return true;
        }

        for (String next : directEvidence) {
            if (isRecursivelySupported(graph, next, evidence, visited)) {
                return true;
            }
        }
        return false;
    }

    private KieSession prepareSession(UserAssessment input, boolean saveAssessment) {
        if (saveAssessment) {
            AssessmentEntity entity = new AssessmentEntity(input);
            entity.setTimestamp(LocalDateTime.now());
            repo.save(entity);
        }

        KieSession kieSession = kieContainer.newKieSession("rulesSession");

        List<AssessmentEntity> history = repo.findByUserIdAndTimestampAfter(
                input.getUserId(), LocalDateTime.now().minusDays(7));

        for (AssessmentEntity oldData : history) {
            kieSession.insert(new UserAssessmentEvent(oldData));
        }
        if (!saveAssessment) {
            kieSession.insert(new UserAssessmentEvent(input));
        }

        EmotionalFeatures ef = featureService.calculateEmotional(input);
        SleepFeatures sf = featureService.calculatePhysical(input);
        CognitiveFeatures cf = featureService.calculateCognitive(input);
        SocialFeatures sof = featureService.calculateSocial(input);
        TemporalFeatures tf = featureService.calculateTemporal(input);
        EnvironmentalFeatures env = featureService.calculateEnvironmental(input);

        kieSession.insert(ef);
        kieSession.insert(sf);
        kieSession.insert(cf);
        kieSession.insert(sof);
        kieSession.insert(tf);
        kieSession.insert(env);
        kieSession.insert(input);

        kieSession.setGlobal("featureService", featureService);
        int fired = kieSession.fireAllRules();
        System.out.println("RULES FIRED: " + fired);

        return kieSession;
    }

    private BackwardHypothesis backwardHypothesis(FinalState targetState) {
        return switch (targetState) {
            case LOW_RISK -> new BackwardHypothesis(
                    "whyLowRisk",
                    false,
                    List.of("lowEmotionalFeatures", "lowSleepFeatures", "lowCognitiveFeatures",
                            "lowSocialFeatures", "lowEnvironmentalFeatures"));
            case LOW_EMOTIONAL_TENSION -> new BackwardHypothesis(
                    "whyLowEmotionalTension",
                    false,
                    List.of("ESI", "NAI", "EVI", "LCI"));
            case LOW_MOOD_VULNERABILITY -> new BackwardHypothesis(
                    "whyLowMoodVulnerability",
                    false,
                    List.of("NAI", "ESI", "EVI", "LCI"));
            case LOW_SLEEP_STRAIN -> new BackwardHypothesis(
                    "whyLowSleepStrain",
                    false,
                    List.of("SQI", "FI", "SFI"));
            case LOW_FATIGUE -> new BackwardHypothesis(
                    "whyLowFatigue",
                    false,
                    List.of("FI", "SQI", "SFI"));
            case LOW_COGNITIVE_LOAD -> new BackwardHypothesis(
                    "whyLowCognitiveLoad",
                    false,
                    List.of("CLI", "MFS", "DLI"));
            case LOW_SOCIAL_WITHDRAWAL -> new BackwardHypothesis(
                    "whyLowSocialWithdrawal",
                    false,
                    List.of("SWI", "IDI", "SAD"));
            case LOW_EXTERNAL_PRESSURE -> new BackwardHypothesis(
                    "whyLowExternalPressure",
                    false,
                    List.of("externalStressIndex", "chronicStressExposure"));
            case EARLY_BURNOUT_PATTERN -> new BackwardHypothesis(
                    "whyEarlyBurnout",
                    false,
                    List.of("emotionalStressRisk", "sleepDisturbanceRisk", "cognitiveStrainRisk"));
            case BURNOUT_SYNDROME -> new BackwardHypothesis(
                    "whyBurnoutSyndrome",
                    false,
                    List.of("emotionalOverloadRisk", "sleepDeprivationRisk", "cognitiveOverloadRisk"));
            case SEVERE_BURNOUT_STATE -> new BackwardHypothesis(
                    "whySevereBurnoutCep",
                    true,
                    List.of("BurnoutEmergenceEvent", "SocialCollapseEvent", "emotionalInstabilityTemporalRisk"));
            case OCCUPATIONAL_EXHAUSTION_PATTERN -> new BackwardHypothesis(
                    "whyOccupationalExhaustion",
                    false,
                    List.of("highExternalStressRisk", "physicalFatigueRisk", "BURNOUT_SYNDROME"));
            case ANXIETY_RISK_PATTERN -> new BackwardHypothesis(
                    "whyAnxietyRisk",
                    false,
                    List.of("emotionalStressRisk", "cognitiveStrainRisk"));
            case ANXIETY_ESCALATION_STATE -> new BackwardHypothesis(
                    "whyAnxietyEscalationCep",
                    true,
                    List.of("StressEscalationEvent", "EmotionalVolatilityBurstEvent"));
            case PANIC_ATTACK_PATTERN -> new BackwardHypothesis(
                    "whyPanicAttack",
                    false,
                    List.of("emotionalOverloadRisk", "escalationRisk", "sleepDeprivationRisk"));
            case GENERALIZED_ANXIETY_TENDENCY -> new BackwardHypothesis(
                    "whyGeneralizedAnxiety",
                    true,
                    List.of("emotionalStressRisk", "chronicStressRisk", "StressEscalationEvent"));
            case DEPRESSIVE_PATTERN -> new BackwardHypothesis(
                    "whyDepressivePattern",
                    false,
                    List.of("socialIsolationRisk", "emotionalStressRisk"));
            case EMOTIONAL_NUMBNESS_STATE -> new BackwardHypothesis(
                    "whyEmotionalNumbness",
                    true,
                    List.of("emotionalInstabilityRisk", "cognitiveOverloadRisk",
                            "EmotionalVolatilityBurstEvent", "CognitiveDegradationEvent"));
            case MOTIVATIONAL_COLLAPSE -> new BackwardHypothesis(
                    "whyMotivationalCollapse",
                    true,
                    List.of("behavioralWithdrawalRisk", "mentalFatigueRisk", "SocialCollapseEvent"));
            case SEVERE_DEPRESSIVE_RISK_PATTERN -> new BackwardHypothesis(
                    "whySevereDepressiveCep",
                    true,
                    List.of("SocialCollapseEvent", "EmotionalVolatilityBurstEvent", "CrisisBuildUpEvent"));
            case MENTAL_FATIGUE_SYNDROME -> new BackwardHypothesis(
                    "whyMentalFatigue",
                    false,
                    List.of("cognitiveOverloadRisk", "sleepDeprivationRisk", "emotionalStressRisk"));
            case CONCENTRATION_DECLINE_PATTERN -> new BackwardHypothesis(
                    "whyConcentrationDecline",
                    true,
                    List.of("CognitiveDegradationEvent", "decisionImpairmentRisk"));
            case DECISION_FATIGUE_STATE -> new BackwardHypothesis(
                    "whyDecisionFatigue",
                    true,
                    List.of("decisionImpairmentRisk", "cognitiveOverloadRisk", "emotionalInstabilityRisk",
                            "CognitiveDegradationEvent", "EmotionalVolatilityBurstEvent"));
            case SOCIAL_WITHDRAWAL_SYNDROME -> new BackwardHypothesis(
                    "whySocialWithdrawal",
                    false,
                    List.of("socialIsolationRisk", "emotionalStressRisk", "mentalFatigueRisk"));
            case INTERPERSONAL_DETACHMENT_PATTERN -> new BackwardHypothesis(
                    "whyInterpersonalDetachment",
                    true,
                    List.of("interpersonalDetachmentRisk", "emotionalInstabilityRisk",
                            "SocialCollapseEvent", "EmotionalVolatilityBurstEvent"));
            case SOCIAL_ISOLATION_PROGRESSION -> new BackwardHypothesis(
                    "whySocialIsolationProgression",
                    true,
                    List.of("SocialCollapseEvent", "deteriorationRisk"));
            case SEVERE_PSYCHOLOGICAL_DETERIORATION -> new BackwardHypothesis(
                    "whySeverePsychologicalDeterioration",
                    true,
                    List.of("CrisisBuildUpEvent", "CognitiveDegradationEvent", "SocialCollapseEvent"));
            case EMOTIONAL_COLLAPSE_RISK -> new BackwardHypothesis(
                    "whyEmotionalCollapse",
                    true,
                    List.of("emotionalCrisisRisk", "EmotionalVolatilityBurstEvent", "CrisisBuildUpEvent"));
            case SELF_HARM_RISK_PATTERN -> new BackwardHypothesis(
                    "whySelfHarmRisk",
                    true,
                    List.of("SEVERE_DEPRESSIVE_RISK_PATTERN", "SEVERE_PSYCHOLOGICAL_DETERIORATION",
                            "EMOTIONAL_COLLAPSE_RISK"));
            case CRISIS_INTERVENTION_RECOMMENDED -> new BackwardHypothesis(
                    "whyCrisisInterventionCep",
                    true,
                    List.of("exhaustionRisk", "emotionalCrisisRisk", "CrisisBuildUpEvent",
                            "EMOTIONAL_COLLAPSE_RISK", "SEVERE_PSYCHOLOGICAL_DETERIORATION"));
        };
    }

    private record BackwardHypothesis(String queryName, boolean cepBased, List<String> evidence) {
    }

    /*
     * Old evaluation flow continues below through helper methods.
     */
    public FinalDecision evaluateOld(UserAssessment input) {
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
