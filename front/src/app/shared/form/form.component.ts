import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  formData!: FormGroup;
 
  public dynamicData!: any;
  @Input("targetView")
  public view!: "registration" | "login" | "article" | "profile" 
  
  constructor(private _router:Router) {}
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
      this.formData = new FormGroup({
        theme: new FormControl(''),
        password: new FormControl('')
      });
    }

    if (this.view === "profile") {
      this.dynamicData = this.fields("profile");
      this.formData = new FormGroup({
        name: new FormControl(''),
        article: new FormControl(''),
        content: new FormControl('')
      });
    }

  }


  submitForm(form: FormGroup) {

    if(this.view === "registration"){
      console.log(form)
      console.log(1)
    }
    if(this.view === "login"){
      console.log(form)
      console.log(2)
      this._router.navigate(["board","article"])
    }
    if(this.view === "article"){
      console.log(form)
      console.log(2)
    }
    if(this.view === "profile"){
      console.log(form)
      console.log(1)
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
            name: "theme",
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
