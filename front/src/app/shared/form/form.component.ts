import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IError } from 'src/app/core/interfaces/IError';
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
  themeList!: ITheme[];
  authorN!: string;
  respondError !: null | string


  public dynamicData!: any;
  @Input("targetView")
  public view!: "registration" | "login" | "article" | "profile"

  @Input("userInfo")
  public userData !: IUser

  @Output()
  public redirectByString = new EventEmitter()

  constructor(
    private _router: Router,
    private _authServices: AuthService,
    private _themeService: ThemeService,
    private _userService: UserService,
    private _articleService: ArticleService
  ) { }
  ngOnInit() {



    if (this.view === "registration") {
      localStorage.setItem('token', '')
      this.dynamicData = this.fields("registration");
      this.formData = new FormGroup({
        name: new FormControl('', [Validators.required]),
        email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
        password: new FormControl('', [Validators.required])
      });
    }

    if (this.view === "login") {
      localStorage.setItem('token', '')
      this.dynamicData = this.fields("login");
      this.formData = new FormGroup({
        email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
        password: new FormControl('', [Validators.required])
      });
    }

    if (this.view === "article") {
      this.dynamicData = this.fields("article");
      this._userService.get().subscribe(response => this.authorN = response.name, error => console.log(error))
      this._themeService.get().subscribe(response => this.themeList = response, error => console.log(error))
      this.formData = new FormGroup({
        articleThemes: new FormControl('', [Validators.required]),
        article: new FormControl('', [Validators.required]),
        content: new FormControl('', [Validators.required]),
        authorName: new FormControl('', [Validators.required]),
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

    if (!this.formData.valid) {
      this.respondError = "You need to fill your form before submitting "
      return
    }

    if (this.view === "registration") {
      this._authServices.registerUser(form).subscribe(
        response => {
          localStorage.setItem('token', response.token)
          this._router.navigate(["board", "article"])
        },
        (error: IError) => {
          console.log('Test error')
        },
      )
    }

    if (this.view === "login") {
      this._authServices.loginUser(form).subscribe(
        response => {
          localStorage.setItem('token', response.token)
          this._router.navigate(["board", "article"])
        },
        (error: IError) => {
          if (error.ok === false && error.status === 403) this.respondError = "The email or password is incorrect"
        },
      )
    }

    if (this.view === "article") {
      const format = form as unknown as IRequestArticle
      format.authorName = this.authorN
      this._articleService.create(format).subscribe(response => console.log(response), error => console.log(error))
      this.redirectByString.emit('article')
      this._router.navigateByUrl('/').then(() => {
        this._router.navigate(["board", "article"]);
      });;


    }
    if (this.view === "profile") {
      const format = form as unknown as IRequestUser
      this._userService.update(format).subscribe(response => console.log(response), error => console.log(error))
    }

  }


  fields(fields: "registration" | "login" | "article" | "profile") {
    if (fields === "registration") {
      return {
        title: " Registration",
        fields: [
          {
            label: "Name",
            name: "name",
            placeholder: "Type your name ...",
            error: "A name is required",
          },
          {
            label: "E-mail",
            name: "email",
            placeholder: "Type your email ...",
            error: "An email is required ",
            errorEmail: "Invalid email "
          },
          {
            label: "Password",
            name: "password",
            placeholder: "Type your password ....",
            error: "A password is required",
          }
        ]
      }
    }

    if (fields === "login") {
      return {
        title: "login",
        fields: [
          {
            label: "E-mail",
            name: "email",
            placeholder: "Type your email ...",
            error: "An email is required",
            errorEmail: "Invalid email "
          },
          {
            label: "Password",
            name: "password",
            placeholder: "Type your password ...",
            error: "A password is required"
          }
        ]
      }
    }

    if (fields === "article") {
      return {
        title: "Create an article",
        fields: [
          {
            label: "",
            name: "articleThemes",
            placeholder: "Select a theme ...",
            error: "A theme required"
          },
          {
            label: "",
            name: "article",
            placeholder: "Type a title ...",
            error: "A theme required"
          },

          {
            label: "",
            name: "content",
            placeholder: "Type a content description ...",
            error: "A theme required"
          }
        ]
      }
    }

    if (fields === "profile") {
      return {
        title: "User profile",
        fields: [
          {
            label: "",
            name: "name",
            placeholder: "Type your name ...",
            error: "A name is required",
          },
          {
            label: "",
            name: "email",
            placeholder: "Type your email ...",
            error: "An email is required",
            errorEmail: "Invalid email "
          },
        ]
      }
    }

    return;

  }


}
