import {Component, Input, OnInit} from '@angular/core';
import {Image, Plan, PriceSchedule, Unit} from "../model";
import {UnitService} from "../services/unit.service";
import {ActivatedRoute, Router} from '@angular/router';
import {ObjectService} from "../services/object.service";

@Component({
  selector: 'app-unit',
  templateUrl: './unit.component.html',
  styleUrls: ['./unit.component.css']
})
export class UnitComponent implements OnInit {
  @Input() id: any;
  units: Unit[]=[];
  plan: Plan;
  priceSchedule: PriceSchedule;
  planF: boolean;
  images: Image[]=[];
  constructor(private unitService: UnitService , private activatedRoute: ActivatedRoute, private objectService: ObjectService, private router: Router) { }

  ngOnInit() {


    this.activatedRoute.params.subscribe(data=>{
      console.log(data['id']);
      this.objectService.findUnits(data['id']).subscribe(data=>
      {this.units=data;
      console.log("unit");
      console.log(data.id);
      } )
    });
    this.plan=new Plan();

  }


  getPrice(pS: PriceSchedule[]){

    for(let i=0; i<pS.length; i++){
      if(new Date(pS[i].plan[0].from).getTime()<=new Date().getTime() && new Date(pS[i].plan[pS[i].plan.length-1].to).getTime()>= new Date().getTime()){
        for(let j=0; j<pS[i].plan.length; j++){
          if(new Date(pS[i].plan[j].from).getTime()<=new Date().getTime() && new Date(pS[i].plan[j].to).getTime()>=new Date().getTime()){
            console.log(pS[i].plan[j]);
            this.plan= pS[i].plan[j];
            this.planF=true;
            return this.plan.price;
          }
        }
      }
    }
    this.planF=false;
    return "Without price";
  }

  getPerPerson() {
    if (this.planF) {
      if (this.plan.perPerson)
        return "Per person";
      else
        return "Unit";
    } else{
      return "/"
    }
  }

  makeRes(i: number){
    let un: Unit;
    un= this.units[i];
    this.router.navigateByUrl('/home/createReservation/' + un.id);
  }


}
