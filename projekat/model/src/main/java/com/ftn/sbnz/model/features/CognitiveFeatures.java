package com.ftn.sbnz.model.features;

public class CognitiveFeatures {
    private double CLI;
    private double MFS;
    private double DLI;
    public CognitiveFeatures(double CLI, double MFS, double DLI) {
        this.CLI = CLI;
        this.MFS = MFS;
        this.DLI = DLI;
    }
    public double getCLI() {
        return CLI;
    }
    public void setCLI(double CLI) {
        this.CLI = CLI;
    }
    public double getMFS() {
        return MFS;
    }
    public void setMFS(double MFS) {
        this.MFS = MFS;
    }
    public double getDLI() {
        return DLI;
    }
    public void setDLI(double DLI) {
        this.DLI = DLI;
    }
    
    
}
