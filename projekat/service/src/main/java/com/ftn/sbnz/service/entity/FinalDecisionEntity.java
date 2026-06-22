package com.ftn.sbnz.service.entity;

import java.time.LocalDateTime;

import com.ftn.sbnz.model.decision.FinalDecision;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "final_decisions")
public class FinalDecisionEntity extends FinalDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDateTime timestamp;

    public FinalDecisionEntity() {
       
    }

    public FinalDecisionEntity(FinalDecision decision, Long userId) {

        this.setFinalState(decision.getFinalState());
        this.setSeverity(decision.getSeverity());
        this.setExplanation(decision.getExplanation());
        this.setRecommendation(decision.getRecommendation());
        this.setTriggeredPatterns(decision.getTriggeredPatterns());
        this.setScore(decision.getScore());

        this.userId = userId;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}