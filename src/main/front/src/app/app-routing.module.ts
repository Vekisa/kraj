import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {HomeComponent} from "./home/home.component";
import {NewCertificateComponent} from "./new-certificate/new-certificate.component";

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
  }

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
