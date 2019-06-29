import { Component, OnInit } from '@angular/core';
import {Message, Reservation} from "../../../../frontAgent/src/app/model";
import {ReservationService} from "../services/reservation.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BackendService} from "../services/backend.service";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {
  reservations : Reservation[];
  currentDate: Date;
  messageForm: FormGroup;
  message: Message;
  id : number;

  constructor(private reservationService : ReservationService, private router: Router,private formBuilder: FormBuilder, private backendService: BackendService) { }

  ngOnInit() {
    this.message = new Message();
    this.currentDate = new Date();

    this.messageForm = this.formBuilder.group({
      text: ['']
    });

    this.reservationService.getAllForUser().subscribe(data =>{
      this.reservations = data;
    });
  }

  showReservation(id: number){
    this.router.navigateByUrl('reserved/'+ id);
  }

  sendMessage(){

  }

  setId(id : number){
    this.id = id;
  }

  onSubmit(){
    this.message = this.messageForm.value.text;
    this.backendService.sendMessage(this.id,this.messageForm.value).subscribe(data=>{
      console.log(data);
    });
  }
}
