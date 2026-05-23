package com.ftn.sbnz.model.features;

public class FeatureVector {
    private EmotionalFeatures emotional;
    private SleepFeatures sleep;
    private CognitiveFeatures cognitive;
    private SocialFeatures social;
    private EnvironmentalFeatures environmental;
    private TemporalFeatures temporal;
    public FeatureVector() {
    }
    public FeatureVector(EmotionalFeatures emotional, SleepFeatures sleep, CognitiveFeatures cognitive,
            SocialFeatures social, EnvironmentalFeatures environmental, TemporalFeatures temporal) {
        this.emotional = emotional;
        this.sleep = sleep;
        this.cognitive = cognitive;
        this.social = social;
        this.environmental = environmental;
        this.temporal = temporal;
    }
    public EmotionalFeatures getEmotional() {
        return emotional;
    }
    public void setEmotional(EmotionalFeatures emotional) {
        this.emotional = emotional;
    }
    public SleepFeatures getSleep() {
        return sleep;
    }
    public void setSleep(SleepFeatures sleep) {
        this.sleep = sleep;
    }
    public CognitiveFeatures getCognitive() {
        return cognitive;
    }
    public void setCognitive(CognitiveFeatures cognitive) {
        this.cognitive = cognitive;
    }
    public SocialFeatures getSocial() {
        return social;
    }
    public void setSocial(SocialFeatures social) {
        this.social = social;
    }
    public EnvironmentalFeatures getEnvironmental() {
        return environmental;
    }
    public void setEnvironmental(EnvironmentalFeatures environmental) {
        this.environmental = environmental;
    }
    public TemporalFeatures getTemporal() {
        return temporal;
    }
    public void setTemporal(TemporalFeatures temporal) {
        this.temporal = temporal;
    }
    
}
