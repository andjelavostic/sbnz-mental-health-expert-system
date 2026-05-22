package com.ftn.sbnz.model.features;

public class TemporalFeatures {
    private double SPS;
    private double DR;
    private double EVL;
    private double TII;
    public TemporalFeatures(double SPS, double DR, double EVL, double TII) {
        this.SPS = SPS;
        this.DR = DR;
        this.EVL = EVL;
        this.TII = TII;
    }
    public double getSPS() {
        return SPS;
    }
    public void setSPS(double SPS) {
        this.SPS = SPS;
    }
    public double getDR() {
        return DR;
    }
    public void setDR(double DR) {
        this.DR = DR;
    }
    public double getEVL() {
        return EVL;
    }
    public void setEVL(double EVL) {
        this.EVL = EVL;
    }
    public double getTII() {
        return TII;
    }
    public void setTII(double TII) {
        this.TII = TII;
    }
    
}
