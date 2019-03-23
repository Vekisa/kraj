import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { RegistrationComponent } from './registration/registration.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { NavigationComponent } from './navigation/navigation.component';

import { httpInterceptorProviders } from './authentication/interceptor';
import {HttpClientModule} from "@angular/common/http";
import { HomeComponent } from './home/home.component';
import { NewCertificateComponent } from './new-certificate/new-certificate.component';
import { RevokeComponent } from './revoke/revoke.component';
import { UsersComponent } from './users/users.component';
import {FilterPipe} from "./additional/filter.pipe";
import { CommunicationComponent } from './communication/communication.component';
import { ValidateComponent } from './validate/validate.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    WelcomeComponent,
    NavigationComponent,
    HomeComponent,
    NewCertificateComponent,
    RevokeComponent,
    UsersComponent,
    FilterPipe,
    CommunicationComponent,
    ValidateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
