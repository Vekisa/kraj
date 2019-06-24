import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NewUnitComponent} from "./new-unit/new-unit.component";

import {UnitComponent} from "./unit/unit.component";
import {NewObjectComponent} from "./new-object/new-object.component";
import {NewPlanComponent} from "./new-plan/new-plan.component";
import {ObjectComponent} from "./object/object.component";
import {NewReservationComponent} from "./new-reservation/new-reservation.component";
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {InboxComponent} from "./inbox/inbox.component";
import {MessageComponent} from "./message/message.component";

const routes: Routes = [
  {
    path: '',redirectTo:'home',pathMatch:'full'
  },
  {
    path:'home', component: HomeComponent,
    children:[
      {
        path:'newUnit',
        component: NewUnitComponent
      },
      {
        path:'showUnits/:id',
        component: UnitComponent
      },
      {
        path:'showObjects',
        component: ObjectComponent
      },
      {
        path:'newObject',
        component: NewObjectComponent
      },
      {
        path:'newPlan/:id',
        component: NewPlanComponent
      },
      {
        path:'createReservation/:id',
        component: NewReservationComponent
      },
      {
        path:'inbox',
        component: InboxComponent
      },
      {
        path:'readMessages/:id',
        component: MessageComponent
      }
    ]
  },
  {
    path:'sign_in',component:LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
