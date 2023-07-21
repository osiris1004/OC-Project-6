import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/services/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private _authService : AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){
    const tokenizedRequest = request.clone({
      setHeaders: {
        Authorization: `Bearer ${this._authService.getToken()?? ''}`,
      },
    });

    return next.handle(tokenizedRequest);
  }
}
/**
 * Interceptor is the mechanism to intercept a resquest or a respond and modify the HTTP request or response  understandable  including various functions.
 */
