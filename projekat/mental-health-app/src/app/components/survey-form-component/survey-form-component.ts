import { Component } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { UserAssessment } from '../../models/user-assessment.model';
import { FinalDecision } from '../../models/final-decision.model';
import { RouterModule } from '@angular/router';
import { AssessmentService } from '../../services/assesment-service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { ChangeDetectorRef, NgZone } from '@angular/core';
import { BackwardCheckResult } from '../../models/final-decision.model';
type NumberRule = {
  min: number;
  max: number;
  message: string;
};
@Component({
  selector: 'app-survey-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatStepperModule,
    MatButtonModule,
    MatRadioModule,
    MatCardModule,
    MatExpansionModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressSpinnerModule,
    MatSelectModule,
  ],
  templateUrl: './survey-form-component.html',
  styleUrls: ['./survey-form-component.css'],
})
export class SurveyFormComponent {
  constructor(
    private assessmentService: AssessmentService,
    private cdr: ChangeDetectorRef,
    private zone: NgZone,
  ) {}
  numberRules: Record<string, NumberRule> = {
    hours: { min: 0, max: 24, message: '0–24 sati' },
    days: { min: 0, max: 365, message: '0–365 dana' },
    weekly: { min: 0, max: 7, message: '0–7 puta nedeljno' },
  };
  emotionalQuestions = [
    {
      field: 'stressLevel',
      text: 'Koliko često osećate stres tokom dana?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'emotionalExhaustion',
      text: 'Da li imate osećaj emocionalne iscrpljenosti?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'nervousness',
      text: 'Koliko često osećate nervozu ili unutrašnji nemir?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'overloadFeeling',
      text: 'Da li imate osećaj preopterećenosti obavezama?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'lowMoodFrequency',
      text: 'Koliko često se osećate bezvoljno?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'moodSwings',
      text: 'Da li primećujete česte promene raspoloženja?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'sadnessLevel',
      text: 'Koliko često osećate tugu ili prazninu?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'lossOfControlFeeling',
      text: 'Da li imate osećaj gubitka kontrole nad svakodnevnim obavezama?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'irritability',
      text: 'Da li se lako iznervirate ili postanete razdražljivi?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'worryFrequency',
      text: 'Koliko često preterano brinete o svakodnevnim stvarima?',
      type: 'scale',
      answer: null,
    },
  ];
  sleepPhysicalQuestions = [
    {
      field: 'sleepHours',
      text: 'Koliko sati prosečno spavate?',
      type: 'hours',
      answer: null,
    },

    {
      field: 'sleepProblems',
      text: 'Da li imate problema sa uspavljivanjem?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'nightAwakenings',
      text: 'Da li se budite tokom noći?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'restedAfterSleep',
      text: 'Da li se osećate odmorno nakon spavanja?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'chronicFatigue',
      text: 'Da li osećate hronični umor tokom dana?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'stressHeadaches',
      text: 'Da li imate glavobolje povezane sa stresom?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'appetiteChanges',
      text: 'Da li primećujete promene apetita?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'lowEnergy',
      text: 'Da li osećate nedostatak energije za svakodnevne aktivnosti?',
      type: 'boolean',
      answer: null,
    },

    {
      field: 'physicalExhaustion',
      text: 'Koliko često osećate fizičku iscrpljenost?',
      type: 'scale',
      answer: null,
    },
  ];
  cognitiveQuestions = [
    {
      field: 'concentrationProblems',
      text: 'Da li imate problema sa koncentracijom?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'forgetfulness',
      text: 'Da li često zaboravljate obaveze ili informacije?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'decisionDifficulty',
      text: 'Da li vam je teško da donesete odluke?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'mentalConfusion',
      text: 'Da li imate osećaj mentalne konfuzije?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'productivityDrop',
      text: 'Da li primećujete pad produktivnosti?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'attentionSpanIssues',
      text: 'Da li vam je teško da zadržite pažnju tokom rada ili učenja?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'mentalFatigue',
      text: 'Da li imate osećaj mentalne iscrpljenosti nakon jednostavnih zadataka?',
      type: 'scale',
      answer: null,
    },
  ];
  socialQuestions = [
    {
      field: 'socialAvoidance',
      text: 'Da li izbegavate društvene aktivnosti?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'communicationWithdrawal',
      text: 'Koliko često se povlačite iz komunikacije sa drugim ljudima?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'socialIsolationFeeling',
      text: 'Da li imate osećaj socijalne izolacije?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'lossOfInterest',
      text: 'Da li ste izgubili interesovanje za aktivnosti koje ste ranije voleli?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'familyAvoidance',
      text: 'Da li izbegavate kontakte sa porodicom ili prijateljima?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'timeSpentAlone',
      text: 'Da li provodite značajno više vremena sami?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'emotionalDistance',
      text: 'Da li imate osećaj emocionalne udaljenosti od drugih ljudi?',
      type: 'scale',
      answer: null,
    },
  ];
  stressorsQuestions = [
    {
      field: 'workPressure',
      text: 'Da li ste izloženi velikom radnom ili akademskom pritisku?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'financialProblems',
      text: 'Da li imate finansijske probleme?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'recentStressEvent',
      text: 'Da li ste doživeli značajan stresni događaj u poslednjih 6 meseci?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'relationshipIssues',
      text: 'Da li imate probleme u partnerskim ili porodičnim odnosima?',
      type: 'boolean',
      answer: null,
    },
    {
      field: 'lackOfRestTime',
      text: 'Da li imate dovoljno vremena za odmor i privatni život?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'constantPressure',
      text: 'Da li imate osećaj konstantnog pritiska i nedostatka vremena?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'ruminationOnTasks',
      text: 'Da li često razmišljate o obavezama čak i tokom odmora?',
      type: 'scale',
      answer: null,
    },
  ];
  temporalQuestions = [
    {
      field: 'symptomDuration',
      text: 'Koliko dugo traju problemi sa snom (u danima)?',
      type: 'days',
      answer: null,
    },
    {
      field: 'isolationDuration',
      text: 'Koliko dugo traje socijalna izolacija (u danima)?',
      type: 'days',
      answer: null,
    },
    {
      field: 'exhaustionDuration',
      text: 'Koliko dugo traje emocionalna iscrpljenost (u danima)?',
      type: 'days',
      answer: null,
    },
    {
      field: 'panicFrequency',
      text: 'Koliko često imate panične epizode (nedeljno)?',
      type: 'weekly',
      answer: null,
    },
    {
      field: 'stressTrend',
      text: 'Koliko često primećujete da nivo stresa raste kroz vreme?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'moodDegradationTrend',
      text: 'Koliko često primećujete pogoršanje raspoloženja kroz vreme?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'productivityDeclineTrend',
      text: 'Koliko često primećujete pad produktivnosti kroz vreme?',
      type: 'scale',
      answer: null,
    },
  ];
  onNumberChange(q: any) {
    const rule = this.numberRules[q.type];
    if (!rule || q.answer == null) return;

    if (q.answer > rule.max) {
      q.answer = rule.max;
      alert(rule.message);
    }

    if (q.answer < rule.min) {
      q.answer = rule.min;
      alert(rule.message);
    }
  }
  isFormValid(): boolean {
    return [
      ...this.emotionalQuestions,
      ...this.sleepPhysicalQuestions,
      ...this.cognitiveQuestions,
      ...this.socialQuestions,
      ...this.stressorsQuestions,
      ...this.temporalQuestions,
    ].every((q) => q.answer !== null);
  }
  mapSection(questions: any[]) {
    const result: any = {};

    questions.forEach((q) => {
      result[q.field] = Number(q.answer);
    });

    return result;
  }
  view: 'form' | 'loading' | 'result' | 'backwardResult' = 'form';
  finalDecision: FinalDecision | null = null;
  backwardResult: BackwardCheckResult | null = null;
  selectedTargetState = 'ANXIETY_RISK_PATTERN';
  loading = false;
  hypothesisOptions = [
    { value: 'LOW_EMOTIONAL_TENSION', label: 'Blag emocionalni pritisak' },
    { value: 'LOW_MOOD_VULNERABILITY', label: 'Blago snizeno raspolozenje' },
    { value: 'LOW_SLEEP_STRAIN', label: 'Blago odstupanje sna' },
    { value: 'LOW_FATIGUE', label: 'Blag umor' },
    { value: 'LOW_COGNITIVE_LOAD', label: 'Blago kognitivno opterecenje' },
    { value: 'LOW_SOCIAL_WITHDRAWAL', label: 'Blago socijalno povlacenje' },
    { value: 'LOW_EXTERNAL_PRESSURE', label: 'Blag spoljasnji pritisak' },
    { value: 'EARLY_BURNOUT_PATTERN', label: 'Rani burnout obrazac' },
    { value: 'BURNOUT_SYNDROME', label: 'Burnout sindrom' },
    { value: 'SEVERE_BURNOUT_STATE', label: 'Tezak burnout' },
    { value: 'OCCUPATIONAL_EXHAUSTION_PATTERN', label: 'Radna/akademska iscrpljenost' },
    { value: 'ANXIETY_RISK_PATTERN', label: 'Rizik anksioznosti' },
    { value: 'ANXIETY_ESCALATION_STATE', label: 'Eskalacija anksioznosti' },
    { value: 'PANIC_ATTACK_PATTERN', label: 'Panicni obrazac' },
    { value: 'GENERALIZED_ANXIETY_TENDENCY', label: 'Generalizovana anksiozna tendencija' },
    { value: 'DEPRESSIVE_PATTERN', label: 'Depresivni obrazac' },
    { value: 'EMOTIONAL_NUMBNESS_STATE', label: 'Emocionalna utrnulost' },
    { value: 'MOTIVATIONAL_COLLAPSE', label: 'Motivacioni kolaps' },
    { value: 'SEVERE_DEPRESSIVE_RISK_PATTERN', label: 'Tezak depresivni rizik' },
    { value: 'MENTAL_FATIGUE_SYNDROME', label: 'Mentalni zamor' },
    { value: 'CONCENTRATION_DECLINE_PATTERN', label: 'Pad koncentracije' },
    { value: 'DECISION_FATIGUE_STATE', label: 'Zamor pri odlucivanju' },
    { value: 'SOCIAL_WITHDRAWAL_SYNDROME', label: 'Socijalno povlacenje' },
    { value: 'INTERPERSONAL_DETACHMENT_PATTERN', label: 'Interpersonalno udaljavanje' },
    { value: 'SOCIAL_ISOLATION_PROGRESSION', label: 'Progresija socijalne izolacije' },
    { value: 'SEVERE_PSYCHOLOGICAL_DETERIORATION', label: 'Tesko psiholosko pogorsanje' },
    { value: 'EMOTIONAL_COLLAPSE_RISK', label: 'Rizik emocionalnog kolapsa' },
    { value: 'SELF_HARM_RISK_PATTERN', label: 'Rizik samopovredjivanja' },
    { value: 'CRISIS_INTERVENTION_RECOMMENDED', label: 'Preporucena krizna intervencija' },
  ];
  getAnswer(field: string) {
    const all = [
      ...this.emotionalQuestions,
      ...this.sleepPhysicalQuestions,
      ...this.cognitiveQuestions,
      ...this.socialQuestions,
      ...this.stressorsQuestions,
      ...this.temporalQuestions,
    ];

    return Number(all.find((q) => q.field === field)?.answer ?? 0);
  }

  isCepDecision(decision: FinalDecision | null): boolean {
    return decision?.triggeredPatterns?.includes('CEP') ?? false;
  }

  buildAssessment() {
    return {
      userId: 1,
      timestamp: new Date(),

      stressLevel: this.getAnswer('stressLevel'),
      emotionalExhaustion: this.getAnswer('emotionalExhaustion'),
      nervousness: this.getAnswer('nervousness'),
      overloadFeeling: this.getAnswer('overloadFeeling'),
      lowMoodFrequency: this.getAnswer('lowMoodFrequency'),
      moodSwings: this.getAnswer('moodSwings'),
      sadnessLevel: this.getAnswer('sadnessLevel'),
      lossOfControlFeeling: this.getAnswer('lossOfControlFeeling'),
      irritability: this.getAnswer('irritability'),
      worryFrequency: this.getAnswer('worryFrequency'),

      sleepHours: this.getAnswer('sleepHours'),
      sleepProblems: this.getAnswer('sleepProblems'),
      nightAwakenings: this.getAnswer('nightAwakenings'),
      restedAfterSleep: this.getAnswer('restedAfterSleep'),
      chronicFatigue: this.getAnswer('chronicFatigue'),
      stressHeadaches: this.getAnswer('stressHeadaches'),
      appetiteChanges: this.getAnswer('appetiteChanges'),
      lowEnergy: this.getAnswer('lowEnergy'),
      physicalExhaustion: this.getAnswer('physicalExhaustion'),

      concentrationProblems: this.getAnswer('concentrationProblems'),
      forgetfulness: this.getAnswer('forgetfulness'),
      decisionDifficulty: this.getAnswer('decisionDifficulty'),
      mentalConfusion: this.getAnswer('mentalConfusion'),
      productivityDrop: this.getAnswer('productivityDrop'),
      attentionSpanIssues: this.getAnswer('attentionSpanIssues'),
      mentalFatigue: this.getAnswer('mentalFatigue'),

      socialAvoidance: this.getAnswer('socialAvoidance'),
      communicationWithdrawal: this.getAnswer('communicationWithdrawal'),
      socialIsolationFeeling: this.getAnswer('socialIsolationFeeling'),
      lossOfInterest: this.getAnswer('lossOfInterest'),
      familyAvoidance: this.getAnswer('familyAvoidance'),
      timeSpentAlone: this.getAnswer('timeSpentAlone'),
      emotionalDistance: this.getAnswer('emotionalDistance'),

      workPressure: this.getAnswer('workPressure'),
      financialProblems: this.getAnswer('financialProblems'),
      recentStressEvent: this.getAnswer('recentStressEvent'),
      relationshipIssues: this.getAnswer('relationshipIssues'),
      lackOfRestTime: this.getAnswer('lackOfRestTime'),
      constantPressure: this.getAnswer('constantPressure'),
      ruminationOnTasks: this.getAnswer('ruminationOnTasks'),

      symptomDuration: this.getAnswer('symptomDuration'),
      isolationDuration: this.getAnswer('isolationDuration'),
      exhaustionDuration: this.getAnswer('exhaustionDuration'),
      panicFrequency: this.getAnswer('panicFrequency'),
      stressTrend: this.getAnswer('stressTrend'),
      moodDegradationTrend: this.getAnswer('moodDegradationTrend'),
      productivityDeclineTrend: this.getAnswer('productivityDeclineTrend'),
    };
  }

  checkBackwardHypothesis() {
    if (!this.isFormValid()) {
      alert('Molimo vas da odgovorite na sva pitanja pre provere hipoteze.');
      return;
    }

    this.view = 'loading';
    this.backwardResult = null;

    this.assessmentService.checkBackward(this.buildAssessment(), this.selectedTargetState).subscribe({
      next: (res) => {
        this.zone.run(() => {
          this.backwardResult = res;
          this.view = 'backwardResult';
          this.cdr.detectChanges();
        });
      },
      error: (err) => {
        console.error(err);
        this.view = 'form';
        alert('Greska pri backward proveri');
      },
    });
  }

  finishSurvey() {
    if (!this.isFormValid()) {
      alert('Molimo vas da odgovorite na sva pitanja pre završetka.');
      return;
    }

    const assessment = {
      userId: 1,
      timestamp: new Date(),

      stressLevel: this.getAnswer('stressLevel'),
      emotionalExhaustion: this.getAnswer('emotionalExhaustion'),
      nervousness: this.getAnswer('nervousness'),
      overloadFeeling: this.getAnswer('overloadFeeling'),
      lowMoodFrequency: this.getAnswer('lowMoodFrequency'),
      moodSwings: this.getAnswer('moodSwings'),
      sadnessLevel: this.getAnswer('sadnessLevel'),
      lossOfControlFeeling: this.getAnswer('lossOfControlFeeling'),
      irritability: this.getAnswer('irritability'),
      worryFrequency: this.getAnswer('worryFrequency'),

      sleepHours: this.getAnswer('sleepHours'),
      sleepProblems: this.getAnswer('sleepProblems'),
      nightAwakenings: this.getAnswer('nightAwakenings'),
      restedAfterSleep: this.getAnswer('restedAfterSleep'),
      chronicFatigue: this.getAnswer('chronicFatigue'),
      stressHeadaches: this.getAnswer('stressHeadaches'),
      appetiteChanges: this.getAnswer('appetiteChanges'),
      lowEnergy: this.getAnswer('lowEnergy'),
      physicalExhaustion: this.getAnswer('physicalExhaustion'),

      concentrationProblems: this.getAnswer('concentrationProblems'),
      forgetfulness: this.getAnswer('forgetfulness'),
      decisionDifficulty: this.getAnswer('decisionDifficulty'),
      mentalConfusion: this.getAnswer('mentalConfusion'),
      productivityDrop: this.getAnswer('productivityDrop'),
      attentionSpanIssues: this.getAnswer('attentionSpanIssues'),
      mentalFatigue: this.getAnswer('mentalFatigue'),

      socialAvoidance: this.getAnswer('socialAvoidance'),
      communicationWithdrawal: this.getAnswer('communicationWithdrawal'),
      socialIsolationFeeling: this.getAnswer('socialIsolationFeeling'),
      lossOfInterest: this.getAnswer('lossOfInterest'),
      familyAvoidance: this.getAnswer('familyAvoidance'),
      timeSpentAlone: this.getAnswer('timeSpentAlone'),
      emotionalDistance: this.getAnswer('emotionalDistance'),

      workPressure: this.getAnswer('workPressure'),
      financialProblems: this.getAnswer('financialProblems'),
      recentStressEvent: this.getAnswer('recentStressEvent'),
      relationshipIssues: this.getAnswer('relationshipIssues'),
      lackOfRestTime: this.getAnswer('lackOfRestTime'),
      constantPressure: this.getAnswer('constantPressure'),
      ruminationOnTasks: this.getAnswer('ruminationOnTasks'),

      symptomDuration: this.getAnswer('symptomDuration'),
      isolationDuration: this.getAnswer('isolationDuration'),
      exhaustionDuration: this.getAnswer('exhaustionDuration'),
      panicFrequency: this.getAnswer('panicFrequency'),
      stressTrend: this.getAnswer('stressTrend'),
      moodDegradationTrend: this.getAnswer('moodDegradationTrend'),
      productivityDeclineTrend: this.getAnswer('productivityDeclineTrend'),
    };

    console.log(assessment);

    this.view = 'loading';
    this.finalDecision = null;

    this.assessmentService.evaluate(assessment).subscribe({
      next: (res) => {
        this.zone.run(() => {
          this.finalDecision = res;
          this.view = 'result';

          if (this.isCepDecision(res)) {
            alert('CEP upozorenje: finalna odluka je zasnovana i na promenama kroz istoriju procena.');
          }

          this.cdr.detectChanges();
        });
      },

      error: (err) => {
        console.error(err);
        this.view = 'form';
        alert('Greška pri evaluaciji');
      },
    });
  }
  resetSurvey() {
  this.zone.run(() => {
    this.finalDecision = null;
    this.backwardResult = null;
    this.loading = false;
    this.view = 'form';

    const reset = (arr: any[]) => {
      arr.forEach((q) => (q.answer = null));
    };

    reset(this.emotionalQuestions);
    reset(this.sleepPhysicalQuestions);
    reset(this.cognitiveQuestions);
    reset(this.socialQuestions);
    reset(this.stressorsQuestions);
    reset(this.temporalQuestions);

    this.cdr.detectChanges();
  });
}
}
