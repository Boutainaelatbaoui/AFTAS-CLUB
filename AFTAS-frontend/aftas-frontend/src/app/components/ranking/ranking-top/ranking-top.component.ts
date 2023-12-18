import { Component, OnInit } from '@angular/core';
import { Competition } from 'src/app/models/competition';
import { Ranking } from 'src/app/models/ranking';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { RankingService } from 'src/app/services/rankingService/ranking.service';

@Component({
  selector: 'app-ranking-top',
  templateUrl: './ranking-top.component.html',
  styleUrls: ['./ranking-top.component.css']
})
export class RankingTopComponent implements OnInit {
  competitionId: number = 0;
  topRankings: Ranking[] = [];
  competitions: Competition[] = [];
  imageR: string = 'assets/img/podium.png';
  imageRa: string = 'assets/img/double-down.gif';

  constructor(
    private rankingDetailsService: RankingService,
    private competitionService: CompetitionService
  ) {}

  ngOnInit(): void {
    this.loadCompetitions();
  }

  loadCompetitions(): void {
    this.competitionService.getAllCompetitions().subscribe(
      (competitions) => {
        this.competitions = competitions;
      },
      (error) => {
        console.error('Error loading competitions:', error);
      }
    );
  }

  onCompetitionChange(): void {
    if (this.competitionId) {
      this.loadTopRankings();
    }
  }

  loadTopRankings(): void {
    this.rankingDetailsService.getTopRankingsForCompetition(this.competitionId).subscribe(
      (rankings) => {
        this.topRankings = rankings;
      },
      (error) => {
        console.error('Error loading top rankings:', error);
        const errorMessage = this.extractErrorMessage(error);
        alert(`Error creating competition: ${errorMessage}`);
      }
    );
  }

  private extractErrorMessage(error: any): string {
    if (error.error && typeof error.error === 'object' && 'message' in error.error) {
      return error.error.message;
    } else if (error.message) {
      return error.message;
    }
  
    return 'An unknown error occurred.';
  }
}
