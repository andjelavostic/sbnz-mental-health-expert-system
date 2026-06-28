package com.ftn.sbnz.model.assessment;
import java.time.OffsetDateTime;

public class UserAssessmentEvent {
    private UserAssessment assessment;
    private OffsetDateTime timestamp;
    public UserAssessmentEvent() {}
    public UserAssessmentEvent(UserAssessment assessment) {
        this.assessment = assessment;
        this.timestamp = assessment.getTimestamp();
    }

    public UserAssessment getAssessment() { return assessment; }
    public OffsetDateTime getTimestamp() { return timestamp; }
    public long getTimestampMillis() 
    {
        return timestamp.toInstant().toEpochMilli();
    }
}
