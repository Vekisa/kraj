import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {RegisteredUser} from "../model";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user:RegisteredUser;

  constructor(private authService:AuthService) { }

  ngOnInit() {
    this.authService.user().subscribe(data=>{
      this.user = data;
    });
  }

}
