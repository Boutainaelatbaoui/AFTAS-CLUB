import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MemberService } from 'src/app/services/memberService/member.service';
import { Member } from 'src/app/models/member';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-member-form',
  templateUrl: './member-form.component.html',
  styleUrls: ['./member-form.component.css'],
})
export class MemberFormComponent {
  member: Member = {
    name: '',
    familyName: '',
    nationality: '',
    identityDocument: '',
    identityNumber: '',
  };

  constructor(
    private memberService: MemberService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  onSubmit(form: NgForm): void {
    if (form.valid) {
      this.memberService.createMember(this.member).subscribe(
        (response) => {
          console.log('Member created successfully:', response);
          this.router.navigate(['/members']);
        },
        (error) => {
          console.error('Error creating member:', error);
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
