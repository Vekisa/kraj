import {Component, NgModule, OnInit, Pipe} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {CertificateService} from "../service/certificate.service";
import {FilterPipe} from "../additional/filter.pipe";

import {Observable} from "rxjs";
import {CertInfo} from "../model";


@Component({
  selector: 'app-new-certificate',
  templateUrl: './new-certificate.component.html',
  styleUrls: ['./new-certificate.component.css']

})
export class NewCertificateComponent implements OnInit {

  certForm: FormGroup;
  cert: CertInfo[];
  certTemp: CertInfo[];
  par: any;
  inputVar: string;

  searchText:any;


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

    this.certificateService.allCertificates().subscribe(data=>{
      this.cert = data;
      this.certTemp = data;
    });
  }

  onSubmit(){

    console.log(this.certForm.value);

    //this.certificateService.newCertificate(this.certForm.value);
    this.certificateService.showCertificate("self");
  }

  rowSelected(cert:any){
    console.log(this.certForm.get('parent').value);
    console.log(this.cert);
    this.certForm.value.parent = cert;
    this.inputVar = cert;
    this.par = cert;
  }

  onSearchChange(searchValue : string) {
    console.log(searchValue);
    this.cert = this.certTemp.filter(
      CertInfo => CertInfo.alias === searchValue);

  }


  revoke(alias: string){
    this.certificateService.revokeCertificate(alias);
  }



}
