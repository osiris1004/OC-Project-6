import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './features/home/home.component';
import { HeaderComponent } from './shared/header/header.component';
import { AuthSystemViewsComponent } from './features/auth-system-views/auth-system-views.component';
import { FormsModule, ReactiveFormsModule   } from '@angular/forms';
import { FormComponent } from './shared/form/form.component';
import { BoardComponent } from './features/board/board.component';
import { ProfileComponent } from './features/profile/profile.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { TokenInterceptorService } from './core/interceptor/token-interceptor.service';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSelectModule} from '@angular/material/select';

@NgModule({
  declarations: [AppComponent, HomeComponent, HeaderComponent, AuthSystemViewsComponent, FormComponent, BoardComponent, ProfileComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule ,
    HttpClientModule,
    MatProgressSpinnerModule,
    MatSelectModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptorService, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}

