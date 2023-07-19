import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-auth-system-views',
  templateUrl: './auth-system-views.component.html',
  styleUrls: ['./auth-system-views.component.scss']
})
export class AuthSystemViewsComponent implements OnInit {

  public view !:"registration" | "login" | "article" | "profile"

  constructor(private _route : ActivatedRoute) {}
  ngOnInit(): void {
    this._route.paramMap.subscribe((param : ParamMap)=>{
      this.view = param.get('view')  as "registration" | "login" | "article" | "profile"
    })
  }
}
