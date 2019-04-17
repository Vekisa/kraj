import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {HomeComponent} from "./home/home.component";
import {NewCertificateComponent} from "./new-certificate/new-certificate.component";
import {UsersComponent} from "./user/users/users.component";
import {RevokeComponent } from "./revoke/revoke.component";
import {CommunicationComponent} from "./communication/communication.component";
import {ValidateComponent} from "./validate/validate.component";
import {UserPanelComponent} from "./user/user-panel/user-panel.component";
import {RolepointComponent} from "./user/rolepoint/rolepoint.component";
import {CertificatePanelComponent} from "./certificate/certificate-panel/certificate-panel.component";

const routes: Routes = [
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'sign_in',
    component: LoginComponent
  },
  {
    path: 'sign_up',
    component: RegistrationComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'user_panel', component: UserPanelComponent,
    children: [
      { path: '', redirectTo: 'users', pathMatch: 'full'},
      { path: 'users', component: UsersComponent },
      { path: 'roles', component: RolepointComponent },
    ]
  },
  {
    path: 'certificate_panel', component: CertificatePanelComponent,
    children: [
      { path: '', redirectTo: 'all', pathMatch: 'full'},
      { path: 'all', component: NewCertificateComponent },
      { path: 'revoke', component: RevokeComponent },
      { path: 'validate', component: ValidateComponent },
      { path: 'communication', component: CommunicationComponent },
    ]
  }

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
