import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Member } from 'src/app/models/member';
import { MemberResponse } from 'src/app/models/member-response';
import { Role } from 'src/app/models/role';

@Injectable({
  providedIn: 'root',
})
export class MemberService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getMembers(): Observable<MemberResponse[]> {
    return this.http.get<MemberResponse[]>(this.apiUrl + '/members/all');
  }

  createMember(member: Member): Observable<any> {
    return this.http.post('http://localhost:8080/api/members', member, { responseType: 'text' });
  }

  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.apiUrl}/v1/admin/roles`);
  }

  updateMemberRole(memberId: number, roleId: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/v1/admin/updateRole/${memberId}/${roleId}`, {});
  }

  activateUser(userId: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/v1/admin/activate/${userId}`, {});
  }

  deactivateUser(userId: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/v1/admin/deactivate/${userId}`, {});
  }

  
  
}

