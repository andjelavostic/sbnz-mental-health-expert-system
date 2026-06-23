package com.ftn.sbnz.service.services;

import java.time.Duration;
import java.util.Comparator;
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
                double worry = normalization.likert(a.getWorryFrequency());
                double overload = normalization.yesNo(a.getOverloadFeeling());
                double lossOfControl = normalization.yesNo(a.getLossOfControlFeeling());

                double esi = clamp(
                                0.30 * stress +
                                                0.25 * nervousness +
                                                0.20 * worry +
                                                0.15 * irritability +
                                                0.10 * exhaustion);

                double nai = clamp(
                                0.45 * normalization.likert(a.getSadnessLevel()) +
                                                0.35 * normalization.likert(a.getLowMoodFrequency()) +
                                                0.20 * lossOfControl);

                double lci = clamp(
                                0.45 * overload +
                                                0.35 * lossOfControl +
                                                0.20 * exhaustion);

                double evi = clamp(
                                0.75 * normalization.likert(a.getMoodSwings()) +
                                                0.25 * irritability);
                System.out.println("ESI = " + esi);
                System.out.println("NAI = " + nai);
                System.out.println("LCI = " + lci);
                System.out.println("EVI = " + evi);
                return new EmotionalFeatures(esi, nai, evi, lci);
        }

        public SleepFeatures calculatePhysical(UserAssessment a) {

                double sleepHours = normalization.sleepHours(a.getSleepHours());
                double sleepProblems = normalization.yesNo(a.getSleepProblems());
                double nightAwakenings = normalization.yesNo(a.getNightAwakenings());
                double notRestedAfterSleep = normalization.invert(normalization.yesNo(a.getRestedAfterSleep()));
                double chronicFatigue = normalization.yesNo(a.getChronicFatigue());
                double lowEnergy = normalization.yesNo(a.getLowEnergy());
                double physicalExhaustion = normalization.likert(a.getPhysicalExhaustion());
                double stressHeadaches = normalization.yesNo(a.getStressHeadaches());
                double appetiteChanges = normalization.yesNo(a.getAppetiteChanges());

                double sqi = clamp(
                                0.40 * sleepHours
                                                + 0.25 * sleepProblems
                                                + 0.20 * nightAwakenings
                                                + 0.15 * notRestedAfterSleep);

                double fi = clamp(
                                0.30 * chronicFatigue
                                                + 0.25 * lowEnergy
                                                + 0.25 * physicalExhaustion
                                                + 0.10 * notRestedAfterSleep
                                                + 0.05 * stressHeadaches
                                                + 0.05 * appetiteChanges);

                double sfi = clamp(
                                0.45 * nightAwakenings
                                                + 0.35 * sleepProblems
                                                + 0.20 * sleepHours);
                System.out.println("SQI = " + sqi);
                System.out.println("FI = " + fi);
                System.out.println("SFI = " + sfi);
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
                System.out.println("CLI = " + cli);
                System.out.println("MFS = " + mfs);
                System.out.println("DLI = " + dli);
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
                System.out.println("SWI = " + swi);
                System.out.println("IDI = " + idi);
                System.out.println("SAD = " + sad);
                return new SocialFeatures(swi, idi, sad);
        }

        public EnvironmentalFeatures calculateEnvironmental(UserAssessment a) {

                double workPressure = normalization.likert(a.getWorkPressure());

                double financial = normalization.yesNo(a.getFinancialProblems());

                double relationships = normalization.yesNo(a.getRelationshipIssues());

                double recentStress = normalization.yesNo(a.getRecentStressEvent());

                double lackOfRest = normalization.invert(normalization.likert(a.getLackOfRestTime()));

                double constantPressure = normalization.likert(a.getConstantPressure());

                double rumination = normalization.likert(a.getRuminationOnTasks());

                double esl = clamp(
                                0.25 * workPressure
                                                + 0.20 * financial
                                                + 0.20 * relationships
                                                + 0.20 * recentStress
                                                + 0.15 * lackOfRest);

                double cse = clamp(
                                0.35 * normalization.durationDays(a.getSymptomDuration())
                                                + 0.25 * constantPressure
                                                + 0.20 * rumination
                                                + 0.20 * lackOfRest);
                System.out.println("ESL = " + esl);
                System.out.println("CSE = " + cse);
                return new EnvironmentalFeatures(esl, cse);
        }

        public TemporalFeatures calculateTemporal(UserAssessment a) {

                double symptomDuration = normalization.durationDays(a.getSymptomDuration());

                double isolationDuration = normalization.durationDays(a.getIsolationDuration());

                double exhaustionDuration = normalization.durationDays(a.getExhaustionDuration());

                double panicFrequency = normalization.frequency(a.getPanicFrequency());

                double stressTrend = normalization.likert(a.getStressTrend());

                double moodTrend = normalization.likert(a.getMoodDegradationTrend());

                double productivityTrend = normalization.likert(a.getProductivityDeclineTrend());


                double sps = clamp(
                                0.5 * symptomDuration
                                                + 0.3 * exhaustionDuration
                                                + 0.2 * isolationDuration);


                double dr = clamp(
                                0.7 * moodTrend
                                                + 0.3 * panicFrequency);


                double evl = clamp(
                                0.7 * stressTrend
                                                + 0.3 * panicFrequency);


                double tii = clamp(
                                0.5 * productivityTrend
                                                + 0.3 * exhaustionDuration
                                                + 0.2 * isolationDuration);
                System.out.println("SPS = " + sps);
                System.out.println("DR = " + dr);
                System.out.println("EVL = " + evl);
                System.out.println("TII = " + tii);
                return new TemporalFeatures(sps, dr, evl, tii);
        }

        public boolean isSpike(double currentEvi, List<Double> history) {
                double sum = 0;
                for (double val : history)
                        sum += val;
                double average = sum / history.size();

                // spike je ako je trenutni EVI bar 50% veći od proseka
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

        public double calculateStressEscalationScore(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                List<UserAssessmentEvent> timeline = timelineUpTo(current, events);
                if (timeline.size() < 3) {
                        return -1.0;
                }

                UserAssessmentEvent t0 = timeline.get(timeline.size() - 1);
                UserAssessmentEvent t1 = timeline.get(timeline.size() - 2);
                UserAssessmentEvent t2 = timeline.get(timeline.size() - 3);

                long hoursBetween = Duration.between(t2.getTimestamp(), t0.getTimestamp()).toHours();
                if (hoursBetween < 24) {
                        return -1.0;
                }

                EmotionalFeatures f0 = calculateEmotional(t0.getAssessment());
                EmotionalFeatures f1 = calculateEmotional(t1.getAssessment());
                EmotionalFeatures f2 = calculateEmotional(t2.getAssessment());
                TemporalFeatures temporal = calculateTemporal(t0.getAssessment());

                if (!(f0.getESI() > f1.getESI() && f1.getESI() > f2.getESI() && temporal.getDR() > 0.6)) {
                        return -1.0;
                }

                double trendSlope = f0.getESI() - f2.getESI();
                return clamp((trendSlope * 0.5) + (f0.getESI() * 0.3) + (temporal.getDR() * 0.2));
        }

        public double calculateBurnoutEmergenceScore(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                List<UserAssessmentEvent> timeline = timelineUpTo(current, events);
                if (timeline.size() < 4) {
                        return -1.0;
                }

                UserAssessmentEvent t0 = timeline.get(timeline.size() - 1);
                UserAssessmentEvent t1 = timeline.get(timeline.size() - 2);
                UserAssessmentEvent t2 = timeline.get(timeline.size() - 3);

                SleepFeatures f0 = calculatePhysical(t0.getAssessment());
                SleepFeatures f1 = calculatePhysical(t1.getAssessment());
                SleepFeatures f2 = calculatePhysical(t2.getAssessment());
                CognitiveFeatures c0 = calculateCognitive(t0.getAssessment());
                CognitiveFeatures c1 = calculateCognitive(t1.getAssessment());
                CognitiveFeatures c2 = calculateCognitive(t2.getAssessment());

                boolean burnoutTrend = f0.getFI() > f1.getFI()
                                && f1.getFI() > f2.getFI()
                                && f0.getSQI() < f1.getSQI()
                                && f1.getSQI() < f2.getSQI()
                                && c0.getMFS() > c1.getMFS()
                                && c1.getMFS() > c2.getMFS();
                if (!burnoutTrend) {
                        return -1.0;
                }

                double fiDelta = f0.getFI() - f2.getFI();
                double sqiDelta = f2.getSQI() - f0.getSQI();
                double mfsDelta = c0.getMFS() - c2.getMFS();
                return clamp((fiDelta + sqiDelta + mfsDelta) / 3.0);
        }

        public double calculateEmotionalVolatilityBurstScore(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                List<UserAssessmentEvent> timeline = timelineUpTo(current, events);
                if (timeline.size() < 3) {
                        return -1.0;
                }

                EmotionalFeatures currentFeatures = calculateEmotional(current.getAssessment());
                TemporalFeatures temporal = calculateTemporal(current.getAssessment());
                List<Double> eviValues = timeline.stream()
                                .map(e -> calculateEmotional(e.getAssessment()).getEVI())
                                .toList();

                double average = calculateAverage(eviValues);
                if (average <= 0.0 || currentFeatures.getEVI() <= average * 1.5 || temporal.getTII() <= 0.6) {
                        return -1.0;
                }

                double spikeIntensity = (currentFeatures.getEVI() / average) - 1.0;
                return clamp((spikeIntensity * 0.6) + (temporal.getTII() * 0.4));
        }

        public double calculateCognitiveDegradationScore(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                List<UserAssessmentEvent> timeline = timelineUpTo(current, events);
                if (timeline.size() < 4) {
                        return -1.0;
                }

                UserAssessmentEvent t0 = timeline.get(timeline.size() - 1);
                UserAssessmentEvent t1 = timeline.get(timeline.size() - 2);
                UserAssessmentEvent t2 = timeline.get(timeline.size() - 3);

                CognitiveFeatures c0 = calculateCognitive(t0.getAssessment());
                CognitiveFeatures c1 = calculateCognitive(t1.getAssessment());
                CognitiveFeatures c2 = calculateCognitive(t2.getAssessment());

                boolean degradationTrend = c0.getCLI() > c1.getCLI()
                                && c1.getCLI() > c2.getCLI()
                                && c0.getDLI() > c1.getDLI()
                                && c1.getDLI() > c2.getDLI()
                                && c0.getMFS() > c1.getMFS()
                                && c1.getMFS() > c2.getMFS();
                if (!degradationTrend) {
                        return -1.0;
                }

                double cliDelta = c0.getCLI() - c2.getCLI();
                double dliDelta = c0.getDLI() - c2.getDLI();
                double mfsDelta = c0.getMFS() - c2.getMFS();
                return clamp((cliDelta + dliDelta + mfsDelta) / 3.0);
        }

        public double calculateSocialCollapseScore(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                List<UserAssessmentEvent> timeline = timelineUpTo(current, events);
                if (timeline.size() < 4) {
                        return -1.0;
                }

                UserAssessmentEvent t0 = timeline.get(timeline.size() - 1);
                UserAssessmentEvent t1 = timeline.get(timeline.size() - 2);
                UserAssessmentEvent t2 = timeline.get(timeline.size() - 3);

                SocialFeatures s0 = calculateSocial(t0.getAssessment());
                SocialFeatures s1 = calculateSocial(t1.getAssessment());
                SocialFeatures s2 = calculateSocial(t2.getAssessment());

                boolean collapseTrend = s0.getSWI() > s1.getSWI()
                                && s1.getSWI() > s2.getSWI()
                                && s0.getSAD() > s1.getSAD()
                                && s1.getSAD() > s2.getSAD()
                                && s0.getIDI() > s1.getIDI()
                                && s1.getIDI() > s2.getIDI();
                if (!collapseTrend) {
                        return -1.0;
                }

                double swiDelta = s0.getSWI() - s2.getSWI();
                double sadDelta = s0.getSAD() - s2.getSAD();
                double idiDelta = s0.getIDI() - s2.getIDI();
                return clamp((swiDelta + sadDelta + idiDelta) / 3.0);
        }

        public double calculateCrisisBuildUpScore(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                List<UserAssessmentEvent> timeline = timelineUpTo(current, events);
                if (timeline.size() < 3) {
                        return -1.0;
                }

                double averageEsi = calculateAverageEsi(timeline);
                double averageFi = calculateAverageFi(timeline);
                TemporalFeatures temporal = calculateTemporal(current.getAssessment());

                if (!(averageEsi > 0.6 && averageFi > 0.6 && temporal.getSPS() > 0.6)) {
                        return -1.0;
                }

                return clamp((averageEsi + averageFi + temporal.getSPS()) / 3.0);
        }

        private List<UserAssessmentEvent> timelineUpTo(UserAssessmentEvent current, List<UserAssessmentEvent> events) {
                if (current == null || events == null) {
                        return List.of();
                }
                long currentTimestamp = current.getTimestampMillis();
                boolean currentIsLatest = events.stream()
                                .filter(e -> e != null)
                                .noneMatch(e -> e.getTimestampMillis() > currentTimestamp);
                if (!currentIsLatest) {
                        return List.of();
                }
                return events.stream()
                                .filter(e -> e != null && e.getTimestampMillis() <= currentTimestamp)
                                .sorted(Comparator.comparingLong(UserAssessmentEvent::getTimestampMillis))
                                .toList();
        }
}
