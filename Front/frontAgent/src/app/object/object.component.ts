import { Component, OnInit } from '@angular/core';
import {Unit} from "../model";
import {ObjectService} from "../services/object.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-object',
  templateUrl: './object.component.html',
  styleUrls: ['./object.component.css']
})
export class ObjectComponent implements OnInit {
  objects: Object[];
  constructor(private objectService: ObjectService, private router: Router) { }

  ngOnInit() {
    this.objectService.allObjects().subscribe(data=>
    this.objects=data)
  }

  showUnits(i: number){
    this.router.navigateByUrl('/showUnits/' + i);
  }
}
