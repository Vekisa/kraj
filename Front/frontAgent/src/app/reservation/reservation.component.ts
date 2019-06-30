import { Component, OnInit } from '@angular/core';
import {Reservation} from "../model";
import {ReservationService} from "../services/reservation.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  reservations: Reservation[]=[];
  canP: boolean;
  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservationService.getReservations().subscribe(data=> {
     console.log("data");
     console.log(data);
     this.reservations=data;
    })
    this.canP=true;
  }

  confirm(i: number){
      let res: Reservation=this.reservations[i];
      res.confirmed=true;
      this.reservationService.updateReservation(res).subscribe(data=>console.log(data));
  }

  cancelR(i: number){
    let res: Reservation=this.reservations[i];
    res.cancelled=true;
    this.reservationService.updateReservation(res).subscribe(data=>console.log(data));
  }

  calDateC(canD: Date){
    let pom=(new Date(canD).getTime()-new Date().getTime())/(1000*60*60*24.0);
    if(pom<0){
      this.canP=false;
      return "Late";
    }else{
      return Math.round(pom);
    }
  }

  getDate(date: Date){
    let d: Date= new Date(date);
    return d.getDate()+"-"+d.getMonth()+"-" + d.getFullYear();
  }
}
