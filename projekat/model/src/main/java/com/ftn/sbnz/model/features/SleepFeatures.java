package com.ftn.sbnz.model.features;

public class SleepFeatures {
    private double SQI;
    private double FI;
    private double SFI;
    public SleepFeatures() {
    }
    public SleepFeatures(double SQI, double FI, double SFI) {
        this.SQI = SQI;
        this.FI = FI;
        this.SFI = SFI;
    }
    public double getSQI() {
        return SQI;
    }
    public void setSQI(double SQI) {
        this.SQI = SQI;
    }
    public double getFI() {
        return FI;
    }
    public void setFI(double FI) {
        this.FI = FI;
    }
    public double getSFI() {
        return SFI;
    }
    public void setSFI(double SFI) {
        this.SFI = SFI;
    }
    
}
