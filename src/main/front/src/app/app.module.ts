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


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    WelcomeComponent,
    NavigationComponent,
    HomeComponent,
    NewCertificateComponent,
    RevokeComponent
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
