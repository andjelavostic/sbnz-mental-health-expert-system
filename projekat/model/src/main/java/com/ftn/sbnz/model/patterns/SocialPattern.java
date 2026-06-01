package com.ftn.sbnz.model.patterns;

public class SocialPattern {
    private boolean socialWithdrawal;
    private boolean interpersonalDetachment;
    private boolean socialIsolationProgression;
    private double score;
    public SocialPattern() {
    }
    public SocialPattern(boolean socialWithdrawal, boolean interpersonalDetachment,
            boolean socialIsolationProgression) {
        this.socialWithdrawal = socialWithdrawal;
        this.interpersonalDetachment = interpersonalDetachment;
        this.socialIsolationProgression = socialIsolationProgression;
    }
    public boolean isSocialWithdrawal() {
        return socialWithdrawal;
    }
    public void setSocialWithdrawal(boolean socialWithdrawal) {
        this.socialWithdrawal = socialWithdrawal;
    }
    public boolean isInterpersonalDetachment() {
        return interpersonalDetachment;
    }
    public void setInterpersonalDetachment(boolean interpersonalDetachment) {
        this.interpersonalDetachment = interpersonalDetachment;
    }
    public boolean isSocialIsolationProgression() {
        return socialIsolationProgression;
    }
    public void setSocialIsolationProgression(boolean socialIsolationProgression) {
        this.socialIsolationProgression = socialIsolationProgression;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
}
