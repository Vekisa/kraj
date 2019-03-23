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
  searchText: any;
  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {

    this.certificateService.allUsers().subscribe(data=>{
      this.users = data;

    })
  }

  enable(id : number){
    this.certificateService.enable(id).then( value =>
      this.certificateService.allUsers().subscribe(data=>{
        this.users = data;
      })
    );
  }

  disable(id: number){
    this.certificateService.disable(id).then(value =>
      this.certificateService.allUsers().subscribe(data=>{
        this.users = data;
      })
    );
  }

  search(searchValue : string) {
    console.log("sv:" + searchValue);
    if(searchValue == undefined || searchValue == ""){
      this.certificateService.allUsers().subscribe(data=>{
        this.users = data;
      });
    }else {
      this.certificateService.searchUsers(searchValue).subscribe(data => {
        this.users = data;
      });
    }
  }
}
