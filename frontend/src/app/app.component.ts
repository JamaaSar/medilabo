import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';
import { User } from 'src/model/user';
import { CookieService } from './service/cookie.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  username: string;

  constructor(private cookieService: CookieService) {
    this.username = cookieService.getCookie('username')!;
  }
}
