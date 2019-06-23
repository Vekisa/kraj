import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PageComponent } from './page/page.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SearchComponent } from './search/search.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { LoginComponent } from './login/login.component';
import {SignupComponent} from "./signup/signup.component";


const routes: Routes = [

{path: 'page',
component: PageComponent },
{path: 'megatravel',
component: NavbarComponent
},
{path: 'search',
component: SearchComponent
},
{path: 'reservations',
component: ReservationsComponent
},
{   path: 'sign_in',
    component: LoginComponent
  },
  {
    path: 'sign_up',
    component: SignupComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
