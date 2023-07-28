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

   isShown = false;
   drawerClass = ".drawer-hidden"
  ngOnInit(){}
  
  setWidth(widthNumber: number){
    this.width = widthNumber;
    this.cdr.detectChanges();
  }

  @Input("targetView")
  public view!: "other" | "profile" | "no-back-button"

  isDrawerDisplayed(){
    this.isShown = true
  }

  goBack(){
    this._location.back()
  }
  navToArticle(){
    this.isShown = false
    this._router.navigate(["board","article"])
  }
  navToTheme(){
    console.log(1)
    this.isShown = false
    this._router.navigate(["board","theme"])
  }
  navToProfile(){
    this.isShown = false
    this._router.navigate(["profile"])
  }

}
