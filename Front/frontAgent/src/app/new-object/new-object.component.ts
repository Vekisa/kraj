import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from "@angular/forms";
import {ObjectService} from "../services/object.service";
import {Adress, Object} from "../model";
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
      name: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9 ]*'), Validators.maxLength(50)]],
      description: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9,.!?: ]*'), Validators.maxLength(250)]],
      category: ['', [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(2)]]
    })
    this.newAddressForm=this.formBuilder.group({
      state: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(30)]],
      city: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9- ]*'), Validators.maxLength(30)]],
      street: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9,.-/ ]*'), Validators.maxLength(50)]],
      number: ['', [Validators.required, Validators.pattern('[0-9]*'), Validators.maxLength(4)]],
      zip: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9 ]*'), Validators.maxLength(10)]],
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
