import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PageComponent } from './page/page.component';
import { SearchComponent } from './search/search.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import {CookieService} from 'ngx-cookie-service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './interceptor/authinterceptor.interceptor';
import { HomeComponent } from './home/home.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { ProfileComponent } from './profile/profile.component';
import { AccountComponent } from './account/account.component';
import { ObjectsComponent } from './objects/objects.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { ReservedComponent } from './reserved/reserved.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PageComponent,
    SearchComponent,
    ReservationsComponent,
    LoginComponent,
    SignupComponent,
    HomeComponent,
    ProfileComponent,
    AccountComponent,
    ObjectsComponent,
    ReservedComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgMultiSelectDropDownModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule.forRoot()
  ],
  providers: [
    CookieService,
    [
      { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
    ]
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
