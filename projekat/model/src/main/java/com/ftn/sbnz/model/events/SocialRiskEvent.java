package com.ftn.sbnz.model.events;
import java.time.LocalDateTime;

public class SocialRiskEvent {
    private double score;
    private LocalDateTime timestamp;
    public SocialRiskEvent() {
    }
    public SocialRiskEvent(double score, LocalDateTime timestamp) {
        this.score = score;
        this.timestamp = timestamp;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}