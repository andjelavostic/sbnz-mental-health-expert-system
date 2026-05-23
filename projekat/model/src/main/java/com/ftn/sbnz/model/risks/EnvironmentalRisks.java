package com.ftn.sbnz.model.risks;

public class EnvironmentalRisks {
    private boolean externalPressureRisk;
    private boolean highExternalStressRisk;
    private boolean chronicStressRisk;
    public EnvironmentalRisks() {
    }
    public EnvironmentalRisks(boolean externalPressureRisk, boolean highExternalStressRisk, boolean chronicStressRisk) {
        this.externalPressureRisk = externalPressureRisk;
        this.highExternalStressRisk = highExternalStressRisk;
        this.chronicStressRisk = chronicStressRisk;
    }
    public boolean getExternalPressureRisk() {
        return externalPressureRisk;
    }
    public void setExternalPressureRisk(boolean externalPressureRisk) {
        this.externalPressureRisk = externalPressureRisk;
    }
    public boolean getChronicStressRisk() {
        return chronicStressRisk;
    }
    public void setChronicStressRisk(boolean chronicStressRisk) {
        this.chronicStressRisk = chronicStressRisk;
    }
    public boolean isHighExternalStressRisk() {
        return highExternalStressRisk;
    }
    public void setHighExternalStressRisk(boolean highExternalStressRisk) {
        this.highExternalStressRisk = highExternalStressRisk;
    }
}
