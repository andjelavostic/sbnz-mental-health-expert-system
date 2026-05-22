package com.ftn.sbnz.model.risks;

public class EmotionalRisks {
    private double emotionalStressRisk;
    private double emotionalOverloadRisk;
    private double emotionalInstabilityRisk;
    private double emotionalCrisisRisk;
    public EmotionalRisks(double emotionalStressRisk, double emotionalOverloadRisk, double emotionalInstabilityRisk,
            double emotionalCrisisRisk) {
        this.emotionalStressRisk = emotionalStressRisk;
        this.emotionalOverloadRisk = emotionalOverloadRisk;
        this.emotionalInstabilityRisk = emotionalInstabilityRisk;
        this.emotionalCrisisRisk = emotionalCrisisRisk;
    }
    public double getEmotionalStressRisk() {
        return emotionalStressRisk;
    }
    public void setEmotionalStressRisk(double emotionalStressRisk) {
        this.emotionalStressRisk = emotionalStressRisk;
    }
    public double getEmotionalOverloadRisk() {
        return emotionalOverloadRisk;
    }
    public void setEmotionalOverloadRisk(double emotionalOverloadRisk) {
        this.emotionalOverloadRisk = emotionalOverloadRisk;
    }
    public double getEmotionalInstabilityRisk() {
        return emotionalInstabilityRisk;
    }
    public void setEmotionalInstabilityRisk(double emotionalInstabilityRisk) {
        this.emotionalInstabilityRisk = emotionalInstabilityRisk;
    }
    public double getEmotionalCrisisRisk() {
        return emotionalCrisisRisk;
    }
    public void setEmotionalCrisisRisk(double emotionalCrisisRisk) {
        this.emotionalCrisisRisk = emotionalCrisisRisk;
    }
    
}
