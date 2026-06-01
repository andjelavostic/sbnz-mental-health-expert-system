package com.ftn.sbnz.model.patterns;

public class CognitivePattern {
    private boolean mentalFatigueSyndrome;
    private boolean concentrationDecline;
    private boolean decisionFatigue;
    private double score;
    public CognitivePattern() {
    }
    public CognitivePattern(boolean mentalFatigueSyndrome, boolean concentrationDecline, boolean decisionFatigue) {
        this.mentalFatigueSyndrome = mentalFatigueSyndrome;
        this.concentrationDecline = concentrationDecline;
        this.decisionFatigue = decisionFatigue;
    }
    public boolean isMentalFatigueSyndrome() {
        return mentalFatigueSyndrome;
    }
    public void setMentalFatigueSyndrome(boolean mentalFatigueSyndrome) {
        this.mentalFatigueSyndrome = mentalFatigueSyndrome;
    }
    public boolean isConcentrationDecline() {
        return concentrationDecline;
    }
    public void setConcentrationDecline(boolean concentrationDecline) {
        this.concentrationDecline = concentrationDecline;
    }
    public boolean isDecisionFatigue() {
        return decisionFatigue;
    }
    public void setDecisionFatigue(boolean decisionFatigue) {
        this.decisionFatigue = decisionFatigue;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}
