import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CookieService } from '../service/cookie.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss'],
})
export class SigninComponent implements OnInit {
  isLoginFailed = false;
  errorMessage = '';
  signinForm: any = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
    ]),
  });
  isClicked = false;

  constructor(
    private authService: AuthenticationService,
    public router: Router,
    private cookieService: CookieService
  ) {}

  get email() {
    return this.signinForm.get('email');
  }

  get password() {
    return this.signinForm.get('password');
  }
  ngOnInit() {}

  onLogin() {
    if (!this.signinForm) {
      this.isLoginFailed = true;
      return;
    }
    this.authService
      .login(
        this.signinForm.get('email').value,
        this.signinForm.get('password').value
      )
      .subscribe({
        next: (res) => {
          this.cookieService.setCookie('authToken', res.token, 1); // 1 hour
          this.cookieService.setCookie('username', res.username, 1); // 1 hour
          this.router.navigate(['/']).then(() => {
            window.location.reload();
          });
        },
        error: (error) => {
          this.isLoginFailed = true;
          this.errorMessage = error.error;
          console.error('There was an error!', error);
        },
      });
  }
}
