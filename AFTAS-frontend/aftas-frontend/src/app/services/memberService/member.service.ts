import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Member } from 'src/app/models/member';

@Injectable({
  providedIn: 'root',
})
export class MemberService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getMembers(): Observable<Member[]> {
    return this.http.get<Member[]>(this.apiUrl + '/members/all');
  }

  createMember(member: Member): Observable<any> {
    return this.http.post('http://localhost:8080/api/members', member, { responseType: 'text' });
  }
  
}

