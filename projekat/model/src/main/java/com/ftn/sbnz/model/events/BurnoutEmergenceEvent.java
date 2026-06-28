package com.ftn.sbnz.model.events;
import java.time.OffsetDateTime;

public class BurnoutEmergenceEvent {
    private Long userId;
    private OffsetDateTime timestamp;
    private double score;
    public BurnoutEmergenceEvent(){}
    public BurnoutEmergenceEvent(Long userId, OffsetDateTime timestamp, double score) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.score = score;
    }
    public long getTimestampMillis() { return timestamp.toInstant().toEpochMilli(); }
    public Long getUserId() { return userId; }
    public double getScore() { return score; }
}
