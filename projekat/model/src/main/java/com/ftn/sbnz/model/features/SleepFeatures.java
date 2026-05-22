package com.ftn.sbnz.model.features;

public class SleepFeatures {
    private double sqi;
    private double fi;
    private double sfi;
    public SleepFeatures(double sqi, double fi, double sfi) {
        this.sqi = sqi;
        this.fi = fi;
        this.sfi = sfi;
    }
    public double getSqi() {
        return sqi;
    }
    public void setSqi(double sqi) {
        this.sqi = sqi;
    }
    public double getFi() {
        return fi;
    }
    public void setFi(double fi) {
        this.fi = fi;
    }
    public double getSfi() {
        return sfi;
    }
    public void setSfi(double sfi) {
        this.sfi = sfi;
    }
    
}
