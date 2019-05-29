import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RegistrationComponent} from './registration/registration.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {NavigationComponent} from './navigation/navigation.component';

import {httpInterceptorProviders} from './authentication/interceptor';
import {HttpClientModule} from "@angular/common/http";
import {HomeComponent} from './home/home.component';
import {NewCertificateComponent} from './new-certificate/new-certificate.component';
import {RevokeComponent} from './revoke/revoke.component';
import {UsersComponent} from './user/users/users.component';
import {FilterPipe} from "./additional/filter.pipe";
import {CommunicationComponent} from './communication/communication.component';
import {ValidateComponent} from './validate/validate.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {MessageComponent} from './message/message.component';
import {RolepointComponent} from './user/rolepoint/rolepoint.component';

import {UserPanelComponent} from './user/user-panel/user-panel.component';
import {CertificatePanelComponent} from './certificate/certificate-panel/certificate-panel.component';
import {GroupComponent} from './user/group/group.component';
import {UserRolesComponent} from './user/user-roles/user-roles.component';


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
    ValidateComponent,
    MessageComponent,
    RolepointComponent,
    UserPanelComponent,
    CertificatePanelComponent,
    GroupComponent,
    UserRolesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
