import { Component, OnInit } from '@angular/core';
import {ObjectTypesService} from "../services/object-types.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ExtraOption, ObjectType} from "../model";
import {ExtraOptionsService} from "../services/extra-options.service";

@Component({
  selector: 'app-extra-options',
  templateUrl: './extra-options.component.html',
  styleUrls: ['./extra-options.component.css']
})
export class ExtraOptionsComponent implements OnInit {

  extraOptionsForm: FormGroup;
  extraOptions : ExtraOption[];

  constructor(private extraOptionsService: ExtraOptionsService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.extraOptionsForm = this.formBuilder.group({
      name: [''],
      price: [''],
      description: ['']
    });

    this.extraOptionsService.allExtraOptions().subscribe(data => {
      this.extraOptions = data;
    });
  }

  onSubmit(){
    this.extraOptionsService.createExtraOption(this.extraOptionsForm.value).subscribe(data => {
      this.extraOptionsService.allExtraOptions().subscribe(data =>{
        this.extraOptions = data;
      })
    });
  }

  remove(id: number){
    this.extraOptionsService.delete(id).subscribe(data =>{
      this.extraOptionsService.allExtraOptions().subscribe(data =>{
        this.extraOptions = data;
      })
    });
  }

}
