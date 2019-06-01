import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {ObjectTypesService} from "../services/object-types.service";
import {ObjectType} from "../model";

@Component({
  selector: 'app-object-types',
  templateUrl: './object-types.component.html',
  styleUrls: ['./object-types.component.css']
})
export class ObjectTypesComponent implements OnInit {

  objectTypes : ObjectType[];

  constructor(private objectTypeService: ObjectTypesService) { }

  ngOnInit() {
    this.objectTypeService.allObjectTypes().subscribe(data => {
      this.objectTypes = data;
    });
  }

}
