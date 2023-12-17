import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CompetitionComponent } from './components/competition/competition.component';
import { CompetitionFormComponent } from './components/competition/competition-form/competition-form.component';
import { MemberComponent } from './components/member/member.component';
import { MemberFormComponent } from './components/member/member-form/member-form.component';
import { CompetitionMemberFormComponent } from './components/competition/competition-member-form/competition-member-form.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { HuntingComponent } from './components/hunting/hunting.component';
import { HuntingFormComponent } from './components/hunting/hunting-form/hunting-form.component';
import { RankingTopComponent } from './components/ranking/ranking-top/ranking-top.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path:'competition-form', component: CompetitionFormComponent },
  { path: 'member-form', component: MemberFormComponent },
  { path: 'competitions', component: CompetitionComponent },
  { path: 'members', component: MemberComponent },
  { path: 'rankings', component:RankingComponent },
  { path:'hunting-form', component: HuntingFormComponent },
  { path: 'huntings', component: HuntingComponent },
  { path: 'ranking-top', component: RankingTopComponent },
  { path: 'competition-member-form', component: CompetitionMemberFormComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
