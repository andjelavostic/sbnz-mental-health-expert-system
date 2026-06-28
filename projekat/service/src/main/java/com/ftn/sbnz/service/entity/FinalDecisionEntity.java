package com.ftn.sbnz.service.entity;
import java.time.OffsetDateTime;

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

    public FinalDecisionEntity() {
       
    }

    public FinalDecisionEntity(FinalDecision decision, Long userId) {
        this(decision, userId, OffsetDateTime.now());
    }

    public FinalDecisionEntity(FinalDecision decision, Long userId, OffsetDateTime date) {

        this.setFinalState(decision.getFinalState());
        this.setSeverity(decision.getSeverity());
        this.setExplanation(decision.getExplanation());
        this.setRecommendation(decision.getRecommendation());
        this.setTriggeredPatterns(decision.getTriggeredPatterns());
        this.setScore(decision.getScore());
        this.setUserId(userId);
        this.setDate(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  

}
