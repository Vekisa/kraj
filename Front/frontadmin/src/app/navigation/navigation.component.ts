import { Component, OnInit } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  isLoggedIn = false;
  username : string;

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
