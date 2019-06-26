import {Component, Input, OnInit} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BackendService} from "../services/backend.service";
import {ActivatedRoute} from "@angular/router";
import {Unit} from "../../../../frontAgent/src/app/model";
import {AuthService} from "../services/auth.service";
import {Plan} from "../model";

@Component({
  selector: 'app-objects',
  templateUrl: './objects.component.html',
  styleUrls: ['./objects.component.css']
})
export class ObjectsComponent implements OnInit {
  @Input() id: number;
  title = "CodeSandbox";
  currentRate = 1;
  unit : Unit;
  extraOptions: any;
  isLoggedIn = false;
  plans :Plan[];
  comments : Comment[];

  constructor(private backendService: BackendService, private activatedRoute: ActivatedRoute,private authService:AuthService) { }

  ngOnInit() {
    this.authService.roles().subscribe(data=>{
      this.isLoggedIn = true;
    },err=>{
      this.isLoggedIn = false;
    });


    this.activatedRoute.params.subscribe(data=>{
      this.backendService.getUnit(data['id']).subscribe(data =>{
        this.unit = data;
        console.log(data);
      });

      this.backendService.extraOptionsOfObject(data['id']).subscribe(data =>{
        this.extraOptions = data;
        console.log(data);
      });

      this.backendService.getPlanOfUnit(data['id']).subscribe(data => {
        this.plans = data;
        console.log(data);
      });

      this.backendService.getComments(data['id']).subscribe(data => {
        this.comments = data;
        console.log("COMMENTS: ");
        console.log(data);
      });

    });
  }

}
