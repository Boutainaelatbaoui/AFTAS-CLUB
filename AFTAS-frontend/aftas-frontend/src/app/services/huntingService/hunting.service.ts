import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Hunting } from 'src/app/models/hunting';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class HuntingService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  createHunting(huntingData: Hunting): Observable<any> {
    return this.http.post(`${this.apiUrl}/huntings`, huntingData, { responseType: 'text' })
  }

  getAllHuntings(): Observable<Hunting[]> {
    return this.http.get<Hunting[]>(`${this.apiUrl}/huntings/all`);
  }

  getAllFishes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/fishes/all`);
  }
}
