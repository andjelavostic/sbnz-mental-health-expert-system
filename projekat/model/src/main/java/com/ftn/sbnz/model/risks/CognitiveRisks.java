package com.ftn.sbnz.model.risks;

public class CognitiveRisks {
    private double cognitiveStrainRisk;
    private double cognitiveOverloadRisk;
    private double decisionImpairmentRisk;
    private double mentalFatigueRisk;
    public CognitiveRisks(double cognitiveStrainRisk, double cognitiveOverloadRisk, double decisionImpairmentRisk,
            double mentalFatigueRisk) {
        this.cognitiveStrainRisk = cognitiveStrainRisk;
        this.cognitiveOverloadRisk = cognitiveOverloadRisk;
        this.decisionImpairmentRisk = decisionImpairmentRisk;
        this.mentalFatigueRisk = mentalFatigueRisk;
    }
    public double getCognitiveStrainRisk() {
        return cognitiveStrainRisk;
    }
    public void setCognitiveStrainRisk(double cognitiveStrainRisk) {
        this.cognitiveStrainRisk = cognitiveStrainRisk;
    }
    public double getCognitiveOverloadRisk() {
        return cognitiveOverloadRisk;
    }
    public void setCognitiveOverloadRisk(double cognitiveOverloadRisk) {
        this.cognitiveOverloadRisk = cognitiveOverloadRisk;
    }
    public double getDecisionImpairmentRisk() {
        return decisionImpairmentRisk;
    }
    public void setDecisionImpairmentRisk(double decisionImpairmentRisk) {
        this.decisionImpairmentRisk = decisionImpairmentRisk;
    }
    public double getMentalFatigueRisk() {
        return mentalFatigueRisk;
    }
    public void setMentalFatigueRisk(double mentalFatigueRisk) {
        this.mentalFatigueRisk = mentalFatigueRisk;
    }
    
}