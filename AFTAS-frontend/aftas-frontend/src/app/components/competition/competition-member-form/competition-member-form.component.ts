import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { MemberService } from 'src/app/services/memberService/member.service';
import { Competition } from 'src/app/models/competition';
import { Member } from 'src/app/models/member';
import { ActivatedRoute, Router } from '@angular/router';
import { MemberResponse } from 'src/app/models/member-response';

@Component({
  selector: 'app-competition-member-form',
  templateUrl: './competition-member-form.component.html',
  styleUrls: ['./competition-member-form.component.css'],
})
export class CompetitionMemberFormComponent implements OnInit {
  competitions: Competition[] = [];
  members: MemberResponse[] = [];
  selectedCompetitionId: number = 0;
  selectedMemberId: number = 0;

  constructor(private competitionService: CompetitionService,
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.loadCompetitions();
    this.loadMembers();
  }

  private loadCompetitions(): void {
    this.competitionService.getAllCompetitions().subscribe((competitions) => {
      this.competitions = competitions;
    });
  }

  private loadMembers(): void {
    this.memberService.getMembers().subscribe((members) => {
      this.members = members;
    });
  }

  onSubmit(form: NgForm): void {
    if (form.valid) {
      const competitionId = this.selectedCompetitionId;
      const memberId = this.selectedMemberId;

      this.competitionService.addMemberToCompetition(competitionId, memberId).subscribe(
        () => {
          console.log('Member added to the competition successfully.');
          this.router.navigate(['/rankings']);
        },
        (error) => {
          console.error('Error adding member to competition:', error);
          try {
            const errorObject = JSON.parse(error.error);
            const errorMessage = errorObject.message;
      
            alert(`Error creating member: ${errorMessage}`);
          } catch (parseError) {
            console.error('Error parsing JSON:', parseError);
            alert('An unexpected error occurred.');
          }
        }
      );
    }
  }
}
