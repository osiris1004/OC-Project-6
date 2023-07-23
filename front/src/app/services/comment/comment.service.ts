import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IComment } from 'src/app/core/interfaces/IComment';
import { IRequestComment } from 'src/app/core/interfaces/ResquestComment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseURL = "api/"
  constructor(private http: HttpClient) { }

  addCommentInArticle(request: IRequestComment, articleId:number){
    return this.http.post<IComment>(this.baseURL + 'article/' +articleId+'/comment', request)
  }
}
