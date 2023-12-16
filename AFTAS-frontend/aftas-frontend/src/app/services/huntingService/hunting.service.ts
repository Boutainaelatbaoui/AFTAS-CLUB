import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hunting } from 'src/app/models/hunting';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class HuntingService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  createHunting(huntingData: Hunting): Observable<Hunting> {
    return this.http.post<Hunting>(`${this.apiUrl}/huntings`, huntingData);
  }

  getAllHuntings(): Observable<Hunting[]> {
    return this.http.get<Hunting[]>(`${this.apiUrl}/huntings/all`);
  }
}
