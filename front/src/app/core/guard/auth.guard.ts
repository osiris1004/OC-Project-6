import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
 

  constructor(private _authService : AuthService, private _router : Router){}
  canActivate(): boolean {
    if(this._authService.isLoggedIn()){
      return true; // continue navigating 
    }else {
      this._router.navigate(['/auth/login']) 
      return false;
    }
  }
  
}
/**
 * 
 * peace of code that control navifation to and from component
 *  -it reture true in which case the normal execution continues (retrun true of token is present in browser)
 * - it retur false in which case tht navigation is stoped (retrun true of token is not present in browser)
 */