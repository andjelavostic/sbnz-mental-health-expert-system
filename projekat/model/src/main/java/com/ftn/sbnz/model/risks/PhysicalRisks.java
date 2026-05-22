package com.ftn.sbnz.model.risks;

public class PhysicalRisks {
    private double sleepDisturbanceRisk;
    private double sleepDeprivationRisk;
    private double physicalFatigueRisk;
    private double exhaustionRisk;
    public PhysicalRisks(double sleepDisturbanceRisk, double sleepDeprivationRisk, double physicalFatigueRisk,
            double exhaustionRisk) {
        this.sleepDisturbanceRisk = sleepDisturbanceRisk;
        this.sleepDeprivationRisk = sleepDeprivationRisk;
        this.physicalFatigueRisk = physicalFatigueRisk;
        this.exhaustionRisk = exhaustionRisk;
    }
    public double getSleepDisturbanceRisk() {
        return sleepDisturbanceRisk;
    }
    public void setSleepDisturbanceRisk(double sleepDisturbanceRisk) {
        this.sleepDisturbanceRisk = sleepDisturbanceRisk;
    }
    public double getSleepDeprivationRisk() {
        return sleepDeprivationRisk;
    }
    public void setSleepDeprivationRisk(double sleepDeprivationRisk) {
        this.sleepDeprivationRisk = sleepDeprivationRisk;
    }
    public double getPhysicalFatigueRisk() {
        return physicalFatigueRisk;
    }
    public void setPhysicalFatigueRisk(double physicalFatigueRisk) {
        this.physicalFatigueRisk = physicalFatigueRisk;
    }
    public double getExhaustionRisk() {
        return exhaustionRisk;
    }
    public void setExhaustionRisk(double exhaustionRisk) {
        this.exhaustionRisk = exhaustionRisk;
    }
    
}
