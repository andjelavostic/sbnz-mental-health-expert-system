package com.ftn.sbnz.model.risks;

public class TemporalRisks {
    private double stressProgressionRisk;
    private double deteriorationRisk;
    private double escalationRisk;
    public TemporalRisks(double stressProgressionRisk, double deteriorationRisk, double escalationRisk) {
        this.stressProgressionRisk = stressProgressionRisk;
        this.deteriorationRisk = deteriorationRisk;
        this.escalationRisk = escalationRisk;
    }
    public double getStressProgressionRisk() {
        return stressProgressionRisk;
    }
    public void setStressProgressionRisk(double stressProgressionRisk) {
        this.stressProgressionRisk = stressProgressionRisk;
    }
    public double getDeteriorationRisk() {
        return deteriorationRisk;
    }
    public void setDeteriorationRisk(double deteriorationRisk) {
        this.deteriorationRisk = deteriorationRisk;
    }
    public double getEscalationRisk() {
        return escalationRisk;
    }
    public void setEscalationRisk(double escalationRisk) {
        this.escalationRisk = escalationRisk;
    }
    
}