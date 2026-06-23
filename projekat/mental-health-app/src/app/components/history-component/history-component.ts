import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AssessmentService } from '../../services/assesment-service';
import { FinalDecision } from '../../models/final-decision.model';

@Component({
  selector: 'app-history-component',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './history-component.html',
  styleUrl: './history-component.css',
})
export class HistoryComponent implements OnInit {
  history: FinalDecision[] = [];
  loading = true;
  errorMessage = '';

  constructor(
    private assessmentService: AssessmentService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    const userId = 1;

    console.log('userId:', userId);

    this.assessmentService.getHistory(userId).subscribe({
      next: (res) => {
        console.log('History:', res);

        this.history = res.map((h: any) => ({
          ...h,
          date: h.date ? new Date(h.date) : undefined,
        }));

        this.loading = false;
        this.cdr.detectChanges();
      },

      error: (err) => {
        console.error(err);
        this.errorMessage = 'Istorija trenutno ne moze da se ucita.';
        this.loading = false;
        this.cdr.detectChanges(); 
      },
    });
  }
}
