package com.ftn.sbnz.model.risks;

public class SocialRisks {
    private double socialWithdrawalRisk;
    private double socialIsolationRisk;
    private double interpersonalDetachmentRisk;
    public SocialRisks(double socialWithdrawalRisk, double socialIsolationRisk, double interpersonalDetachmentRisk) {
        this.socialWithdrawalRisk = socialWithdrawalRisk;
        this.socialIsolationRisk = socialIsolationRisk;
        this.interpersonalDetachmentRisk = interpersonalDetachmentRisk;
    }
    public double getSocialWithdrawalRisk() {
        return socialWithdrawalRisk;
    }
    public void setSocialWithdrawalRisk(double socialWithdrawalRisk) {
        this.socialWithdrawalRisk = socialWithdrawalRisk;
    }
    public double getSocialIsolationRisk() {
        return socialIsolationRisk;
    }
    public void setSocialIsolationRisk(double socialIsolationRisk) {
        this.socialIsolationRisk = socialIsolationRisk;
    }
    public double getInterpersonalDetachmentRisk() {
        return interpersonalDetachmentRisk;
    }
    public void setInterpersonalDetachmentRisk(double interpersonalDetachmentRisk) {
        this.interpersonalDetachmentRisk = interpersonalDetachmentRisk;
    }
    
}
