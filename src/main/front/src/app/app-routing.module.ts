import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {HomeComponent} from "./home/home.component";
import {NewCertificateComponent} from "./new-certificate/new-certificate.component";
import {UsersComponent} from "./users/users.component";
import {RevokeComponent } from "./revoke/revoke.component";

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
    path: 'certificate_new',
    component: NewCertificateComponent
  },
  {
    path: 'revoke',
    component: RevokeComponent
  },
  {
    path: 'users',
    component: UsersComponent
  }

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
