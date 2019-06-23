import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ObjectTypesComponent} from "./object-types/object-types.component";
import {AccommodationTypesComponent} from "./accommodation-types/accommodation-types.component";
import {ExtraOptionsComponent} from "./extra-options/extra-options.component";
import {CommentsComponent} from "./comments/comments.component";
import {UsersComponent} from "./users/users.component";
import {AgentsComponent} from "./agents/agents.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {SignupComponent} from "./signup/signup.component";
import {PanelComponent} from "./panel/panel.component";
import {AuthGuard} from "./security/auth.guard";

const routes: Routes = [
  {
    path: '',redirectTo:'panel',pathMatch:'full'
  },
  {

    path:'panel',component:PanelComponent,canActivate:[AuthGuard],data: { roles: ["ROLE_ADMIN"] },
    children:[
      {
        path: '',redirectTo:'home',pathMatch:'full'
      },
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'object_types',
        component: ObjectTypesComponent
      },
      {
        path: 'accommodation_types',
        component: AccommodationTypesComponent
      },
      {
        path: 'extra_options',
        component: ExtraOptionsComponent
      },
      {
        path: 'comments',
        component: CommentsComponent
      },
      {
        path: 'users',
        component: UsersComponent
      },
      {
        path: 'agents',
        component: AgentsComponent
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
