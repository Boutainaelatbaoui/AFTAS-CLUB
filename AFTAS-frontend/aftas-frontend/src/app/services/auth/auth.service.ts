import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap, map } from 'rxjs/operators';
import { User } from 'src/app/models/user';
import { StorageService } from '../storage/storage.service';
import { AuthResponseData } from 'src/app/models/auth-response-data';
import { environment } from 'src/environments/environment';
import { Registration } from 'src/app/models/registration';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = environment.apiUrl;

  constructor(
    private http: HttpClient,
    private storageService: StorageService,
    private router: Router
  ) {}

  private handleAuthentication(response: AuthResponseData): User {
    const user: User = {
      email: response.email,
      id: response.id,
      token: response.access_token,
      refreshToken: response.refresh_token

    };
    this.storageService.saveUser(user);
    console.log(user);
    return user;
  }

  login(email: string, password: string) {
    return this.http
      .post<AuthResponseData>(
        `${this.apiUrl}/v1/auth/login`,
        { email, password },
      )
      .pipe(
        catchError(error => {
          if (error.status === 401) {
            Swal.fire('Error!', 'Your Account is Not Activated Yet !!!', 'error');
          } 
          return throwError(error);
        }),
        tap((response) => this.handleAuthentication(response))
      );
  }
  

  register(registerData: Registration) {
    return this.http
      .post<AuthResponseData>(
        `${this.apiUrl}/v1/auth/register`,
        registerData,
      )
      .pipe(
        tap((response) => this.handleAuthentication(response))
      );
  }

  logout() {
    this.http
      .post(`${this.apiUrl}/v1/auth/logout`, null)
      .subscribe({
        next: () => {
          this.storageService.clean();
          this.router.navigate(['/login']);
        },
      });
  }

  refreshAccessToken(): Observable<any> {
    const refreshToken = this.storageService.getSavedUser()?.refreshToken;
    console.log(refreshToken);

    return this.http.post<{ access_token: string, refresh_token: string }>(`${this.apiUrl}/v1/auth/refreshToken`, { refreshToken })
      .pipe(
        catchError(error => {
          console.error('Failed to refresh access token', error);
          return throwError(error);
        }),
        map(response => {
          this.storageService.saveAccessToken(response.access_token);
          return response.access_token;
        })
      );
}
}
