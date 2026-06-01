package com.ftn.sbnz.model.risks;

public class TemporalRisks {
    private boolean stressProgressionRisk;
    private boolean deteriorationRisk;
    private boolean escalationRisk;
    private boolean emotionalInstabilityTemporalRisk;
    private double score;
    public TemporalRisks() {
    }
    public TemporalRisks(boolean stressProgressionRisk, boolean deteriorationRisk, boolean escalationRisk, boolean emotionalInstabilityTemporalRisk) {
        this.stressProgressionRisk = stressProgressionRisk;
        this.deteriorationRisk = deteriorationRisk;
        this.escalationRisk = escalationRisk;
        this.emotionalInstabilityTemporalRisk=emotionalInstabilityTemporalRisk;
    }
    public boolean isStressProgressionRisk() {
        return stressProgressionRisk;
    }
    public void setStressProgressionRisk(boolean stressProgressionRisk) {
        this.stressProgressionRisk = stressProgressionRisk;
    }
    public boolean isDeteriorationRisk() {
        return deteriorationRisk;
    }
    public void setDeteriorationRisk(boolean deteriorationRisk) {
        this.deteriorationRisk = deteriorationRisk;
    }
    public boolean isEscalationRisk() {
        return escalationRisk;
    }
    public void setEscalationRisk(boolean escalationRisk) {
        this.escalationRisk = escalationRisk;
    }
    public boolean isEmotionalInstabilityTemporalRisk() {
        return emotionalInstabilityTemporalRisk;
    }
    public void setEmotionalInstabilityTemporalRisk(boolean emotionalInstabilityTemporalRisk) {
        this.emotionalInstabilityTemporalRisk = emotionalInstabilityTemporalRisk;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}