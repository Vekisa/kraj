import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NewUnitComponent} from "./new-unit/new-unit.component";

import {UnitComponent} from "./unit/unit.component";
import {NewObjectComponent} from "./new-object/new-object.component";
import {NewPlanComponent} from "./new-plan/new-plan.component";
import {ObjectComponent} from "./object/object.component";
import {NewReservationComponent} from "./new-reservation/new-reservation.component";

const routes: Routes = [
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
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
