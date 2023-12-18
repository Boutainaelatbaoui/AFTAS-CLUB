import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, FormGroup} from '@angular/forms';
import { MatPaginatorModule } from '@angular/material/paginator';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MemberComponent } from './components/member/member.component';
import { CompetitionComponent } from './components/competition/competition.component';
import { HuntingComponent } from './components/hunting/hunting.component';
import { RankingComponent } from './components/ranking/ranking.component';
import { CompetitionFormComponent } from './components/competition/competition-form/competition-form.component';
import { MemberFormComponent } from './components/member/member-form/member-form.component';
import { CompetitionMemberFormComponent } from './components/competition/competition-member-form/competition-member-form.component';
import { HuntingFormComponent } from './components/hunting/hunting-form/hunting-form.component';
import { RankingTopComponent } from './components/ranking/ranking-top/ranking-top.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent,
    FooterComponent,
    DashboardComponent,
    MemberComponent,
    CompetitionComponent,
    HuntingComponent,
    RankingComponent,
    CompetitionFormComponent,
    MemberFormComponent,
    CompetitionMemberFormComponent,
    HuntingFormComponent,
    RankingTopComponent,
  ],
  imports: [
    [BrowserModule, FormsModule],
      AppRoutingModule,
      HttpClientModule,
      MatPaginatorModule,
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
