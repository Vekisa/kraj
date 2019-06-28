import { Component, OnInit } from '@angular/core';
import {ObjectTypesService} from '../services/object-types.service';
import {ObjectType} from '../model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-object-types',
  templateUrl: './object-types.component.html',
  styleUrls: ['./object-types.component.css']
})
export class ObjectTypesComponent implements OnInit {
  objectTypesForm: FormGroup;
  objectTypes: ObjectType[];

  constructor(private objectTypeService: ObjectTypesService, private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.objectTypesForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      description: ['']
    });

    this.objectTypeService.allObjectTypes().subscribe(data => {
      this.objectTypes = data;
    });
  }

  onSubmit() {
    this.objectTypeService.createObjectType(this.objectTypesForm.value).subscribe(data => {
      // tslint:disable-next-line:no-shadowed-variable
      this.objectTypeService.allObjectTypes().subscribe(data => {
        this.objectTypes = data;
      });
    });
  }

  remove(id: number) {
    this.objectTypeService.delete(id).subscribe(data => {
      // tslint:disable-next-line:no-shadowed-variable
      this.objectTypeService.allObjectTypes().subscribe( data => {
        this.objectTypes = data;
      });
    });
  }

}
