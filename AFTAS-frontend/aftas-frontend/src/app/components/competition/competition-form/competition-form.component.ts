import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { Competition } from 'src/app/models/competition';
import { formatDate } from '@angular/common';
import { ActivatedRoute, Router, NavigationExtras } from '@angular/router';
import Swal from 'sweetalert2';

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
    private route: ActivatedRoute,
    private router: Router
  ) {}

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.competition.date = formatDate(this.competition.date, 'yyyy-MM-dd', 'en-US');
  
      this.competitionService.createCompetition(this.competition).subscribe(
        (createdCompetition) => {
          Swal.fire('Competition created', 'The competition has been created successfully', 'success');
          this.router.navigate(['/competitions']);
        },
        (error) => {
          console.error('Error creating competition:', error);
  
          const errorMessage = this.extractErrorMessage(error);
          Swal.fire('Error creating competition', errorMessage, 'error');
        }
      );
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
  
}
