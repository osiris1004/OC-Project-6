import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root' 
})
export class AuthService {

  private baseURL = "api"

  constructor(private http: HttpClient) { }

  registerUser(userRequest:any){
    return  this.http.post<any>(this.baseURL+'/auth/register', userRequest)
  }

  loginUser(userRequest:any){
    return  this.http.post<any>(this.baseURL+'/auth/login', userRequest)
  }
}

/*
the auth service contain the method for:
  -logging and
  -registration
 */