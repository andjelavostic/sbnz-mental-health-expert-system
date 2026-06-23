package com.ftn.sbnz.service.dto;

import com.ftn.sbnz.model.assessment.UserAssessment;
import com.ftn.sbnz.model.decision.FinalState;

public class BackwardCheckRequest {
    private UserAssessment assessment;
    private FinalState targetState;

    public UserAssessment getAssessment() {
        return assessment;
    }

    public void setAssessment(UserAssessment assessment) {
        this.assessment = assessment;
    }

    public FinalState getTargetState() {
        return targetState;
    }

    public void setTargetState(FinalState targetState) {
        this.targetState = targetState;
    }
}
