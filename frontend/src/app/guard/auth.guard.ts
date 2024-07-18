import { Injectable } from '@angular/core';
import { Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard {
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  canActivate(
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (!this.authService.isLoggedIn) {
      this.router.navigate(['/signin'], {
        queryParams: { returnUrl: state.url },
      });
      return false;
    }
    // logged in, so return true
    return true;
  }
}