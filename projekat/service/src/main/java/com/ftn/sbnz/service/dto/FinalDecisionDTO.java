package com.ftn.sbnz.service.dto;
import com.ftn.sbnz.model.decision.FinalState;
import com.ftn.sbnz.model.decision.Severity;
import com.ftn.sbnz.service.entity.FinalDecisionEntity;

import java.time.OffsetDateTime;
import java.util.List;

public class FinalDecisionDTO {

    private FinalState finalState;
    private Severity severity;

    private String explanation;
    private String recommendation;

    private List<String> triggeredPatterns;
    private double score;

    private OffsetDateTime date;

    public FinalDecisionDTO() {}

    public FinalDecisionDTO(FinalDecisionEntity e) {
        this.finalState = e.getFinalState();
        this.severity = e.getSeverity();
        this.explanation = e.getExplanation();
        this.recommendation = e.getRecommendation();
        this.triggeredPatterns = e.getTriggeredPatterns();
        this.score = e.getScore();
        this.date = e.getDate();
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

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date= date;
    }

    
}
