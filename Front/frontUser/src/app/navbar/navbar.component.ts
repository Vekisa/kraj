import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  navBarForm: FormGroup;
  username:string;

  isLoggedIn = false;

  constructor(private authService:AuthService,private route:Router) { }

  ngOnInit() {

    this.authService.roles().subscribe(data=>{
      console.log(data);
      this.username = data.user;
      this.isLoggedIn = true;
    },err=>{
      this.isLoggedIn = false;
    });

  }

  logout(){
    if (this.isLoggedIn){
      this.authService.logout();
      window.location.href = "[/home]";
    }
  }

}
