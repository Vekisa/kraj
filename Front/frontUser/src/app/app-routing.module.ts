import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {PageComponent} from './page/page.component';
import {NavbarComponent} from './navbar/navbar.component';
import {SearchComponent} from './search/search.component';
import {ReservationsComponent} from './reservations/reservations.component';
import {LoginComponent} from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {HomeComponent} from './home/home.component';
import {ProfileComponent} from './profile/profile.component';
import {AccountComponent} from './account/account.component';
import { ObjectsComponent } from './objects/objects.component';
import { ReservedComponent } from './reserved/reserved.component';


const routes: Routes = [


  {
    path: '', component: HomeComponent, children: [
      {
        path: 'page', component: PageComponent
      },
      {
        path: 'search', component: SearchComponent
      },
      {
        path: 'reservations', component: ReservationsComponent
      },
      {
        path: 'profile', component: ProfileComponent
      },
      {
        path: 'account', component: AccountComponent
      },
      {
        path: 'objects/:id', component: ObjectsComponent
      },
      {
        path: 'reserved/:id', component: ReservedComponent
      }
    ]
  },
  {
    path: 'sign_in',
    component: LoginComponent
  },
  {
    path: 'sign_up',
    component: SignupComponent
  },
  {
    path: 'reservations',
    component: ReservationsComponent
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
