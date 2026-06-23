package com.ftn.sbnz.service.dto;

import java.util.List;

import com.ftn.sbnz.model.decision.FinalState;

public class BackwardCheckDTO {
    private FinalState targetState;
    private boolean confirmed;
    private boolean cepBased;
    private String explanation;
    private List<String> evidence;

    public BackwardCheckDTO() {
    }

    public BackwardCheckDTO(FinalState targetState, boolean confirmed, boolean cepBased, String explanation,
            List<String> evidence) {
        this.targetState = targetState;
        this.confirmed = confirmed;
        this.cepBased = cepBased;
        this.explanation = explanation;
        this.evidence = evidence;
    }

    public FinalState getTargetState() {
        return targetState;
    }

    public void setTargetState(FinalState targetState) {
        this.targetState = targetState;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isCepBased() {
        return cepBased;
    }

    public void setCepBased(boolean cepBased) {
        this.cepBased = cepBased;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<String> getEvidence() {
        return evidence;
    }

    public void setEvidence(List<String> evidence) {
        this.evidence = evidence;
    }
}
