import { Routes } from '@angular/router';
import { HomeComponent } from './components/home-component/home-component';
import { SurveyFormComponent } from './components/survey-form-component/survey-form-component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'survey', component: SurveyFormComponent }
]