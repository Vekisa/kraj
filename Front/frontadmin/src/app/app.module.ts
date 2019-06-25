import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { ObjectTypesComponent } from './object-types/object-types.component';
import { AccommodationTypesComponent } from './accommodation-types/accommodation-types.component';
import { ExtraOptionsComponent } from './extra-options/extra-options.component';
import { CommentsComponent } from './comments/comments.component';
import { UsersComponent } from './users/users.component';
import { AgentsComponent } from './agents/agents.component';
import {AppRoutingModule} from "./app-routing.module";
import { HttpClientModule ,HTTP_INTERCEPTORS} from '@angular/common/http';
import { AuthInterceptor } from './interceptor/authinterceptor.interceptor'
import { FormsModule, ReactiveFormsModule }         from '@angular/forms';
import { LoginComponent } from './login/login.component';

import { HomeComponent } from './home/home.component';
import {CookieService} from "ngx-cookie-service";

import {AuthGuard} from "./security/auth.guard";
import { PanelComponent } from './panel/panel.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    ObjectTypesComponent,
    AccommodationTypesComponent,
    ExtraOptionsComponent,
    CommentsComponent,
    UsersComponent,
    AgentsComponent,
    LoginComponent,
    HomeComponent,
    PanelComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [

    CookieService,

      [
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
      ],

    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
