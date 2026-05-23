package com.ftn.sbnz.model.patterns;

public class DepressivePattern {
    private boolean depressivePattern;
    private boolean emotionalNumbnessState;
    private boolean motivationalCollapse;
    private boolean severeDepressiveRisk;
    public DepressivePattern() {
    }
    public DepressivePattern(boolean depressivePattern, boolean emotionalNumbnessState, boolean motivationalCollapse,
            boolean severeDepressiveRisk) {
        this.depressivePattern = depressivePattern;
        this.emotionalNumbnessState = emotionalNumbnessState;
        this.motivationalCollapse = motivationalCollapse;
        this.severeDepressiveRisk = severeDepressiveRisk;
    }
    public boolean isDepressivePattern() {
        return depressivePattern;
    }
    public void setDepressivePattern(boolean depressivePattern) {
        this.depressivePattern = depressivePattern;
    }
    public boolean isEmotionalNumbnessState() {
        return emotionalNumbnessState;
    }
    public void setEmotionalNumbnessState(boolean emotionalNumbnessState) {
        this.emotionalNumbnessState = emotionalNumbnessState;
    }
    public boolean isMotivationalCollapse() {
        return motivationalCollapse;
    }
    public void setMotivationalCollapse(boolean motivationalCollapse) {
        this.motivationalCollapse = motivationalCollapse;
    }
    public boolean isSevereDepressiveRisk() {
        return severeDepressiveRisk;
    }
    public void setSevereDepressiveRisk(boolean severeDepressiveRisk) {
        this.severeDepressiveRisk = severeDepressiveRisk;
    }
    
}