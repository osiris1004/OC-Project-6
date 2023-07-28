import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IUser } from 'src/app/core/interfaces/IUser';
import { ThemeService } from 'src/app/services/theme/theme.service';
import { UserService } from 'src/app/services/user/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


   user! : IUser;
  constructor(
    private _userService : UserService,
    private _themeService : ThemeService,
    private _snackBar: MatSnackBar
    ) { }
  ngOnInit(): void {

    this._userService.get().subscribe(response =>  {
      this.user = response
      console.log(this.user)
    } ,error => console.log(error)) 
  }

  unSubscribe(themeId:number){
    this._themeService.deleteThemeInUser(themeId, this.user.id).subscribe(
      response => {
        console.log(response)
        this._snackBar.open('you successfully unsubscribe to the Theme', '....', {duration: 3000});
        this.ngOnInit()
      },
      error => console.log(error),
    ) 
  }

}
