import {Component, Input, OnInit} from '@angular/core';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute} from "@angular/router";
import {ReservationService} from "../services/reservation.service";
import {Reservation} from "../../../../frontAgent/src/app/model";


@Component({
  selector: 'app-reserved',
  templateUrl: './reserved.component.html',
  styleUrls: ['./reserved.component.css'],
  providers: [NgbRatingConfig]
})
export class ReservedComponent implements OnInit {
  @Input() id: number;
  currentRate = 1;
  reservation : Reservation;

  constructor(config: NgbRatingConfig, private activatedRoute: ActivatedRoute, private reservationService : ReservationService) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe(data => {
      this.reservationService.getReservation(data['id']).subscribe(data =>{
        console.log(data);
        this.reservation = data;
      })
    });


  }

}
