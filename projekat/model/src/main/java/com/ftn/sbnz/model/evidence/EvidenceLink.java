package com.ftn.sbnz.model.evidence;

public class EvidenceLink {
    private String goal;
    private String evidence;

    public EvidenceLink() {
    }

    public EvidenceLink(String goal, String evidence) {
        this.goal = goal;
        this.evidence = evidence;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }
}
