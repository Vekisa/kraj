import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NewUnitComponent } from './new-unit/new-unit.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import { UnitComponent } from './unit/unit.component';
import { NewObjectComponent } from './new-object/new-object.component';
import { NewPlanComponent } from './new-plan/new-plan.component';
import { ObjectComponent } from './object/object.component';
import { NewReservationComponent } from './new-reservation/new-reservation.component';

@NgModule({
  declarations: [
    AppComponent,
    NewUnitComponent,
    UnitComponent,
    NewObjectComponent,
    NewPlanComponent,
    ObjectComponent,
    NewReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
