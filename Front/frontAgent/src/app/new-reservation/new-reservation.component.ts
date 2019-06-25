import {Component, Input, OnInit} from '@angular/core';
import {UnitService} from "../services/unit.service";
import {ActivatedRoute} from '@angular/router';
import {Reservation, Unit} from "../model";
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {ReservationService} from "../services/reservation.service";
@Component({
  selector: 'app-new-reservation',
  templateUrl: './new-reservation.component.html',
  styleUrls: ['./new-reservation.component.css']
})
export class NewReservationComponent implements OnInit {
  @Input() id: any;
  unit: Unit;
  newReservationForm: FormGroup;
  priceUk: number;
  reservation: Reservation;
  price: number;
  constructor(private formBuilder: FormBuilder, private unitService: UnitService, private activatedRoute: ActivatedRoute, private reservationService: ReservationService) { }

  ngOnInit() {
    this.price=-1;
    this.newReservationForm=this.formBuilder.group({
      start: [''],
      end:['']
    })

    this.activatedRoute.params.subscribe(data=>{
      console.log(data['id']);
      this.unitService.findById(data['id']).subscribe(data=>
        this.unit=data)
    });
    this.reservation=new Reservation();
  }

  checkReservation(){
    this.reservation.unit=this.unit;
    this.reservation.start=this.newReservationForm.value.start;
    this.reservation.end=this.newReservationForm.value.end;
    this.reservationService.checkReservation(this.reservation).subscribe(data=>
    { this.price=data;
    console.log(data)})
  }

  /*calPrice(){
    let pS=this.unit.priceSchedule;
    for(let i=0; i<pS.length; i++){
      if(new Date(pS[i].plan[0].from).getTime()<=new Date(this.newReservationForm.value.start).getTime() && new Date(pS[i].plan[pS[i].plan.length-1].to).getTime()>= new Date(this.newReservationForm.value.end).getTime()){
        for(let j=0; j<pS[i].plan.length; j++){
          if(new Date(pS[i].plan[j].from).getTime()<=new Date(this.newReservationForm.value.start).getTime() && new Date(pS[i].plan[j].to).getTime()>=new Date(this.newReservationForm.value.end).getTime()){
            if(pS[i].plan[j].perPerson){
              return (pS[i].plan[j].price*this.unit.adults+ pS[i].plan[j].price*this.unit.children)*(new Date(this.newReservationForm.value.end).getTime() -new Date(this.newReservationForm.value.start).getTime() )/(1000*60*60*24.0);
            }else{
              return pS[i].plan[j].price*(new Date(this.newReservationForm.value.end).getTime() -new Date(this.newReservationForm.value.start).getTime() )/(1000*60*60*24.0);
            }
          }
        }
      }
    }
  }*/

  createReservation(){
    this.reservation.price=this.price;
    console.log("saljem ");
    console.log(this.reservation);
    this.reservation.possibleCancellationDate=new Date(new Date(this.reservation.start).getTime()- this.reservation.unit.object.cancellation*(1000*60*60*24.0) );
    this.reservationService.createReservation(this.reservation).subscribe(data=>
    console.log(data))
  }

}
