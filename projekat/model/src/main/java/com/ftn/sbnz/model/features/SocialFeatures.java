package com.ftn.sbnz.model.features;

public class SocialFeatures {
    private double swi;
    private double idi;
    private double sad;
    public SocialFeatures(double swi, double idi, double sad) {
        this.swi = swi;
        this.idi = idi;
        this.sad = sad;
    }
    public double getSwi() {
        return swi;
    }
    public void setSwi(double swi) {
        this.swi = swi;
    }
    public double getIdi() {
        return idi;
    }
    public void setIdi(double idi) {
        this.idi = idi;
    }
    public double getSad() {
        return sad;
    }
    public void setSad(double sad) {
        this.sad = sad;
    }
    
}
