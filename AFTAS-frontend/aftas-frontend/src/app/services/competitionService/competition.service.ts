import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Competition } from 'src/app/models/competition';
import { environment } from 'src/environments/environment';

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
}