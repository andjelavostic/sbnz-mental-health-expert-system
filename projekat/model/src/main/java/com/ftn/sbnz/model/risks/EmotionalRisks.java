package com.ftn.sbnz.model.risks;

public class EmotionalRisks {
    private boolean emotionalStressRisk;
    private boolean emotionalOverloadRisk;
    private boolean emotionalInstabilityRisk;
    private boolean emotionalCrisisRisk;
    public EmotionalRisks() {
    }
    public EmotionalRisks(boolean emotionalStressRisk, boolean emotionalOverloadRisk, boolean emotionalInstabilityRisk,
            boolean emotionalCrisisRisk) {
        this.emotionalStressRisk = emotionalStressRisk;
        this.emotionalOverloadRisk = emotionalOverloadRisk;
        this.emotionalInstabilityRisk = emotionalInstabilityRisk;
        this.emotionalCrisisRisk = emotionalCrisisRisk;
    }
    public boolean isEmotionalStressRisk() {
        return emotionalStressRisk;
    }
    public void setEmotionalStressRisk(boolean emotionalStressRisk) {
        this.emotionalStressRisk = emotionalStressRisk;
    }
    public boolean isEmotionalOverloadRisk() {
        return emotionalOverloadRisk;
    }
    public void setEmotionalOverloadRisk(boolean emotionalOverloadRisk) {
        this.emotionalOverloadRisk = emotionalOverloadRisk;
    }
    public boolean isEmotionalInstabilityRisk() {
        return emotionalInstabilityRisk;
    }
    public void setEmotionalInstabilityRisk(boolean emotionalInstabilityRisk) {
        this.emotionalInstabilityRisk = emotionalInstabilityRisk;
    }
    public boolean isEmotionalCrisisRisk() {
        return emotionalCrisisRisk;
    }
    public void setEmotionalCrisisRisk(boolean emotionalCrisisRisk) {
        this.emotionalCrisisRisk = emotionalCrisisRisk;
    }
    
    
}
