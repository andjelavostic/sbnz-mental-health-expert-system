package com.ftn.sbnz.model.features;

public class CognitiveFeatures {
    private double cli;
    private double mfs;
    private double dli;
    public CognitiveFeatures(double cli, double mfs, double dli) {
        this.cli = cli;
        this.mfs = mfs;
        this.dli = dli;
    }
    public double getCli() {
        return cli;
    }
    public void setCli(double cli) {
        this.cli = cli;
    }
    public double getMfs() {
        return mfs;
    }
    public void setMfs(double mfs) {
        this.mfs = mfs;
    }
    public double getDli() {
        return dli;
    }
    public void setDli(double dli) {
        this.dli = dli;
    }
    
    
}
