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
  filteredCompetitions: Competition[] = [];
  selectedFilter: string = '';
  imageSection2: string = 'assets/img/Hunt2.jpg';

  constructor(private competitionService: CompetitionService) {}

  ngOnInit(): void {
    this.competitionService.getAllCompetitions().subscribe((competitions) => {
      this.competitions = competitions;
      this.filteredCompetitions = competitions;
    });
  }

  filterCompetitions(): void {
    if (this.selectedFilter === 'en cours') {
      this.filteredCompetitions = this.competitions.filter(
        (competition) => this.isCompetitionInProgress(competition)
      );
    } else if (this.selectedFilter === 'fermé') {
      this.filteredCompetitions = this.competitions.filter(
        (competition) => !this.isCompetitionInProgress(competition)
      );
    } else {
      this.filteredCompetitions = this.competitions;
    }
  }

  private isCompetitionInProgress(competition: Competition): boolean {
    const currentDate = new Date();
    const competitionDateTime = new Date(`${competition.date}T${competition.startTime}`);
    return currentDate < competitionDateTime;
  }

  selectFilter(filter: string): void {
    console.log('Filter selected:', filter);
    this.selectedFilter = filter;
    this.filterCompetitions();
  }
  
}
