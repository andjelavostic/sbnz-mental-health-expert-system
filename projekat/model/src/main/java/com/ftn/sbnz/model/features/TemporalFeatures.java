package com.ftn.sbnz.model.features;

public class TemporalFeatures {
    private double sps;
    private double dr;
    private double evl;
    private double tii;
    public TemporalFeatures(double sps, double dr, double evl, double tii) {
        this.sps = sps;
        this.dr = dr;
        this.evl = evl;
        this.tii = tii;
    }
    public double getSps() {
        return sps;
    }
    public void setSps(double sps) {
        this.sps = sps;
    }
    public double getDr() {
        return dr;
    }
    public void setDr(double dr) {
        this.dr = dr;
    }
    public double getEvl() {
        return evl;
    }
    public void setEvl(double evl) {
        this.evl = evl;
    }
    public double getTii() {
        return tii;
    }
    public void setTii(double tii) {
        this.tii = tii;
    }
    
}
