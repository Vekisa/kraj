import {Component, OnInit, Input, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {PlanService} from "../services/plan.service";
import {Plan, PriceSchedule, Unit} from "../model";
import {UnitService} from "../services/unit.service";
import {ActivatedRoute} from '@angular/router';
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-new-plan',
  templateUrl: './new-plan.component.html',
  styleUrls: ['./new-plan.component.css']
})
export class NewPlanComponent implements OnInit {
  @Input() id: any;

  newPlanForm: FormGroup;
  plan: Plan;
  fS: number;

  dateNow: NgbDate;
  dateOneYear: NgbDate;
  dateN: String;
  dateOY: String;
  priceSchedule: PriceSchedule;
  unit: Unit;
  plans: Plan[]=[];
  readonlyP: boolean;
  brojUListi: number;
  fromDate: NgbDate;
  toDate: NgbDate;
  constructor(private formBuilder: FormBuilder, private planService: PlanService, private unitService: UnitService, private activatedRoute: ActivatedRoute, private calendar: NgbCalendar) {

  }

  ngOnInit() {
    this.newPlanForm=this.formBuilder.group({
      fromDate: null,
      toDate: null,
      price: [''],
      perPerson: ['']

    })
   /* this.dateNow=new Date();
    this.dateN=this.dateNow.getFullYear().toString() + '-'+this.dateNow.getMonth().toString()+ '-'+this.dateNow.getDate().toString();
    this.dateOneYear=this.dateNow;
    this.dateOneYear.setFullYear(this.dateNow.getFullYear()+1, this.dateNow.getMonth(), this.dateNow.getDate());
    this.dateNow.setFullYear(this.dateNow.getFullYear()-1, this.dateNow.getMonth(), this.dateNow.getDate())
    this.dateOY=this.dateOneYear.getFullYear().toString()+ '-'+this.dateOneYear.getMonth().toString()+ '-'+this.dateOneYear.getDate().toString();
    */
   this.priceSchedule=new PriceSchedule();
    //this.newPlanForm.controls['fromDate'].setValue(this.dateNow.toISOString().substring(0,10));
    this.unit=new Unit();

    this.activatedRoute.params.subscribe(data=>{
      console.log(data['id']);
      this.unitService.findById(data['id']).subscribe(data=>{
        this.unit=data;
        console.log(data);
      })
    });
    this.fS=1;
    this.readonlyP=false;
    this.brojUListi=0;
    this.fromDate = this.calendar.getToday();
    this.newPlanForm.controls['fromDate'].setValue(this.fromDate);
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);
    this.dateOneYear=this.calendar.getNext(this.calendar.getToday(), 'd', 365);
    this.newPlanForm.controls['toDate'].setValue(this.dateOneYear);
    this.dateNow = this.calendar.getToday();
  }



  buttonR(broj: number) {
    this.fS = broj;
  }

  onSubmitYear(){
    this.priceSchedule=new PriceSchedule();
    this.priceSchedule.plan=[];
    this.priceSchedule.made=new Date();
    this.plan=this.newPlanForm.value;
    this.plan.fromDate=new Date(this.dateNow.year, this.dateNow.month, this.dateNow.day, 0,0,0,0);
    this.plan.toDate=new Date(this.dateOneYear.year, this.dateOneYear.month, this.dateOneYear.day,0,0,0);
    console.log("ovde ");
    console.log(this.plan);
    this.priceSchedule.plan.push(this.plan);
    //this.planService.newPlan(this.plan).subscribe(data=> {console.log(data)})
    this.planService.newPlanList(this.priceSchedule).subscribe(data=> {
      this.unit.priceSchedule.push(data);
      console.log(data);
      this.unitService.updateUnit(data, this.unit.id).subscribe(data=>
        console.log(data));
    });

  }
  onSubmitCustom(){
    if((new Date(this.plans[this.plans.length-1].toDate).getTime()-new Date(this.plans[0].fromDate).getTime())/(1000*60*60*24.0)<365){
      console.log("greska, manje od godine");
    }else {
      this.priceSchedule.plan = this.plans;
      this.priceSchedule.made = new Date();
      this.planService.newPlanList(this.priceSchedule).subscribe(data=> {
        this.unit.priceSchedule.push(data);
        console.log(data);
        this.unitService.updateUnit(data, this.unit.id).subscribe(data=>
        console.log(data));
      });
    }
  }

  addFnc(){
    const fromDate = this.newPlanForm.get('fromDate').value;
    const toDate = this.newPlanForm.get('toDate').value;
    this.toDate=this.newPlanForm.get('toDate').value;

    const fromDateDate = new Date(fromDate.year, fromDate.month , fromDate.day, 0, 0, 0);
    const toDateDate = new Date(toDate.year, toDate.month , toDate.day, 0, 0, 0);
    this.newPlanForm.patchValue({
      from: fromDateDate,
      to: toDateDate
    });
    let p: Plan;
    p=this.newPlanForm.value;
    p.toDate=toDateDate;
    p.fromDate=fromDateDate;
    this.plans.push(p);
    console.log("Planovi ");
    console.log(this.plans);
    const pom=this.calendar.getNext(this.toDate, 'd',1);


    this.newPlanForm.controls['fromDate'].setValue(pom);

    this.readonlyP=false;
  }

  editPrice(i: number){
    console.log(i);
    this.brojUListi=i;
    let pom: Plan=new Plan();
    pom=this.plans[i];
    this.newPlanForm.controls['fromDate'].setValue(new Date(pom.fromDate));
    this.newPlanForm.controls['toDate'].setValue(new Date(pom.toDate));
    this.newPlanForm.controls['price'].setValue(pom.price);
    this.newPlanForm.controls['perPerson'].setValue(false);
    if(pom.perPerson){
      this.newPlanForm.controls['perPerson'].setValue(true);
    }
    this.readonlyP=true;
  }

  editFnc(){
    this.plans[this.brojUListi].perPerson=this.newPlanForm.value.perPerson;
    this.plans[this.brojUListi].price=this.newPlanForm.value.price;
    this.readonlyP=false;
    this.newPlanForm.controls['fromDate'].setValue(this.plans[this.plans.length-1].toDate);
  }

  onDateSelected() {

    console.log('Promena');

    this.toDate = this.calendar.getNext(this.newPlanForm.get('fromDate').value, 'd', 1);

    this.newPlanForm.patchValue({
      endDate: this.toDate

    });

  }
}
