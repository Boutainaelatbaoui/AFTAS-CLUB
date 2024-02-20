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
import { LoginComponent } from './components/login/login.component';
import { noAuthGuardGuard } from './helpers/noAuth/no-auth-guard.guard';
import { RegisterComponent } from './components/register/register.component';
import { authGuard } from './helpers/auth/auth.guard';

const routes: Routes = [
  { path: '', component: HomePageComponent, canActivate: [authGuard] },
  { path:'competition-form', component: CompetitionFormComponent, canActivate: [authGuard] },
  { path: 'member-form', component: MemberFormComponent, canActivate: [authGuard] },
  { path: 'competitions', component: CompetitionComponent, canActivate: [authGuard] },
  { path: 'members', component: MemberComponent, canActivate: [authGuard] },
  { path: 'rankings', component:RankingComponent, canActivate: [authGuard] },
  { path:'hunting-form', component: HuntingFormComponent, canActivate: [authGuard] },
  { path: 'huntings', component: HuntingComponent, canActivate: [authGuard] },
  { path: 'ranking-top', component: RankingTopComponent, canActivate: [authGuard] },
  { path: 'competition-member-form', component: CompetitionMemberFormComponent, canActivate: [authGuard] },
  {
    path: 'login',
    component:LoginComponent,
    canActivate: [noAuthGuardGuard]
  },
  {
    path: 'register',
    component:RegisterComponent,
    canActivate: [noAuthGuardGuard]
  },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
