package com.ftn.sbnz.model.patterns;

public class BurnoutPattern {
    private boolean earlyBurnout;
    private boolean burnoutSyndrome;
    private boolean severeBurnoutState;
    private boolean occupationalExhaustion;
    public BurnoutPattern() {
    }
    public BurnoutPattern(boolean earlyBurnout, boolean burnoutSyndrome, boolean severeBurnoutState,
            boolean occupationalExhaustion) {
        this.earlyBurnout = earlyBurnout;
        this.burnoutSyndrome = burnoutSyndrome;
        this.severeBurnoutState = severeBurnoutState;
        this.occupationalExhaustion = occupationalExhaustion;
    }
    public boolean isEarlyBurnout() {
        return earlyBurnout;
    }
    public void setEarlyBurnout(boolean earlyBurnout) {
        this.earlyBurnout = earlyBurnout;
    }
    public boolean isBurnoutSyndrome() {
        return burnoutSyndrome;
    }
    public void setBurnoutSyndrome(boolean burnoutSyndrome) {
        this.burnoutSyndrome = burnoutSyndrome;
    }
    public boolean isSevereBurnoutState() {
        return severeBurnoutState;
    }
    public void setSevereBurnoutState(boolean severeBurnoutState) {
        this.severeBurnoutState = severeBurnoutState;
    }
    public boolean isOccupationalExhaustion() {
        return occupationalExhaustion;
    }
    public void setOccupationalExhaustion(boolean occupationalExhaustion) {
        this.occupationalExhaustion = occupationalExhaustion;
    }
    
}
