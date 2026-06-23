export interface FinalDecision {
  finalState: any; 
  severity: 'LOW' | 'MODERATE' | 'HIGH' | 'CRITICAL';

  explanation: string;
  recommendation: string;

  triggeredPatterns: string[];

  score: number;
  date?: string | Date;
}

export interface BackwardCheckResult {
  targetState: any;
  confirmed: boolean;
  cepBased: boolean;
  explanation: string;
  evidence: string[];
}
