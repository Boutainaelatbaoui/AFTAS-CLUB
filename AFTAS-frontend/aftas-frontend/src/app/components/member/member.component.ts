import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Member } from 'src/app/models/member';
import { MemberService } from 'src/app/services/memberService/member.service';

declare var $: any;

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css'],
})

export class MemberComponent implements AfterViewInit {
  members: Member[] = [];
  
  constructor(private memberService: MemberService) {}

  ngAfterViewInit(): void {
    this.memberService.getMembers().subscribe((result) => {
      this.members = result;

      $(document).ready(() => {
        $('#memberDataTable').DataTable();
      });
    });
  }
}
