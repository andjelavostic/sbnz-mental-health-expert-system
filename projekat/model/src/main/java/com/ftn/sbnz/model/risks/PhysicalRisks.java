package com.ftn.sbnz.model.risks;

public class PhysicalRisks {
    private boolean sleepDisturbanceRisk;
    private boolean sleepDeprivationRisk;
    private boolean physicalFatigueRisk;
    private boolean exhaustionRisk;
    public PhysicalRisks() {
    }
    public PhysicalRisks(boolean sleepDisturbanceRisk, boolean sleepDeprivationRisk, boolean physicalFatigueRisk,
            boolean exhaustionRisk) {
        this.sleepDisturbanceRisk = sleepDisturbanceRisk;
        this.sleepDeprivationRisk = sleepDeprivationRisk;
        this.physicalFatigueRisk = physicalFatigueRisk;
        this.exhaustionRisk = exhaustionRisk;
    }
    public boolean isSleepDisturbanceRisk() {
        return sleepDisturbanceRisk;
    }
    public void setSleepDisturbanceRisk(boolean sleepDisturbanceRisk) {
        this.sleepDisturbanceRisk = sleepDisturbanceRisk;
    }
    public boolean isSleepDeprivationRisk() {
        return sleepDeprivationRisk;
    }
    public void setSleepDeprivationRisk(boolean sleepDeprivationRisk) {
        this.sleepDeprivationRisk = sleepDeprivationRisk;
    }
    public boolean isPhysicalFatigueRisk() {
        return physicalFatigueRisk;
    }
    public void setPhysicalFatigueRisk(boolean physicalFatigueRisk) {
        this.physicalFatigueRisk = physicalFatigueRisk;
    }
    public boolean isExhaustionRisk() {
        return exhaustionRisk;
    }
    public void setExhaustionRisk(boolean exhaustionRisk) {
        this.exhaustionRisk = exhaustionRisk;
    }
}
