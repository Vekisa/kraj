import { Component, OnInit } from '@angular/core';
import {Reservation} from "../../../../frontAgent/src/app/model";
import {ReservationService} from "../services/reservation.service";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {
  reservations : Reservation[];
  constructor(private reservationService : ReservationService) { }

  ngOnInit() {
    this.reservationService.getAllForUser().subscribe(data =>{
      this.reservations = data;
    });
  }

}
