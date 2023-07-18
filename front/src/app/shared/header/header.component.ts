import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  private width: number | undefined;
  constructor(private cdr: ChangeDetectorRef){}
  ngOnInit(){
  }
  
  setWidth(widthNumber: number){
    this.width = widthNumber;
    this.cdr.detectChanges();
  }

  @Input("targetView")
  public view!: "other" | "profile"

}
