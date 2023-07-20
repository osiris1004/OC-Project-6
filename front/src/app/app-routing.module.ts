import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { AuthSystemViewsComponent } from './features/auth-system-views/auth-system-views.component';
import { BoardComponent } from './features/board/board.component';
import { ProfileComponent } from './features/profile/profile.component';


// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'auth/:view', component: AuthSystemViewsComponent },
  { path: 'board/:view', component: BoardComponent },
  { path: 'profile', component: ProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
