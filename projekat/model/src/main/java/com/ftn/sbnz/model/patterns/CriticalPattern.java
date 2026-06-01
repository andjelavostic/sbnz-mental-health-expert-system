package com.ftn.sbnz.model.patterns;

public class CriticalPattern {
    private boolean severePsychologicalDeterioration;
    private boolean emotionalCollapseRisk;
    private boolean selfHarmRisk;
    private boolean crisisInterventionRecommended;
    private double score;
    public CriticalPattern() {
    }
    public CriticalPattern(boolean severePsychologicalDeterioration, boolean emotionalCollapseRisk,
            boolean selfHarmRisk, boolean crisisInterventionRecommended) {
        this.severePsychologicalDeterioration = severePsychologicalDeterioration;
        this.emotionalCollapseRisk = emotionalCollapseRisk;
        this.selfHarmRisk = selfHarmRisk;
        this.crisisInterventionRecommended = crisisInterventionRecommended;
    }
    public boolean isSeverePsychologicalDeterioration() {
        return severePsychologicalDeterioration;
    }
    public void setSeverePsychologicalDeterioration(boolean severePsychologicalDeterioration) {
        this.severePsychologicalDeterioration = severePsychologicalDeterioration;
    }
    public boolean isEmotionalCollapseRisk() {
        return emotionalCollapseRisk;
    }
    public void setEmotionalCollapseRisk(boolean emotionalCollapseRisk) {
        this.emotionalCollapseRisk = emotionalCollapseRisk;
    }
    public boolean isSelfHarmRisk() {
        return selfHarmRisk;
    }
    public void setSelfHarmRisk(boolean selfHarmRisk) {
        this.selfHarmRisk = selfHarmRisk;
    }
    public boolean isCrisisInterventionRecommended() {
        return crisisInterventionRecommended;
    }
    public void setCrisisInterventionRecommended(boolean crisisInterventionRecommended) {
        this.crisisInterventionRecommended = crisisInterventionRecommended;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}
