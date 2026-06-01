package com.ftn.sbnz.service.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.decision.FinalDecision;
import com.ftn.sbnz.model.features.CognitiveFeatures;
import com.ftn.sbnz.model.features.EmotionalFeatures;
import com.ftn.sbnz.model.features.EnvironmentalFeatures;
import com.ftn.sbnz.model.features.SleepFeatures;
import com.ftn.sbnz.model.features.SocialFeatures;
import com.ftn.sbnz.model.features.TemporalFeatures;

@Service
public class RuleEngineService {

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    private FeatureCalculatorService featureService;

    public FinalDecision evaluate(UserAssessment input) {
        KieSession kieSession = kieContainer.newKieSession("rulesSession");

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
        int fired = kieSession.fireAllRules();
        System.out.println("RULES FIRED: " + fired);

        List<FinalDecision> results = new ArrayList<>();

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof FinalDecision fd) {
                results.add(fd);
            }
        }

        kieSession.dispose();
        
        Optional<FinalDecision> bestDecision = results.stream()
            .max(Comparator.comparingDouble(FinalDecision::getScore));

        return bestDecision.orElse(null);
    }
}