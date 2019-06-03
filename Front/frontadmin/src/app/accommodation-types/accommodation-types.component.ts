import { Component, OnInit } from '@angular/core';
import {ObjectTypesService} from "../services/object-types.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccommodationTypesService} from "../services/accommodation-types.service";
import {AccommodationType} from "../model";

@Component({
  selector: 'app-accommodation-types',
  templateUrl: './accommodation-types.component.html',
  styleUrls: ['./accommodation-types.component.css']
})
export class AccommodationTypesComponent implements OnInit {

  accommodationTypeForm : FormGroup;
  accommodationTypes : AccommodationType[];

  constructor(private accommodationTypesService: AccommodationTypesService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.accommodationTypeForm = this.formBuilder.group({
      name: [''],
      description: ['']
    });

    this.accommodationTypesService.allObjectTypes().subscribe(data =>{
      this.accommodationTypes = data;
    })
  }

  onSubmit(){
    this.accommodationTypesService.createAccommodationType(this.accommodationTypeForm.value).subscribe(data =>{
      this.accommodationTypesService.allObjectTypes().subscribe(data=>{
        this.accommodationTypes = data;
      })
    });
  }

  remove(id : number){
    this.accommodationTypesService.remove(id).subscribe(data =>{
      this.accommodationTypesService.allObjectTypes().subscribe(data=>{
        this.accommodationTypes = data;
      })
    });
  }
}
