import { Component } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { User } from 'src/model/user';
import { CookieService } from '../service/cookie.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  currentUser!: User;
  username: string;
  constructor(
    private authService: AuthenticationService,
    private cookieService: CookieService
  ) {
    this.username = cookieService.getCookie('username')!;
  }

  ngOnInit() {}

  logout(): void {
    this.authService.logout();
  }
}
