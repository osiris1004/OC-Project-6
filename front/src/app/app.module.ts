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
import { FormComponent } from './form/form.component';


@NgModule({
  declarations: [AppComponent, HomeComponent, HeaderComponent, AuthSystemViewsComponent, FormComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule  
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
