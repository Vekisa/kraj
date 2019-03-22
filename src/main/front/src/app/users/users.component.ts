import { Component, OnInit } from '@angular/core';
import {User} from "../model";
import {CertificateService} from "../service/certificate.service";
import {FormBuilder} from "@angular/forms";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[];
  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {

    this.certificateService.allUsers().subscribe(data=>{
      this.users = data;

    })
  }

  enable(id : number){
    this.certificateService.enable(id);
    this.certificateService.allUsers().subscribe(data=>{
      this.users = data;
    })
  }

  disable(id: number){
    this.certificateService.disable(id);
    this.certificateService.allUsers().subscribe(data=>{
      this.users = data;
    })
  }

}
