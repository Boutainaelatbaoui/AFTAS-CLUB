import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, FormGroup, ReactiveFormsModule} from '@angular/forms';
import { MatPaginatorModule } from '@angular/material/paginator';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MemberComponent } from './components/member/member.component';
import { CompetitionComponent } from './components/competition/competition.component';
import { HuntingComponent } from './components/hunting/hunting.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { CompetitionFormComponent } from './components/competition/competition-form/competition-form.component';
import { MemberFormComponent } from './components/member/member-form/member-form.component';
import { CompetitionMemberFormComponent } from './components/competition/competition-member-form/competition-member-form.component';
import { HuntingFormComponent } from './components/hunting/hunting-form/hunting-form.component';
import { RankingTopComponent } from './components/ranking/ranking-top/ranking-top.component';
import { RegisterComponent } from './components/register/register.component';
import { AccessDeniedComponent } from './components/errors/access-denied/access-denied.component';
import { LoginComponent } from './components/login/login.component';
import { HttpInterceptor } from './helpers/http/http.interceptor';
import { MemberCompetitionsComponent } from './components/competition/member-competitions/member-competitions.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent,
    MemberComponent,
    CompetitionComponent,
    HuntingComponent,
    RankingComponent,
    CompetitionFormComponent,
    MemberFormComponent,
    CompetitionMemberFormComponent,
    HuntingFormComponent,
    RankingTopComponent,
    RegisterComponent,
    AccessDeniedComponent,
    LoginComponent,
    MemberCompetitionsComponent,
  ],
  imports: [
    [BrowserModule, FormsModule, ReactiveFormsModule],
      AppRoutingModule,
      HttpClientModule,
      MatPaginatorModule,
    ],
    providers: [
      { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptor, multi: true },
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
