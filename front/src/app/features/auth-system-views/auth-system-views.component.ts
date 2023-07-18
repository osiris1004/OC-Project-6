import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-auth-system-views',
  templateUrl: './auth-system-views.component.html',
  styleUrls: ['./auth-system-views.component.scss']
})
export class AuthSystemViewsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public view :"registration" | "login" | "article" | "profile" = "login"
}
