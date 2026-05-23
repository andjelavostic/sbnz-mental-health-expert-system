package com.ftn.sbnz.model.features;

public class EnvironmentalFeatures {
    private double externalStressIndex;
    private double chronicStressExposure;
    
    public EnvironmentalFeatures() {
    }
    public EnvironmentalFeatures(double externalStressIndex, double chronicStressExposure) {
        this.externalStressIndex = externalStressIndex;
        this.chronicStressExposure = chronicStressExposure;
    }
    public double getExternalStressIndex() {
        return externalStressIndex;
    }
    public void setExternalStressIndex(double externalStressIndex) {
        this.externalStressIndex = externalStressIndex;
    }
    public double getChronicStressExposure() {
        return chronicStressExposure;
    }
    public void setChronicStressExposure(double chronicStressExposure) {
        this.chronicStressExposure = chronicStressExposure;
    }
    
}