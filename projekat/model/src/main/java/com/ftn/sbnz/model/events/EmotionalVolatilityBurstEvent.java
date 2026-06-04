package com.ftn.sbnz.model.events;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class EmotionalVolatilityBurstEvent {
    private Long userId;
    private LocalDateTime timestamp;
    private double score;
    public EmotionalVolatilityBurstEvent(){}

    public EmotionalVolatilityBurstEvent(Long userId, LocalDateTime timestamp, double score) {
        this.userId = userId;
        this.timestamp = timestamp;
        this.score = score;
    }
    public long getTimestampMillis() { return timestamp.toInstant(ZoneOffset.UTC).toEpochMilli(); }
    public Long getUserId() { return userId; }
    public double getScore() { return score; }
}