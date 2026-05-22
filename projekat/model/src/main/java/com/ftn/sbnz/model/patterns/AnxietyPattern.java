package com.ftn.sbnz.model.patterns;

public class AnxietyPattern {
    private boolean anxietyRisk;
    private boolean anxietyEscalation;
    private boolean panicAttack;
    private boolean generalizedAnxiety;
    public AnxietyPattern(boolean anxietyRisk, boolean anxietyEscalation, boolean panicAttack,
            boolean generalizedAnxiety) {
        this.anxietyRisk = anxietyRisk;
        this.anxietyEscalation = anxietyEscalation;
        this.panicAttack = panicAttack;
        this.generalizedAnxiety = generalizedAnxiety;
    }
    public boolean isAnxietyRisk() {
        return anxietyRisk;
    }
    public void setAnxietyRisk(boolean anxietyRisk) {
        this.anxietyRisk = anxietyRisk;
    }
    public boolean isAnxietyEscalation() {
        return anxietyEscalation;
    }
    public void setAnxietyEscalation(boolean anxietyEscalation) {
        this.anxietyEscalation = anxietyEscalation;
    }
    public boolean isPanicAttack() {
        return panicAttack;
    }
    public void setPanicAttack(boolean panicAttack) {
        this.panicAttack = panicAttack;
    }
    public boolean isGeneralizedAnxiety() {
        return generalizedAnxiety;
    }
    public void setGeneralizedAnxiety(boolean generalizedAnxiety) {
        this.generalizedAnxiety = generalizedAnxiety;
    }
    
}