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
export const MOCK_FINAL_DECISION: FinalDecision = {
  finalState: 'AT_RISK',
  severity: 'HIGH',

  score: 0.74,

  explanation: `
    Na osnovu odgovora, primećen je povišen nivo stresa,
    smanjena kvaliteta sna i blagi kognitivni zamor.
    Obrasci ukazuju na povećano emocionalno opterećenje.
  `,

  recommendation: `
    Preporučuje se smanjenje dnevnog stresa, poboljšanje higijene sna
    i uvođenje redovnih pauza tokom dana. Ako simptomi potraju,
    savetuje se razgovor sa stručnim licem.
  `,

  triggeredPatterns: [
    'HIGH_STRESS_LEVEL',
    'SLEEP_DEGRADATION',
    'COGNITIVE_FATIGUE',
    'MODERATE_SOCIAL_WITHDRAWAL',
  ],
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
  ],
  templateUrl: './survey-form-component.html',
  styleUrls: ['./survey-form-component.css'],
})
export class SurveyFormComponent {
  constructor(private assessmentService: AssessmentService) {}
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
      type: 'boolean',
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
    }
  ];
  sleepPhysicalQuestions = [
    {
      field: 'sleepHours',
      text: 'Koliko sati prosečno spavate?',
      type: 'scale',
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
      type: 'scale',
      answer: null,
    },
    {
      field: 'recentStressEvent',
      text: 'Da li ste doživeli značajan stresni događaj u poslednjih 6 meseci?',
      type: 'scale',
      answer: null,
    },
    {
      field: 'relationshipIssues',
      text: 'Da li imate probleme u partnerskim ili porodičnim odnosima?',
      type: 'scale',
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
      type: 'number',
      answer: null,
    },
    {
      field: 'isolationDuration',
      text: 'Koliko dugo traje socijalna izolacija (u danima)?',
      type: 'number',
      answer: null,
    },
    {
      field: 'exhaustionDuration',
      text: 'Koliko dugo traje emocionalna iscrpljenost (u danima)?',
      type: 'number',
      answer: null,
    },
    {
      field: 'panicFrequency',
      text: 'Koliko često imate panične epizode (nedeljno)?',
      type: 'number',
      answer: null,
    },
    {
      field: 'stressTrend',
      text: 'Kako se menja nivo stresa kroz vreme? (1 opada - 5 raste)',
      type: 'scale',
      answer: null,
    },
    {
      field: 'moodDegradationTrend',
      text: 'Kako se menja raspoloženje kroz vreme? (1 bolje - 5 lošije)',
      type: 'scale',
      answer: null,
    },
    {
      field: 'productivityDeclineTrend',
      text: 'Kako se menja produktivnost kroz vreme? (1 stabilno - 5 veliki pad)',
      type: 'scale',
      answer: null,
    },
  ];
  isFormValid(): boolean {
    return [...this.emotionalQuestions, ...this.sleepPhysicalQuestions].every(
      (q) => q.answer !== null,
    );
  }
  mapSection(questions: any[]) {
    const result: any = {};

    questions.forEach((q) => {
      result[q.field] = Number(q.answer);
    });

    return result;
  }

  finalDecision: FinalDecision | null = MOCK_FINAL_DECISION;
  loading = false;
  finishSurvey() {
    if (!this.isFormValid()) {
      alert('Molimo vas da odgovorite na sva pitanja pre završetka.');
      return;
    }

    const assessment: UserAssessment = {
      userId: 1,
      timestamp: new Date(),
      emotionalState: this.mapSection(this.emotionalQuestions),
      sleepPhysical: this.mapSection(this.sleepPhysicalQuestions),
      cognitive: this.mapSection(this.cognitiveQuestions),
      social: this.mapSection(this.socialQuestions),
      stressors: this.mapSection(this.stressorsQuestions),
      temporal: this.mapSection(this.temporalQuestions),
    };

    console.log(assessment);
    this.loading = true;

    this.assessmentService.evaluate(assessment).subscribe({
      next: (res: FinalDecision) => {
        this.finalDecision = res;
        this.loading = false;
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        alert('Greška pri evaluaciji');
      },
    });
  }
}
