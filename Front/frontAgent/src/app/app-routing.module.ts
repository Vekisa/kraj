import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NewUnitComponent} from "./new-unit/new-unit.component";

import {UnitComponent} from "./unit/unit.component";
import {NewObjectComponent} from "./new-object/new-object.component";
import {NewPlanComponent} from "./new-plan/new-plan.component";

const routes: Routes = [
  {
    path:'newUnit',
    component: NewUnitComponent
  },
  {
    path:'showUnits',
    component: UnitComponent
  },
  {
    path:'newObject',
    component: NewObjectComponent
  },
  {
    path:'newPlan',
    component: NewPlanComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
