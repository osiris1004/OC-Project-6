import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IArticle } from 'src/app/core/interfaces/IArticle';
import { IUser } from 'src/app/core/interfaces/IUser';
import { IRequestUser } from 'src/app/core/interfaces/RequestUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseURL = "api"+"/auth/" //"http://localhost:3001"
  constructor(private http: HttpClient) { }

  get() {
    return this.http.get<IUser>(this.baseURL + 'user')
  }

  update(request :IRequestUser){
    return this.http.post<IUser>(this.baseURL + 'user', request)
  }
}
