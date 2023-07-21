import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ITheme } from 'src/app/core/interfaces/ITheme';
import { IUser } from 'src/app/core/interfaces/IUser';
import { IRequestUser } from 'src/app/core/interfaces/RequestUser';
import { IRequestArticle } from 'src/app/core/interfaces/ResquestArticle';
import { ArticleService } from 'src/app/services/article/article.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { ThemeService } from 'src/app/services/theme/theme.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  formData!: FormGroup;
  themeList!: ITheme[] ;
  authorN!: string;

 
  public dynamicData!: any;
  @Input("targetView")
  public view!: "registration" | "login" | "article" | "profile" 

  @Input("userInfo")
  public userData !: IUser
  
  @Output()
  public redirectByString = new EventEmitter()
  
  constructor(
    private _router:Router, 
    private _authServices: AuthService,
    private _themeService : ThemeService,
    private _userService : UserService,
    private _articleService : ArticleService
    ) {}
  ngOnInit() {

    

    if (this.view === "registration") {
      this.dynamicData = this.fields("registration");
      this.formData = new FormGroup({
        name: new FormControl(''),
        email: new FormControl(''),
        password: new FormControl('')
      });

    }

    if (this.view === "login") {
      this.dynamicData = this.fields("login");
      this.formData = new FormGroup({
        email: new FormControl(''),
        password: new FormControl('')
      });
    }

    if (this.view === "article") {
      this.dynamicData = this.fields("article");
      this._userService.get().subscribe(response =>  this.authorN = response.name ,error => console.log(error)) 
      this._themeService.get().subscribe(response =>  this.themeList = response,error => console.log(error))  
      this.formData = new FormGroup({
        articleThemes: new FormControl(''),
        article: new FormControl(''),
        content: new FormControl(''),
        authorName : new FormControl(''),
      });
    }

    if (this.view === "profile") {
      this.dynamicData = this.fields("profile");
    
     
      this.formData = new FormGroup({
        name: new FormControl(this.userData.name),
        email: new FormControl(this.userData.email),
      });
    }

  }


  submitForm(form: FormGroup) {

    if(this.view === "registration"){
      this._authServices.registerUser(form).subscribe(
        response => {
          localStorage.setItem('token', response.token)
          this._router.navigate(["board","article"])
        },
        error => console.log(error),
      )
      console.log(form)
      console.log(1)
    }
    if(this.view === "login"){
      this._authServices.loginUser(form).subscribe(
        response => {
          localStorage.setItem('token', response.token)
          this._router.navigate(["board","article"])
        },
        error => console.log(error),
      )
    }
    if(this.view === "article"){
      const format = form as unknown as IRequestArticle
      format.authorName = this.authorN
      this._articleService.create(format).subscribe(response =>  console.log(response),error => console.log(error))
      this.redirectByString.emit('article')
      this._router.navigateByUrl('/').then(() => {
        this._router.navigate(["board","article"]);
    });;
      
     
    }
    if(this.view === "profile"){
      const format = form as unknown as IRequestUser
      this._userService.update(format).subscribe(response =>  console.log(response),error => console.log(error))
    }
   
  }


  fields(fields: "registration" | "login" | "article" | "profile") {
    if (fields === "registration") {
      return {
        title: " registration",
        fields: [
          {
            label: "user name",
            name: "name",
            placeholder: "user name"
          },
          {
            label: "e-mail",
            name: "email",
            placeholder: "email"
          },
          {
            label: "password",
            name: "password",
            placeholder: "password"
          }
        ]
      }
    }

    if (fields === "login") {
      return {
        title: "login",
        fields: [
          {
            label: "e-mail",
            name: "email",
            placeholder: "email"
          },
          {
            label: "password",
            name: "password",
            placeholder: "password"
          }
        ]
      }
    }

    if (fields === "article") {
      return {
        title: "create an article",
        fields: [
          {
            label: "",
            name: "articleThemes",
            placeholder: "select a theme"
          },
          {
            label: "",
            name: "article",
            placeholder: "article title"
          },

          {
            label: "",
            name: "content",
            placeholder: "article content"
          }
        ]
      }
    }

    if (fields === "profile") {
      return {
        title: "user profile",
        fields: [
          {
            label: "",
            name: "name",
            placeholder: "user name"
          },
          {
            label: "",
            name: "email",
            placeholder: "email"
          },
        ]
      }
    }

    return;

  }


}

