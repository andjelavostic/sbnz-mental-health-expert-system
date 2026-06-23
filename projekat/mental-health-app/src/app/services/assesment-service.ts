import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FinalDecision } from '../models/final-decision.model';

@Injectable({ providedIn: 'root' })
export class AssessmentService {
  private baseUrl = 'http://localhost:8080/api/rules';

  constructor(private http: HttpClient) {}

  evaluate(data: any) {
    return this.http.post<FinalDecision>(`${this.baseUrl}/evaluate`, data);
  }

  getHistory(userId: number) {
    return this.http.get(`${this.baseUrl}/history/${userId}`);
  }
}
