package com.ftn.sbnz.model.risks;

public class EnvironmentalRisks {
    private double externalPressureRisk;
    private double chronicStressRisk;
    public EnvironmentalRisks(double externalPressureRisk, double chronicStressRisk) {
        this.externalPressureRisk = externalPressureRisk;
        this.chronicStressRisk = chronicStressRisk;
    }
    public double getExternalPressureRisk() {
        return externalPressureRisk;
    }
    public void setExternalPressureRisk(double externalPressureRisk) {
        this.externalPressureRisk = externalPressureRisk;
    }
    public double getChronicStressRisk() {
        return chronicStressRisk;
    }
    public void setChronicStressRisk(double chronicStressRisk) {
        this.chronicStressRisk = chronicStressRisk;
    }
    
}
