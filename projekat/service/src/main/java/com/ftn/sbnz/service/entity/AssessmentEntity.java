package com.ftn.sbnz.service.entity;

import jakarta.persistence.*;

import com.ftn.sbnz.model.assessment.UserAssessment;

@Entity
@Table(name = "assessments")
public class AssessmentEntity extends UserAssessment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public AssessmentEntity(UserAssessment input) {
        // Osnovno
        this.setUserId(input.getUserId());
        this.setTimestamp(input.getTimestamp());

        // Emotional
        this.setStressLevel(input.getStressLevel());
        this.setEmotionalExhaustion(input.getEmotionalExhaustion());
        this.setNervousness(input.getNervousness());
        this.setOverloadFeeling(input.getOverloadFeeling());
        this.setLowMoodFrequency(input.getLowMoodFrequency());
        this.setMoodSwings(input.getMoodSwings());
        this.setSadnessLevel(input.getSadnessLevel());
        this.setLossOfControlFeeling(input.getLossOfControlFeeling());
        this.setIrritability(input.getIrritability());
        this.setWorryFrequency(input.getWorryFrequency());

        // Sleep / physical
        this.setSleepHours(input.getSleepHours());
        this.setSleepProblems(input.getSleepProblems());
        this.setNightAwakenings(input.getNightAwakenings());
        this.setRestedAfterSleep(input.getRestedAfterSleep());
        this.setChronicFatigue(input.getChronicFatigue());
        this.setStressHeadaches(input.getStressHeadaches());
        this.setAppetiteChanges(input.getAppetiteChanges());
        this.setLowEnergy(input.getLowEnergy());
        this.setPhysicalExhaustion(input.getPhysicalExhaustion());

        // Cognitive
        this.setConcentrationProblems(input.getConcentrationProblems());
        this.setForgetfulness(input.getForgetfulness());
        this.setDecisionDifficulty(input.getDecisionDifficulty());
        this.setMentalConfusion(input.getMentalConfusion());
        this.setProductivityDrop(input.getProductivityDrop());
        this.setAttentionSpanIssues(input.getAttentionSpanIssues());
        this.setMentalFatigue(input.getMentalFatigue());

        // Social
        this.setSocialAvoidance(input.getSocialAvoidance());
        this.setCommunicationWithdrawal(input.getCommunicationWithdrawal());
        this.setSocialIsolationFeeling(input.getSocialIsolationFeeling());
        this.setLossOfInterest(input.getLossOfInterest());
        this.setFamilyAvoidance(input.getFamilyAvoidance());
        this.setTimeSpentAlone(input.getTimeSpentAlone());
        this.setEmotionalDistance(input.getEmotionalDistance());

        // Stressors
        this.setWorkPressure(input.getWorkPressure());
        this.setFinancialProblems(input.getFinancialProblems());
        this.setRecentStressEvent(input.getRecentStressEvent());
        this.setRelationshipIssues(input.getRelationshipIssues());
        this.setLackOfRestTime(input.getLackOfRestTime());
        this.setConstantPressure(input.getConstantPressure());
        this.setRuminationOnTasks(input.getRuminationOnTasks());

        // Temporal
        this.setSymptomDuration(input.getSymptomDuration());
        this.setIsolationDuration(input.getIsolationDuration());
        this.setExhaustionDuration(input.getExhaustionDuration());
        this.setPanicFrequency(input.getPanicFrequency());
        this.setStressTrend(input.getStressTrend());
        this.setMoodDegradationTrend(input.getMoodDegradationTrend());
        this.setProductivityDeclineTrend(input.getProductivityDeclineTrend());
    }
    
}