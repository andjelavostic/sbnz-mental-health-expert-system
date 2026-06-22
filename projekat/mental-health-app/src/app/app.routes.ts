import { Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { SurveyFormComponent } from './components/survey-form-component/survey-form-component';
import { HistoryComponent } from './components/history-component/history-component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'survey', component: SurveyFormComponent },
  { path: 'history', component: HistoryComponent }
]