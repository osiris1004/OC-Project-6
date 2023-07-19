import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';


@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.scss']
})
export class BoardComponent implements OnInit {

  public viewHeader !: "other" | "profile"
  public view !: "article" | "theme" | "selectedArticle"

  constructor(private _route : ActivatedRoute, private _router :Router) {}
  ngOnInit(): void {
    this._route.paramMap.subscribe((param : ParamMap)=>{
      this.view = param.get('view')  as "article" | "theme" | "selectedArticle"
      this.viewHeader = "other"
    })
  }
  
}


