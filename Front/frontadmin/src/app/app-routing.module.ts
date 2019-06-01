import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NavigationComponent} from "./navigation/navigation.component";
import {ObjectTypesComponent} from "./object-types/object-types.component";

const routes: Routes = [
  {
    path: '',
    component: NavigationComponent
  },
  {
    path: 'object_types',
    component: ObjectTypesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
