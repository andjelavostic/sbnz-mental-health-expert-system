package com.ftn.sbnz.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.assessment.UserAssessmentEvent;
import com.ftn.sbnz.model.features.CognitiveFeatures;
import com.ftn.sbnz.model.features.EmotionalFeatures;
import com.ftn.sbnz.model.features.EnvironmentalFeatures;
import com.ftn.sbnz.model.features.SleepFeatures;
import com.ftn.sbnz.model.features.SocialFeatures;
import com.ftn.sbnz.model.features.TemporalFeatures;

@Service
public class FeatureCalculatorService {
    @Autowired
    private NormalizationService normalization;

    private double clamp(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }

    public EmotionalFeatures calculateEmotional(UserAssessment a) {

        double stress = normalization.likert(a.getStressLevel());
        double exhaustion = normalization.yesNo(a.getEmotionalExhaustion());
        double nervousness = normalization.likert(a.getNervousness());
        double irritability = normalization.yesNo(a.getIrritability());

        double esi = clamp(
                0.30 * stress +
                        0.30 * exhaustion +
                        0.20 * nervousness +
                        0.20 * irritability);

        double nai = clamp(
                0.35 * normalization.likert(a.getSadnessLevel()) +
                        0.35 * normalization.yesNo(a.getLossOfControlFeeling()) +
                        0.30 * normalization.likert(a.getLowMoodFrequency()));

        double lci = clamp(
                0.5 * normalization.yesNo(a.getOverloadFeeling()) +
                        0.5 * normalization.yesNo(a.getLossOfControlFeeling()));

        double evi = clamp(normalization.likert(a.getMoodSwings()));

        return new EmotionalFeatures(esi, nai, evi, lci);
    }

    public SleepFeatures calculatePhysical(UserAssessment a) {

        double sleepHours = normalization.likert(a.getSleepHours());
        double sleepProblems = normalization.yesNo(a.getSleepProblems());
        double nightAwakenings = normalization.yesNo(a.getNightAwakenings());
        double restedAfterSleep = normalization.yesNo(a.getRestedAfterSleep());
        double chronicFatigue = normalization.yesNo(a.getChronicFatigue());
        double lowEnergy = normalization.yesNo(a.getLowEnergy());
        double physicalExhaustion = normalization.yesNo(a.getPhysicalExhaustion());

        double sqi = clamp(
                0.5 * sleepHours
                        - 0.3 * sleepProblems
                        - 0.2 * nightAwakenings
                        + 0.3 * restedAfterSleep);

        double fi = clamp(
                0.4 * chronicFatigue
                        + 0.3 * lowEnergy
                        + 0.3 * physicalExhaustion);

        double sfi = clamp(
                0.5 * nightAwakenings
                        + 0.5 * sleepProblems);

        return new SleepFeatures(sqi, fi, sfi);
    }

    public CognitiveFeatures calculateCognitive(UserAssessment a) {

        double concentration = normalization.likert(a.getConcentrationProblems());
        double forgetfulness = normalization.likert(a.getForgetfulness());
        double mentalConfusion = normalization.likert(a.getMentalConfusion());
        double productivityDrop = normalization.likert(a.getProductivityDrop());
        double attentionIssues = normalization.likert(a.getAttentionSpanIssues());
        double mentalFatigue = normalization.likert(a.getMentalFatigue());
        double decisionDifficulty = normalization.likert(a.getDecisionDifficulty());

        double cli = clamp(
                0.4 * concentration
                        + 0.3 * forgetfulness
                        + 0.3 * mentalConfusion);

        double mfs = clamp(
                0.4 * mentalFatigue
                        + 0.3 * productivityDrop
                        + 0.3 * attentionIssues);

        double dli = clamp(
                0.5 * decisionDifficulty
                        + 0.5 * mentalConfusion);

        return new CognitiveFeatures(cli, mfs, dli);
    }

    public SocialFeatures calculateSocial(UserAssessment a) {

        double socialAvoidance = normalization.likert(a.getSocialAvoidance());
        double withdrawal = normalization.likert(a.getCommunicationWithdrawal());
        double isolation = normalization.likert(a.getSocialIsolationFeeling());
        double lossOfInterest = normalization.likert(a.getLossOfInterest());
        double familyAvoidance = normalization.likert(a.getFamilyAvoidance());
        double timeAlone = normalization.likert(a.getTimeSpentAlone());
        double emotionalDistance = normalization.likert(a.getEmotionalDistance());

        double swi = clamp(
                0.4 * socialAvoidance
                        + 0.3 * withdrawal
                        + 0.3 * isolation);

        double idi = clamp(
                0.5 * emotionalDistance
                        + 0.5 * lossOfInterest);

        double sad = clamp(
                0.5 * timeAlone
                        + 0.5 * familyAvoidance);

        return new SocialFeatures(swi, idi, sad);
    }

    public EnvironmentalFeatures calculateEnvironmental(UserAssessment a) {

        double workPressure = normalization.likert(a.getWorkPressure());
        double financial = normalization.likert(a.getFinancialProblems());
        double relationships = normalization.likert(a.getRelationshipIssues());
        double constantPressure = normalization.likert(a.getConstantPressure());

        double esl = clamp(
                0.4 * workPressure
                        + 0.3 * financial
                        + 0.3 * relationships);

        double cse = clamp(
                0.6 * normalization.likert(a.getSymptomDuration())
                        + 0.4 * constantPressure);

        return new EnvironmentalFeatures(esl, cse);
    }

   public TemporalFeatures calculateTemporal(UserAssessment a) {

    double sps = clamp(normalization.frequency(a.getSymptomDuration()));
    double dr = clamp(normalization.likert(a.getMoodDegradationTrend()));
    double evl = clamp(normalization.likert(a.getStressTrend()));
    double tii = clamp(normalization.likert(a.getProductivityDeclineTrend()));

    return new TemporalFeatures(sps, dr, evl, tii);
}

    public boolean isSpike(double currentEvi, List<Double> history) {
        double sum = 0;
        for (double val : history)
            sum += val;
        double average = sum / history.size();

        // "Spike" je ako je trenutni EVI bar 50% veći od proseka
        return currentEvi > (average * 1.5);
    }

    public double calculateAverage(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        return values.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public double calculateAverageEsi(List<UserAssessmentEvent> events) {
        return events.stream()
                .mapToDouble(e -> calculateEmotional(e.getAssessment()).getESI())
                .average().orElse(0.0);
    }

    public double calculateAverageFi(List<UserAssessmentEvent> events) {
        return events.stream()
                .mapToDouble(e -> calculatePhysical(e.getAssessment()).getFI())
                .average().orElse(0.0);
    }
}