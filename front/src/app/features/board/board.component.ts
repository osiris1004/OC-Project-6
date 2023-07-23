import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { IArticle } from 'src/app/core/interfaces/IArticle';
import { ITheme } from 'src/app/core/interfaces/ITheme';
import { IUser } from 'src/app/core/interfaces/IUser';
import { IRequestArticle } from 'src/app/core/interfaces/ResquestArticle';
import { IRequestComment } from 'src/app/core/interfaces/ResquestComment';
import { ArticleService } from 'src/app/services/article/article.service';
import { CommentService } from 'src/app/services/comment/comment.service';
import { ThemeService } from 'src/app/services/theme/theme.service';
import { UserService } from 'src/app/services/user/user.service';


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
  user! : IUser;
  formData!: FormGroup;
  authorN!: string;

  constructor(
    private _route : ActivatedRoute, 
    private _router :Router,
    private _articleService : ArticleService,
    private _themeService : ThemeService,
    private _userService : UserService,
    private _commentService :CommentService,
    private _snackBar: MatSnackBar
    ) {}
  ngOnInit(): void {
    this._route.paramMap.subscribe((param : ParamMap)=>{
      this.view = param.get('id') ? "selectedArticle" : param.get('view')  as "article" | "theme" | "selectedArticle"
      

      if(this.view === "article"){
       
        this.viewHeader = "no-back-button"
        this._articleService.get().subscribe(
          response => this.viewArticleList = response, error => error,
        ) 
      }

      if(this.view === "theme"){
        this.viewHeader = "other"
        this._themeService.get().subscribe(
          response => this.viewThemeList = response, error => error,
        ) 

        this._userService.get().subscribe(
          response =>  this.user = response ,error => error) 
      }

      if(this.view === "selectedArticle" && param.get('id') ){
        this.viewHeader = "other"
        const id = param.get('id') as unknown as number
        this._userService.get().subscribe(response =>  this.authorN = response.name ,error => error) 
        this._articleService.getById(id).subscribe(
          response => { this.viewArticle = response},
          error => error,
        ) 

        this.formData = new FormGroup({
          content: new FormControl(''),
        });
      }
    })

     
  }

  addArticle(){
    this.view = "addArticle"
  }

  viewSelectedArticle(item:IArticle){
    this._router.navigate(["board","article",item.id])
  }

  subscribe(themeId:number){
    const request = {id  : themeId }
    this._themeService.saveThemeInUser(request, this.user.id).subscribe(
      response => { this._snackBar.open('you successfully subscribe to the theme', '....', {duration: 3000});},
      error => error,
    ) 
  }

  sort(){
    
  }

  submitForm(form:FormGroup){
    const format = form as unknown as IRequestComment
    format.name = this.authorN
    this._commentService.addCommentInArticle(format, this.viewArticle.id).subscribe(
      response => {this.ngOnInit()},
      error => error,
    ) 
  }
  //!services
}


