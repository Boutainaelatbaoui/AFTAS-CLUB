import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
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
export class RankingComponent implements OnInit, AfterViewInit, OnDestroy {
  rankings: Ranking[] = [];
  selectedCompetitionId: number = 0;
  competitions: Competition[] = [];
  dataTable: any;

  constructor(
    private rankingService: RankingService,
    private competitionService: CompetitionService
  ) {}

  ngOnInit(): void {
    this.loadRankings();
    this.loadCompetitions();
  }

  ngAfterViewInit(): void {
    this.initDataTable();
  }

  ngOnDestroy(): void {
    if (this.dataTable) {
      this.dataTable.destroy();
    }
  }

  loadRankings(): void {
    if (this.selectedCompetitionId) {
      this.rankingService.getRankingsForCompetition(this.selectedCompetitionId).subscribe(
        (rankings) => {
          this.rankings = rankings;
          this.refreshDataTable();
        },
        (error) => {
          console.error('Error loading rankings:', error);
  
          const errorMessage = this.extractErrorMessage(error);
          alert(`Error loading rankings: ${errorMessage}`);
        }
      );
    } else {
      this.rankingService.getAllRankings().subscribe((rankings) => {
        this.rankings = rankings;
        this.refreshDataTable();
      });
    }
  }

  private extractErrorMessage(error: any): string {
    if (error.error && typeof error.error === 'object' && 'message' in error.error) {
      return error.error.message;
    } else if (error.message) {
      return error.message;
    }
  
    return 'An unknown error occurred.';
  }

  onCompetitionChange(): void {
    this.loadRankings();
  }

  loadCompetitions(): void {
    this.competitionService.getAllCompetitions().subscribe((competitions) => {
      this.competitions = competitions;
    });
  }

  showAllRankings(): void {
    this.selectedCompetitionId = 0;
    this.loadRankings();
  }

  private initDataTable(): void {
    this.dataTable = $('#rankingTable').DataTable({
      searching: true,
    });
  }

  private refreshDataTable(): void {
    if (this.dataTable) {
      this.dataTable.clear();

      this.rankings.forEach((ranking) => {
        this.dataTable.row.add([
          ranking.rank,
          ranking.score,
          ranking.competition.code,
          ranking.member.name + " " + ranking.member.familyName
        ]);
      });

      this.dataTable.draw();
    }
  }
}

