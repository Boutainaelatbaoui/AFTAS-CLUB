import { Component, AfterViewInit, OnDestroy } from '@angular/core';
import { RankingService } from 'src/app/services/rankingService/ranking.service';
import { Ranking } from 'src/app/models/ranking';
declare var $: any;

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css'],
})
export class RankingComponent implements AfterViewInit, OnDestroy {
  rankings: Ranking[] = [];

  constructor(private rankingService: RankingService) {}

  ngAfterViewInit(): void {
    this.rankingService.getAllRankings().subscribe((data) => {
      this.rankings = data;

      $(document).ready(() => {
        $('#rankingTable').DataTable({
          searching: true,
        });
      });
    });
  }

  ngOnDestroy(): void {
    $('#rankingTable').DataTable().destroy();
  }
}
