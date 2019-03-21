import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CertificateService} from "../service/certificate.service";

@Component({
  selector: 'app-new-certificate',
  templateUrl: './new-certificate.component.html',
  styleUrls: ['./new-certificate.component.css']
})
export class NewCertificateComponent implements OnInit {

  certForm: FormGroup;


  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {

    this.certForm = this.formBuilder.group({
      country: [''],
      state: [''],
    loc: [''],
    org: [''],
    orgUnit:[''],
    commName: [''],
    startDate: null,
    endDate: null,
    alias:[''],
    password: ['']
    });



  }

  onSubmit(){

    console.log(this.certForm.value);

    this.certificateService.newCertificate(this.certForm.value);

  }



}
