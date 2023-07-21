import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IToken } from 'src/app/core/interfaces/IToken';


@Injectable({
  providedIn: 'root' 
})
export class AuthService {

  private baseURL = "api"

  constructor(private http: HttpClient) { }

  registerUser(userRequest:any){
    return  this.http.post<IToken>(this.baseURL+'/auth/register', userRequest)
  }

  loginUser(userRequest:any){
    return  this.http.post<IToken>(this.baseURL+'/auth/login', userRequest)
  }

  

  //* used in the auth gard to controlled navigation
  isLoggedIn(){
    return !!localStorage.getItem('token')
  }

  //interceptor
  getToken(){
    return localStorage.getItem('token')

  }
}

/*
the auth service contain the method for:
  -logging and
  -registration
 */