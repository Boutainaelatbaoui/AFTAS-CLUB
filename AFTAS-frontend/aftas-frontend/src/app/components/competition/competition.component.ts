import { Component, OnInit } from '@angular/core';
import { Competition } from 'src/app/models/competition';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css'],
})
export class CompetitionComponent implements OnInit {
  competitions: Competition[] = [];
  imageSection2: string = 'assets/img/Hunt2.jpg';

  constructor(private competitionService: CompetitionService) {}

  ngOnInit(): void {
    this.getCompetitions();
  }

  getCompetitions(): void {
    this.competitionService.getAllCompetitions().subscribe(
      (competitions) => {
        this.competitions = competitions;
      },
      (error) => {
        console.error('Error fetching competitions:', error);
      }
    );
  }
}
