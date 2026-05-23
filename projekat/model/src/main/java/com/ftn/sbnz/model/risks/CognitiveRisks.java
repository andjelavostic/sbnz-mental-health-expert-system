package com.ftn.sbnz.model.risks;

public class CognitiveRisks {
    private boolean cognitiveStrainRisk;
    private boolean cognitiveOverloadRisk;
    private boolean decisionImpairmentRisk;
    private boolean mentalFatigueRisk;
    public CognitiveRisks() {
    }
    public CognitiveRisks(boolean cognitiveStrainRisk, boolean cognitiveOverloadRisk, boolean decisionImpairmentRisk,
            boolean mentalFatigueRisk) {
        this.cognitiveStrainRisk = cognitiveStrainRisk;
        this.cognitiveOverloadRisk = cognitiveOverloadRisk;
        this.decisionImpairmentRisk = decisionImpairmentRisk;
        this.mentalFatigueRisk = mentalFatigueRisk;
    }
    public boolean isCognitiveStrainRisk() {
        return cognitiveStrainRisk;
    }
    public void setCognitiveStrainRisk(boolean cognitiveStrainRisk) {
        this.cognitiveStrainRisk = cognitiveStrainRisk;
    }
    public boolean isCognitiveOverloadRisk() {
        return cognitiveOverloadRisk;
    }
    public void setCognitiveOverloadRisk(boolean cognitiveOverloadRisk) {
        this.cognitiveOverloadRisk = cognitiveOverloadRisk;
    }
    public boolean isDecisionImpairmentRisk() {
        return decisionImpairmentRisk;
    }
    public void setDecisionImpairmentRisk(boolean decisionImpairmentRisk) {
        this.decisionImpairmentRisk = decisionImpairmentRisk;
    }
    public boolean isMentalFatigueRisk() {
        return mentalFatigueRisk;
    }
    public void setMentalFatigueRisk(boolean mentalFatigueRisk) {
        this.mentalFatigueRisk = mentalFatigueRisk;
    }
    
}