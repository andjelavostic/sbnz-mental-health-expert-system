package com.ftn.sbnz.model.assessment;
import java.time.LocalDateTime;

public class UserAssessmentEvent {
    private UserAssessment assessment;
    private LocalDateTime timestamp;

    public UserAssessmentEvent(UserAssessment assessment) {
        this.assessment = assessment;
        this.timestamp = assessment.getTimestamp();
    }

    public UserAssessment getAssessment() { return assessment; }
    public LocalDateTime getTimestamp() { return timestamp; }
}