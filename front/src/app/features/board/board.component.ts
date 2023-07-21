import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { IArticle } from 'src/app/core/interfaces/IArticle';
import { ITheme } from 'src/app/core/interfaces/ITheme';
import { ArticleService } from 'src/app/services/article/article.service';
import { ThemeService } from 'src/app/services/theme/theme.service';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  public viewHeader !: "other" | "profile" | "no-back-button"
  public view !: "article" | "theme" | "selectedArticle" | "addArticle"
  public viewArticleList! :IArticle[]
  public viewArticle! :IArticle
  public viewThemeList! :ITheme[]

  constructor(
    private _route : ActivatedRoute, 
    private _router :Router,
    private _articleService : ArticleService,
    private _themeService : ThemeService
    ) {}
  ngOnInit(): void {
    this._route.paramMap.subscribe((param : ParamMap)=>{
      this.view = param.get('id') ? "selectedArticle" : param.get('view')  as "article" | "theme" | "selectedArticle"
      
 

      if(this.view === "article"){
        this.viewHeader = "no-back-button"
        this._articleService.get().subscribe(
          response => this.viewArticleList = response,
          error => console.log(error),
        ) 
      }

      if(this.view === "theme"){
        this.viewHeader = "other"
        this._themeService.get().subscribe(
          response => {
            this.viewThemeList = response
            console.log(this.viewThemeList)
          },
          error => console.log(error),
        ) 
      }

      if(this.view === "selectedArticle" && param.get('id') ){
        this.viewHeader = "other"
        const id = param.get('id') as unknown as number
        this._articleService.getById(id).subscribe(
          response => {
            this.viewArticle = response
            console.log(this.viewArticle)
          },
          error => console.log(error),
        ) 
      }
    })
  }

  addArticle(){
    this.view = "addArticle"

  }

  viewSelectedArticle(item:IArticle){
    this._router.navigate(["board","article",item.id])
  }

  //!services
}


