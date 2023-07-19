import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';

import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  private width: number | undefined;
  constructor(private cdr: ChangeDetectorRef, private _location:Location, private _router:Router){}

  ngOnInit(){
  }
  
  setWidth(widthNumber: number){
    this.width = widthNumber;
    this.cdr.detectChanges();
  }

  @Input("targetView")
  public view!: "other" | "profile"


  goBack(){
    this._location.back()
  }

  navToArticle(){
    this._router.navigate(["board","article"])
  }
  navToTheme(){
    this._router.navigate(["board","theme"])
  }
  navToProfile(){
    this._router.navigate(["board","profile"])
  }

}
