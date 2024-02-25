import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Competition } from 'src/app/models/competition';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { RankingService } from 'src/app/services/rankingService/ranking.service';
import { StorageService } from 'src/app/services/storage/storage.service';

@Component({
  selector: 'app-member-competitions',
  templateUrl: './member-competitions.component.html',
  styleUrls: ['./member-competitions.component.css']
})
export class MemberCompetitionsComponent implements OnInit {
  memberId: number | null = null;
  competitions: Competition[] = [];
  imageSection2: string = 'assets/img/Hunt2.jpg';


  constructor(
    private storageService: StorageService,
    private rankingService: RankingService
  ) {}

  ngOnInit(): void {
    this.memberId = this.storageService.getConnectedMemberId();

    console.log(this.memberId);
    
    if (this.memberId) {
      this.loadCompetitions();
    }
  }

  loadCompetitions(): void {
    this.rankingService
      .getMemberCompetitions(this.memberId!)
      .subscribe((competitions) => {
        console.log(competitions);
        
        this.competitions = competitions;
      });
  }
}
