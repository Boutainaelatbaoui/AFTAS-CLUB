import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, FormGroup} from '@angular/forms';

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
    MemberFormComponent
  ],
  imports: [
    [BrowserModule, FormsModule],
      AppRoutingModule,
      HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
