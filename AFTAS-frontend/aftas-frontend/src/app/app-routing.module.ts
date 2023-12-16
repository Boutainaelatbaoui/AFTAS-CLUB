import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { CompetitionComponent } from './components/competition/competition.component';
import { CompetitionFormComponent } from './components/competition/competition-form/competition-form.component';
import { MemberComponent } from './components/member/member.component';
import { MemberFormComponent } from './components/member/member-form/member-form.component';
import { CompetitionMemberFormComponent } from './components/competition/competition-member-form/competition-member-form.component';
import { RankingComponent } from './components/ranking/ranking.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path:'competition-form', component: CompetitionFormComponent },
  { path: 'member-form', component: MemberFormComponent },
  { path: 'competitions', component: CompetitionComponent },
  { path: 'members', component: MemberComponent },
  { path: 'rankings', component:RankingComponent },
  { path: 'competition-member-form', component: CompetitionMemberFormComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
