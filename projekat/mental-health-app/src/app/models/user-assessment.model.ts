export interface UserAssessment {
  userId: number;
  timestamp: Date;

  emotionalState: {
    stressLevel: number;
    emotionalExhaustion: number;
    nervousness: number;
    overloadFeeling: number;
    lowMoodFrequency: number;
    moodSwings: number;
    sadnessLevel: number;
    lossOfControlFeeling: number;
    irritability: number;
    worryFrequency: number;
  };

  sleepPhysical: {
    sleepHours: number;
    sleepProblems: number;
    nightAwakenings: number;
    restedAfterSleep: number;
    chronicFatigue: number;
    stressHeadaches: number;
    appetiteChanges: number;
    lowEnergy: number;
    physicalExhaustion: number;
  };

  cognitive: {
    concentrationProblems: number;
    forgetfulness: number;
    decisionDifficulty: number;
    mentalConfusion: number;
    productivityDrop: number;
    attentionSpanIssues: number;
    mentalFatigue: number;
  };

  social: {
    socialAvoidance: number;
    communicationWithdrawal: number;
    socialIsolationFeeling: number;
    lossOfInterest: number;
    familyAvoidance: number;
    timeSpentAlone: number;
    emotionalDistance: number;
  };

  stressors: {
    workPressure: number;
    financialProblems: number;
    recentStressEvent: number;
    relationshipIssues: number;
    lackOfRestTime: number;
    constantPressure: number;
    ruminationOnTasks: number;
  };

  temporal: {
    symptomDuration: number;
    isolationDuration: number;
    exhaustionDuration: number;
    panicFrequency: number;
    stressTrend: number;
    moodDegradationTrend: number;
    productivityDeclineTrend: number;
  };
}