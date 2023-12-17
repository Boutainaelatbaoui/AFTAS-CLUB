import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HuntingService } from 'src/app/services/huntingService/hunting.service'; 
import { Hunting } from 'src/app/models/hunting';
import { MemberService } from 'src/app/services/memberService/member.service';
import { CompetitionService } from 'src/app/services/competitionService/competition.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-hunting-form',
  templateUrl: './hunting-form.component.html',
  styleUrls: ['./hunting-form.component.css'],
})
export class HuntingFormComponent implements OnInit {
  hunting: Hunting = {
    numberOfFish: 0,
    fishId: 0,
    competitionId: 0,
    weight: 0,
    memberId: 0,
  };

  fishes: any[] = [];
  competitions: any[] = [];
  members: any[] = [];

  constructor(
    private huntingService: HuntingService,
    private memberService: MemberService,
    private competitionService: CompetitionService,
    private route: ActivatedRoute,
    private router: Router 
  ) {}

  ngOnInit(): void {
    this.loadCompetitions();
    this.loadMembers();
    this.loadFishes();
  }

  private loadCompetitions(): void {
    this.competitionService.getAllCompetitions().subscribe((competitions) => {
      this.competitions = competitions;
    });
  }

  private loadFishes(): void {
    this.huntingService.getAllFishes().subscribe((fishes) => { 
      this.fishes = fishes;
    });
  }

  private loadMembers(): void {
    this.memberService.getMembers().subscribe((members) => {
      this.members = members;
    });
  }

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.huntingService.createHunting(this.hunting).subscribe(
        (createdHunting) => {
          console.log('Hunting record created successfully:', createdHunting);
          this.router.navigate(['/huntings']);
        },
        (error) => {
          console.error('Error creating hunting record:', error);
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
