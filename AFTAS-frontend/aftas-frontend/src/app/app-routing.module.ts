import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CompetitionComponent } from './components/competition/competition.component';
import { CompetitionFormComponent } from './components/competition/competition-form/competition-form.component';
import { MemberComponent } from './components/member/member.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path:'competition-form', component: CompetitionFormComponent },
  { path: 'competitions', component: CompetitionComponent },
  { path: 'members', component: MemberComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
