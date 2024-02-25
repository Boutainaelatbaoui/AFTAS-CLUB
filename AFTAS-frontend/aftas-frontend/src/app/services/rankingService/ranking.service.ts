import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ranking } from 'src/app/models/ranking';
import { environment } from 'src/environments/environment';
import { Competition } from 'src/app/models/competition';

@Injectable({
  providedIn: 'root',
})
export class RankingService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getAllRankings(): Observable<Ranking[]> {
    return this.http.get<Ranking[]>(`${this.apiUrl}/rankings/all`);
  }

  getRankingsForCompetition(competitionId: number): Observable<Ranking[]> {
    return this.http.get<Ranking[]>(`${this.apiUrl}/rankings/${competitionId}`);
  }

  getTopRankingsForCompetition(competitionId: number): Observable<Ranking[]> {
    const url = `${this.apiUrl}/rankings/competition/${competitionId}`;
    return this.http.get<Ranking[]>(url);
  }

  getMemberCompetitions(memberId: number): Observable<Competition[]> {
    return this.http.get<Competition[]>(`${this.apiUrl}/rankings/member/${memberId}`);
  }
}
