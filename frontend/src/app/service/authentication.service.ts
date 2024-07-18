import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from 'src/model/user';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { CookieService } from './cookie.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private username: string;
  user: User = new User();

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  constructor(
    private http: HttpClient,
    public router: Router,
    private cookieService: CookieService
  ) {
    this.username = cookieService.getCookie('username')!;
  }
  public get userName() {
    return this.username;
  }

  get isLoggedIn() {
    let authToken = this.cookieService.getCookie('authToken');
    return authToken !== null ? true : false;
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${environment.apiUrl}/user/${id}`, {
      headers: this.headers,
    });
  }

  getToken() {
    return this.cookieService.getCookie('authToken');
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<User>(
      `${environment.apiUrl}/auth/login`,
      { username, password },
      { headers: this.headers }
    );
  }

  logout() {
    this.cookieService.deleteCookie('authToken');
    this.cookieService.deleteCookie('username');
    this.router.navigate(['/signin']).then(() => {
      window.location.reload();
    });
  }

  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      msg = error.error.error;
    } else {
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }
}
