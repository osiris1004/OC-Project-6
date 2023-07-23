import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IArticle } from 'src/app/core/interfaces/IArticle';
import { ITheme } from 'src/app/core/interfaces/ITheme';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private baseURL = "api/"
  constructor(private http: HttpClient) { }

  get() {
    return this.http.get<ITheme[]>(this.baseURL + 'themes')
  }

  saveThemeInUser(request: {id: number}, userId:number){
    return this.http.post<ITheme>(this.baseURL + 'user/' +userId+'/theme', request)
  }

  deleteThemeInUser(themeId:number, userId:number){
    return this.http.delete<ITheme>(this.baseURL + 'user/' +userId+'/theme/'+themeId)
  }
}
