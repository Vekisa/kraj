import {Component, NgModule, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CertificateService} from "../service/certificate.service";

import {Observable} from "rxjs";
import {CertInfo} from "../model";

@Component({
  selector: 'app-new-certificate',
  templateUrl: './new-certificate.component.html',
  styleUrls: ['./new-certificate.component.css']
})
export class NewCertificateComponent implements OnInit {

  certForm: FormGroup;
  certificates: Observable<CertInfo[]>;
  par: any;
  inputVar: string;


  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {

    this.certForm = this.formBuilder.group({
      parent: [''],
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

    this.certificates = this.certificateService.allCertificates();
  }

  onSubmit(){

    console.log(this.certForm.value);

    //this.certificateService.newCertificate(this.certForm.value);
    this.certificateService.showCertificate("self");
  }

  rowSelected(cert:any){
    this.certForm.value.parent = cert;
    this.inputVar = cert;
    this.par = cert;
  }

  onSearchChange(searchValue : string) {
    console.log(searchValue);
   /* this.certificates = this.certificates.filter(
      CertInfo => CertInfo.name === searchValue);*/

  }

  revoke(alias: string){
    console.log(alias);
    this.certificateService.revokeCertificate(alias);
  }
}
