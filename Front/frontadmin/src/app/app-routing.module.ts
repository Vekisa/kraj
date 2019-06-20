import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NavigationComponent} from "./navigation/navigation.component";
import {ObjectTypesComponent} from "./object-types/object-types.component";
import {AccommodationTypesComponent} from "./accommodation-types/accommodation-types.component";
import {ExtraOptionsComponent} from "./extra-options/extra-options.component";
import {CommentsComponent} from "./comments/comments.component";
import {UsersComponent} from "./users/users.component";
import {AgentsComponent} from "./agents/agents.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";

const routes: Routes = [
  {
    path: '',
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
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
