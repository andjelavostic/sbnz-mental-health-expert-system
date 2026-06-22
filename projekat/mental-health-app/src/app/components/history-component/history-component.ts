import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-history-component',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './history-component.html',
  styleUrl: './history-component.css',
})
export class HistoryComponent {
  history = [
  {
    date: '2026-06-20',
    finalState: 'STABLE',
    severity: 'MODERATE',
    score: 0.42,
    explanation: 'Blagi stres, stabilno stanje.',
    recommendation: 'Održavati rutinu spavanja.',
    triggeredPatterns: ['LOW_STRESS']
  },
  {
    date: '2026-06-21',
    finalState: 'RISK',
    severity: 'HIGH',
    score: 0.71,
    explanation: 'Povećan nivo stresa i umora.',
    recommendation: 'Smanjiti obaveze i više odmora.',
    triggeredPatterns: ['SLEEP_DEGRADATION', 'HIGH_LOAD']
  },
  {
    date: '2026-06-22',
    finalState: 'CRITICAL',
    severity: 'CRITICAL',
    score: 0.88,
    explanation: 'Visok rizik burnout-a.',
    recommendation: 'Preporučuje se stručna pomoć.',
    triggeredPatterns: ['BURNOUT_RISK', 'CHRONIC_STRESS']
  }
];
}
