package com.ftn.sbnz.model.features;

public class EmotionalFeatures {
    private double esi;
    private double nai;
    private double evi;
    private double lci;
    public EmotionalFeatures(double esi, double nai, double evi, double lci) {
        this.esi = esi;
        this.nai = nai;
        this.evi = evi;
        this.lci = lci;
    }
    public double getEsi() {
        return esi;
    }
    public void setEsi(double esi) {
        this.esi = esi;
    }
    public double getNai() {
        return nai;
    }
    public void setNai(double nai) {
        this.nai = nai;
    }
    public double getEvi() {
        return evi;
    }
    public void setEvi(double evi) {
        this.evi = evi;
    }
    public double getLci() {
        return lci;
    }
    public void setLci(double lci) {
        this.lci = lci;
    }
    
}