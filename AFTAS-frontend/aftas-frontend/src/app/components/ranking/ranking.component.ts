import { Component, AfterViewInit, OnDestroy } from '@angular/core';
import { RankingService } from 'src/app/services/rankingService/ranking.service';
import { Ranking } from 'src/app/models/ranking';
import { Competition } from 'src/app/models/competition';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
declare var $: any;

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css'],
})
export class RankingComponent implements AfterViewInit, OnDestroy {
  rankings: Ranking[] = [];
  competitions: Competition[] = [];
  selectedCompetitionId: number = 0;

  constructor(private rankingService: RankingService,
    private competitionService: CompetitionService) {}

  ngAfterViewInit(): void {
    this.loadRankings();
    this.loadCompetitions();
  }

  ngOnDestroy(): void {
    $('#rankingTable').DataTable().destroy();
  }

  onCompetitionChange(): void {
    if (this.selectedCompetitionId) {
      this.loadRankingsForCompetition();
    } else {
      this.loadRankings();
    }
  }

  loadRankings(): void {
    this.rankingService.getAllRankings().subscribe((rankings) => {
      this.rankings = rankings;

      $(document).ready(() => {
        $('#rankingTable').DataTable({
          searching: true,
        });
      });
    });
  }

  loadRankingsForCompetition(): void {
    this.rankingService.getRankingsForCompetition(this.selectedCompetitionId).subscribe(
      () => {
        console.log('Rankings loaded successfully');
        alert('Rang has been generated for this competition!')
      },
      (error) => {
        console.error('Error creating competition:', error);

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


  private loadCompetitions(): void {
    this.competitionService.getAllCompetitions().subscribe((competitions) => {
      this.competitions = competitions;
    });
  }
}
