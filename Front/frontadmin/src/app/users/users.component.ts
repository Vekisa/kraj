import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ObjectType, RegisteredUser} from "../model";
import {UsersService} from "../services/users.service";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users : RegisteredUser[];

  constructor(private userService: UsersService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.userService.allUsers().subscribe(data => {
      console.log(data);
      this.users = data;
      console.log("AKTIVAM: " + this.users[0].active);
    });
  }

  activate(id: number){
    this.userService.activate(id).subscribe(data=>{
      this.userService.allUsers().subscribe(data =>{
        this.users = data;
      });
    });
  }

  deactivate(id: number){
    this.userService.deactivate(id).subscribe(data=>{
      this.userService.allUsers().subscribe(data=>{
        this.users = data;
      })
    });
  }

  delete(id: number){
    this.userService.delete(id).subscribe(data=>{
      this.userService.allUsers().subscribe(data=>{
        this.users = data;
      })
    });
  }

}
