package com.ftn.sbnz.service;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.features.CognitiveFeatures;
import com.ftn.sbnz.model.features.EmotionalFeatures;
import com.ftn.sbnz.model.features.EnvironmentalFeatures;
import com.ftn.sbnz.model.features.SleepFeatures;
import com.ftn.sbnz.model.features.SocialFeatures;
import com.ftn.sbnz.model.features.TemporalFeatures;

public class FeatureCalculatorService {
    private double clamp(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }
    public EmotionalFeatures calculateEmotional(UserAssessment a) {

        double esi =
                clamp(
                        0.30 * a.getStressLevel()
                    + 0.30 * a.getEmotionalExhaustion()
                    + 0.20 * a.getNervousness()
                    + 0.20 * a.getIrritability()
                );

        double nai =
                clamp(
                        0.35 * a.getSadnessLevel()
                    + 0.35 * a.getLossOfControlFeeling()
                    + 0.30 * a.getLowMoodFrequency()
                );

        double lci =
                clamp(
                        0.5 * a.getOverloadFeeling()
                    + 0.5 * a.getLossOfControlFeeling()
                );

        double evi =
                clamp(a.getMoodSwings()); // ili history

        return new EmotionalFeatures(esi, nai, evi, lci);
    }
    public SleepFeatures calculatePhysical(UserAssessment a) {

        double sqi =
                clamp(
                        0.5 * a.getSleepHours()
                    - 0.3 * a.getSleepProblems()
                    - 0.2 * a.getNightAwakenings()
                    + 0.3 * a.getRestedAfterSleep()
                );

        double fi =
                clamp(
                        0.4 * a.getChronicFatigue()
                    + 0.3 * a.getLowEnergy()
                    + 0.3 * a.getPhysicalExhaustion()
                );

        double sfi =
                clamp(
                        0.5 * a.getNightAwakenings()
                    + 0.5 * a.getSleepProblems()
                );

        return new SleepFeatures(sqi, fi, sfi);
    }
    public CognitiveFeatures calculateCognitive(UserAssessment a) {

        double cli =
                clamp(
                        0.4 * a.getConcentrationProblems()
                    + 0.3 * a.getForgetfulness()
                    + 0.3 * a.getMentalConfusion()
                );

        double mfs =
                clamp(
                        0.4 * a.getMentalFatigue()
                    + 0.3 * a.getProductivityDrop()
                    + 0.3 * a.getAttentionSpanIssues()
                );

        double dli =
                clamp(
                        0.5 * a.getDecisionDifficulty()
                    + 0.5 * a.getMentalConfusion()
                );

        return new CognitiveFeatures(cli, mfs, dli);
    }
    public SocialFeatures calculateSocial(UserAssessment a) {

        double swi =
                clamp(
                        0.4 * a.getSocialAvoidance()
                    + 0.3 * a.getCommunicationWithdrawal()
                    + 0.3 * a.getSocialIsolationFeeling()
                );

        double idi =
                clamp(
                        0.5 * a.getEmotionalDistance()
                    + 0.5 * a.getLossOfInterest()
                );

        double sad =
                clamp(
                        0.5 * a.getTimeSpentAlone()
                    + 0.5 * a.getFamilyAvoidance()
                );

        return new SocialFeatures(swi, idi, sad);
    }
    public EnvironmentalFeatures calculateEnvironmental(UserAssessment a) {

        double esl =
                clamp(
                        0.4 * a.getWorkPressure()
                    + 0.3 * a.getFinancialProblems()
                    + 0.3 * a.getRelationshipIssues()
                );

        double cse =
                clamp(
                        0.6 * a.getSymptomDuration()
                    + 0.4 * a.getConstantPressure()
                );

        return new EnvironmentalFeatures(esl, cse);
    }
    public TemporalFeatures calculateTemporal(UserAssessment a) {

        double sps = clamp(a.getSymptomDuration());

        double dr = clamp(a.getMoodDegradationTrend());

        double evl = clamp(a.getStressTrend());

        double tii = clamp(a.getProductivityDeclineTrend());

        return new TemporalFeatures(sps, dr, evl, tii);
    }
}