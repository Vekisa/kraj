import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewUnitComponent } from './new-unit/new-unit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { UnitComponent } from './unit/unit.component';
import { NewObjectComponent } from './new-object/new-object.component';
import { NewPlanComponent } from './new-plan/new-plan.component';
import { ObjectComponent } from './object/object.component';
import { NewReservationComponent } from './new-reservation/new-reservation.component';
import { LoginComponent } from './login/login.component';
import {CookieService} from "ngx-cookie-service";
import {AuthInterceptor} from "./interceptor/authinterceptor.interceptor";
import { NavigationComponent } from './navigation/navigation.component';
import { HomeComponent } from './home/home.component';
import { FunctionNavigationComponent } from './function-navigation/function-navigation.component';
import { InboxComponent } from './inbox/inbox.component';
import { MessageComponent } from './message/message.component';
import { ReservationComponent } from './reservation/reservation.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  declarations: [
    AppComponent,
    NewUnitComponent,
    UnitComponent,
    NewObjectComponent,
    NewPlanComponent,
    ObjectComponent,
    NewReservationComponent,
    LoginComponent,
    NavigationComponent,
    HomeComponent,
    FunctionNavigationComponent,
    InboxComponent,
    MessageComponent,
    ReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [CookieService,

    [
      { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    ],

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
