import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Competition } from 'src/app/models/competition';
import { environment } from 'src/environments/environment';
import { CompetitionPage } from 'src/app/models/competition-page';
@Injectable({
  providedIn: 'root',
})
export class CompetitionService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getAllCompetitions(): Observable<Competition[]> {
    return this.http.get<Competition[]>(`${this.apiUrl}/competitions/all`);
  }

  createCompetition(competition: Competition): Observable<Competition> {
    return this.http.post<Competition>(`${this.apiUrl}/competitions`, competition);
  }

  addMemberToCompetition(competitionId: number, memberId: number): Observable<any> {
    const url = `${this.apiUrl}/competitions/add-member/${competitionId}/${memberId}`;
    
    return this.http.post(url, {}, { responseType: 'text' });
  }
  
  getAllPageCompetitions(page: number, size: number): Observable<CompetitionPage> {
    const params = new HttpParams().set('page', page.toString()).set('size', size.toString());
    return this.http.get<CompetitionPage>(`${this.apiUrl}/competitions/paged`, { params });
  }

}