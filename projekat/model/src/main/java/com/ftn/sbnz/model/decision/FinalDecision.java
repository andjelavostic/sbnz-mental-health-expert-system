package com.ftn.sbnz.model.decision;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.MappedSuperclass;
@MappedSuperclass
public class FinalDecision {
    private Long userId; 

    private LocalDateTime date;

    private FinalState finalState;
    private Severity severity;

    private String explanation;
    private String recommendation;

    private List<String> triggeredPatterns;
    private double score;

    public FinalDecision() {
    }

    public FinalDecision(Long id,
            LocalDateTime date,
            FinalState finalState,
            Severity severity,
            String explanation,
            String recommendation,
            List<String> triggeredPatterns,
            double score) {
        this.userId = id;
        this.date = date;
        this.finalState = finalState;
        this.severity = severity;
        this.explanation = explanation;
        this.recommendation = recommendation;
        this.triggeredPatterns = triggeredPatterns;
        this.score = score;
    }
    public FinalDecision(
            FinalState finalState,
            Severity severity,
            String explanation,
            String recommendation,
            List<String> triggeredPatterns,
            double score) {
        this.finalState = finalState;
        this.severity = severity;
        this.explanation = explanation;
        this.recommendation = recommendation;
        this.triggeredPatterns = triggeredPatterns;
        this.score = score;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public FinalState getFinalState() {
        return finalState;
    }

    public void setFinalState(FinalState finalState) {
        this.finalState = finalState;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public List<String> getTriggeredPatterns() {
        return triggeredPatterns;
    }

    public void setTriggeredPatterns(List<String> triggeredPatterns) {
        this.triggeredPatterns = triggeredPatterns;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
