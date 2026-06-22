export interface FinalDecision {
  finalState: any; 
  severity: 'MODERATE' | 'HIGH' | 'CRITICAL';

  explanation: string;
  recommendation: string;

  triggeredPatterns: string[];

  score: number;
}