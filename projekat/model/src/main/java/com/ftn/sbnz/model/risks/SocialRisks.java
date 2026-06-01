package com.ftn.sbnz.model.risks;

public class SocialRisks {
    private boolean socialWithdrawalRisk;
    private boolean socialIsolationRisk;
    private boolean interpersonalDetachmentRisk;
    private boolean behavioralWithdrawalRisk;
    private double score;
    public SocialRisks() {
    }
    public SocialRisks(boolean socialWithdrawalRisk, boolean socialIsolationRisk, boolean interpersonalDetachmentRisk,boolean behavioralWithdrawalRisk) {
        this.socialWithdrawalRisk = socialWithdrawalRisk;
        this.socialIsolationRisk = socialIsolationRisk;
        this.interpersonalDetachmentRisk = interpersonalDetachmentRisk;
        this.behavioralWithdrawalRisk= behavioralWithdrawalRisk;
    }
    public boolean isSocialWithdrawalRisk() {
        return socialWithdrawalRisk;
    }
    public void setSocialWithdrawalRisk(boolean socialWithdrawalRisk) {
        this.socialWithdrawalRisk = socialWithdrawalRisk;
    }
    public boolean isSocialIsolationRisk() {
        return socialIsolationRisk;
    }
    public void setSocialIsolationRisk(boolean socialIsolationRisk) {
        this.socialIsolationRisk = socialIsolationRisk;
    }
    public boolean isInterpersonalDetachmentRisk() {
        return interpersonalDetachmentRisk;
    }
    public void setInterpersonalDetachmentRisk(boolean interpersonalDetachmentRisk) {
        this.interpersonalDetachmentRisk = interpersonalDetachmentRisk;
    }
    public boolean isBehavioralWithdrawalRisk() {
        return behavioralWithdrawalRisk;
    }
    public void setBehavioralWithdrawalRisk(boolean behavioralWithdrawalRisk) {
        this.behavioralWithdrawalRisk = behavioralWithdrawalRisk;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }    
}
