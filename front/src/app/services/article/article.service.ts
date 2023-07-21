import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IArticle } from 'src/app/core/interfaces/IArticle';
import { IRequestArticle } from 'src/app/core/interfaces/ResquestArticle';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private baseURL = "api/"
  constructor(private http: HttpClient) { }

  get() {
    return this.http.get<IArticle[]>(this.baseURL + 'articles')
  }

  getById(id:number){
    return this.http.get<IArticle>(this.baseURL + 'article/'+ id)
  }

  create(requestArticle:IRequestArticle){
    return this.http.post<IArticle>(this.baseURL + 'article', requestArticle)
  }
}
