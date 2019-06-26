import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {ObjectService} from "../services/object.service";
import {Adress, Object, Type} from "../model";
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-object',
  templateUrl: './new-object.component.html',
  styleUrls: ['./new-object.component.css']
})
export class NewObjectComponent implements OnInit {

  newObjectForm: FormGroup;
  newAddressForm: FormGroup;
  object: Object;
  address: Adress;
  constructor(private formBuilder: FormBuilder, private objectService: ObjectService, private router: Router) { }

  ngOnInit() {
    this.newObjectForm=this.formBuilder.group({
      name: [''],
      description: [''],
      category: ['']
    })
    this.newAddressForm=this.formBuilder.group({
      state: [''],
      city: [''],
      street: [''],
      number: [''],
      zip: [''],
      longitude: [''],
      latitude: ['']
    })


  }

  onSubmit(){
    this.object=this.newObjectForm.value;
    this.objectService.newAddress(this.newAddressForm.value).subscribe( data=>
    {
      this.object.adress=data;
      this.objectService.newObject(this.object).subscribe( data=>
        console.log(data)
      );}
    );
    this.router.navigateByUrl('/home/newUnit');
  }
}
