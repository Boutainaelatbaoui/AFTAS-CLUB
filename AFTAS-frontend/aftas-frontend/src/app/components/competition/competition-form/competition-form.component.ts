import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { Competition } from 'src/app/models/competition';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-competition-form',
  templateUrl: './competition-form.component.html',
  styleUrls: ['./competition-form.component.css'],
})
export class CompetitionFormComponent {
  competition: Competition = {
    id: 0,
    code: '',
    location: '',
    date: '',
    startTime: '',
    endTime: '',
    limitParticipants: 0,
    numberOfParticipants: 0,
    amount: 0,
  };

  constructor(
    private competitionService: CompetitionService,
  ) {}

  onSubmit(form: NgForm): void {
    if (form.valid) {
      // Format the date without the time component
      this.competition.date = formatDate(this.competition.date, 'yyyy-MM-dd', 'en-US');
  
      // Directly use start and end times from the form (they are already strings)
      this.competitionService.createCompetition(this.competition).subscribe(
  
        
        (createdCompetition) => {
          console.log('Competition created successfully:', createdCompetition);
          console.log(this.competition);
        },
        (error) => {
          console.log(this.competition);
          console.error('Error creating competition:', error);
        }
      );
    }
  }
  
  
  
  
}
