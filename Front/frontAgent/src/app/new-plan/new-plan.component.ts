import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {PlanService} from "../services/plan.service";
import {Plan, PriceSchedule} from "../model";


@Component({
  selector: 'app-new-plan',
  templateUrl: './new-plan.component.html',
  styleUrls: ['./new-plan.component.css']
})
export class NewPlanComponent implements OnInit {

  newPlanForm: FormGroup;
  plan: Plan;
  fS: number;

  dateNow: Date;
  dateOneYear: Date;
  dateN: String;
  dateOY: String;
  priceSchedule: PriceSchedule;

  plans: Plan[]=[];
  constructor(private formBuilder: FormBuilder, private planService: PlanService) { }

  ngOnInit() {
    this.newPlanForm=this.formBuilder.group({
      from: [new Date()],
      to: [''],
      price: [''],
      perPerson: [''],
      month: ['0']

    })
    this.dateNow=new Date();
    this.dateN=this.dateNow.getFullYear().toString() + '-'+this.dateNow.getMonth().toString()+ '-'+this.dateNow.getDate().toString();
    this.dateOneYear=this.dateNow;
    this.dateOneYear.setFullYear(this.dateNow.getFullYear()+1, this.dateNow.getMonth(), this.dateNow.getDate());
    this.dateOY=this.dateOneYear.getFullYear().toString()+ '-'+this.dateOneYear.getMonth().toString()+ '-'+this.dateOneYear.getDate().toString();
    this.priceSchedule=new PriceSchedule();
    this.newPlanForm.controls['from'].setValue(this.dateNow.toISOString().substring(0,10));
  }


  buttonR(broj: number) {
    this.fS = broj;
  }

  onSubmitYear(){
    this.plan=this.newPlanForm.value;
    this.plan.month=0;
    this.plan.from=new Date(this.dateNow.getFullYear()-1, this.dateNow.getMonth(), this.dateNow.getDate(), 0,0,0,0);
    this.plan.to=new Date(this.dateOneYear.getFullYear(), this.dateOneYear.getMonth(), this.dateOneYear.getDate(), 0,0,0,0);
    console.log("ovde ");
    console.log(this.plan);
    this.planService.newPlan(this.plan).subscribe(data=> console.log(data))

  }

  onSubmitMonth(){
    this.planService.newPlanList(this.newPlanForm.value).subscribe();

  }

  onSubmitCustom(){
    this.priceSchedule.plan=this.plans;
    this.priceSchedule.made=new Date();
    this.planService.newPlanList(this.priceSchedule).subscribe(data=> console.log(data));
  }

  addFnc(){
    console.log("uslo");
    this.plans.push(this.newPlanForm.value);
    console.log(this.plans);
    this.newPlanForm.controls['from'].setValue(this.newPlanForm.value.to);
  }

  clearF(){
    console.log("uslo ovde");
    this.newPlanForm.value.from=this.newPlanForm.value.to;
    this.newPlanForm.value.to='';
    this.newPlanForm.value.perPerson='';
    this.newPlanForm.value.price='';
  }
}
