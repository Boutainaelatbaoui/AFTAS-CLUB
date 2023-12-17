import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ranking } from 'src/app/models/ranking';
import { environment } from 'src/environments/environment';

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
}
