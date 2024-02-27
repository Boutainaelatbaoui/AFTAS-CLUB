import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Member } from 'src/app/models/member';
import { MemberResponse } from 'src/app/models/member-response';
import { Role } from 'src/app/models/role';
import { MemberService } from 'src/app/services/memberService/member.service';
import { StorageService } from 'src/app/services/storage/storage.service';
import Swal from 'sweetalert2';

declare var $: any;

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css'],
})

export class MemberComponent implements OnInit {
  members: MemberResponse[] = [];
  roles: Role[] = [];
  selectedRoleIds: { [memberId: number]: any | null } = {};
  
  constructor(private memberService: MemberService, private storageService: StorageService) {}

  user = this.storageService.getSavedUser();
  permissions = this.storageService.getPermissions();
  showAdminBoard = false;
  showManagerBoard = false;

  ngOnInit(): void {
    if(this.user) {
      console.log(this.user);
      
      this.showAdminBoard = this.permissions.includes('CAN_MANAGE_COMPETITIONS');
      this.showManagerBoard = this.permissions.includes('CAN_MANAGE_USERS');
    }
    this.loadRoles();
    this.memberService.getMembers().subscribe((result) => {
      this.members = result;
      this.initializeSelectedRoleIds();

      $(document).ready(() => {
        $('#memberDataTable').DataTable();
      });
    });
  }

  loadRoles() {
    this.memberService.getRoles().subscribe(
      (roles) => {
        this.roles = roles;
      },
      (error) => {
        console.error('Error loading roles', error);
      }
    );
  }

  initializeSelectedRoleIds() {
    this.members.forEach(member => {
      this.selectedRoleIds[member.id] = member.role ? member.role.id : null;
    });
  }

  updateUserRole(memberId: number): void {
    const selectedRoleId = this.selectedRoleIds[memberId];

    this.memberService.updateMemberRole(memberId, selectedRoleId).subscribe(
      () => {
        Swal.fire('Success','Role updated successfully', 'success');
      },
      (error) => {
        Swal.fire('Error','Error updating role', 'error');
      }
    );
  }

  activateUser(userId: number): void {
    this.memberService.activateUser(userId).subscribe(
      () => {
        Swal.fire('Success','User activated successfully', 'success');
      },
      (error) => {
        console.log(error);
        
        Swal.fire('Error','Error activating user', 'error');
      }
    );
  }

  deactivateUser(userId: number): void {
    this.memberService.deactivateUser(userId).subscribe(
      () => {
        Swal.fire('Success','User deactivated successfully', 'success');
      },
      (error) => {
        console.log(error);
        
        Swal.fire('Error','Error deactivating user', 'error');
      }
    );
  }
}
