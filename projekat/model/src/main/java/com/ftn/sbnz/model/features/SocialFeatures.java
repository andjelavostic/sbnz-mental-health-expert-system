package com.ftn.sbnz.model.features;

public class SocialFeatures {
    private double SWI;
    private double IDI;
    private double SAD;
    public SocialFeatures() {
    }
    public SocialFeatures(double SWI, double IDI, double SAD) {
        this.SWI = SWI;
        this.IDI = IDI;
        this.SAD = SAD;
    }
    public double getSWI() {
        return SWI;
    }
    public void setSWI(double SWI) {
        this.SWI = SWI;
    }
    public double getIDI() {
        return IDI;
    }
    public void setIDI(double IDI) {
        this.IDI = IDI;
    }
    public double getSAD() {
        return SAD;
    }
    public void setSAD(double SAD) {
        this.SAD = SAD;
    }
    
}
