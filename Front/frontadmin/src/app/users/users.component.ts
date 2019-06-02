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
      this.users = data;
    });
  }

  activate(id: number){
    this.userService.activate(id);
  }

  deactivate(id: number){
    this.userService.deactivate(id);
  }

  delete(id: number){
    this.userService.delete(id);
  }

}
