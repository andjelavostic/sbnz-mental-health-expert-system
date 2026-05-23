package com.ftn.sbnz.model.features;

public class EmotionalFeatures {
    private double ESI;
    private double NAI;
    private double EVI;
    private double LCI;
    
    public EmotionalFeatures() {
    }
    public EmotionalFeatures(double ESI, double NAI, double EVI, double LCI) {
        this.ESI = ESI;
        this.NAI = NAI;
        this.EVI = EVI;
        this.LCI = LCI;
    }
    public double getESI() {
        return ESI;
    }
    public void setESI(double ESI) {
        this.ESI = ESI;
    }
    public double getNAI() {
        return NAI;
    }
    public void setNAI(double NAI) {
        this.NAI = NAI;
    }
    public double getEVI() {
        return EVI;
    }
    public void setEVI(double EVI) {
        this.EVI = EVI;
    }
    public double getLCI() {
        return LCI;
    }
    public void setLCI(double LCI) {
        this.LCI = LCI;
    }
    
}