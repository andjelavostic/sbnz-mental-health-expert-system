export interface FinalDecision {
  finalState: any; 
  severity: 'LOW' | 'MODERATE' | 'HIGH' | 'CRITICAL';

  explanation: string;
  recommendation: string;

  triggeredPatterns: string[];

  score: number;
  date?: string | Date;
}
