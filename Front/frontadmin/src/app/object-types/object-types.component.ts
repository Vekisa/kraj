import { Component, OnInit } from '@angular/core';
import {ObjectTypesService} from "../services/object-types.service";
import {ObjectType} from "../model";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-object-types',
  templateUrl: './object-types.component.html',
  styleUrls: ['./object-types.component.css']
})
export class ObjectTypesComponent implements OnInit {
  objectTypesForm: FormGroup;
  objectTypes : ObjectType[];

  constructor(private objectTypeService: ObjectTypesService, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.objectTypesForm = this.formBuilder.group({
      name: [''],
      description: ['']
    });

    this.objectTypeService.allObjectTypes().subscribe(data => {
      this.objectTypes = data;
    });
  }

  onSubmit() {
    this.objectTypeService.createObjectType(this.objectTypesForm.value);
  }

  remove(id:number){
    console.log("obr1")
    this.objectTypeService.delete(id);
  }

}
