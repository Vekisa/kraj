import { Component, OnInit } from '@angular/core';
import {CertInfo} from "../model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {CertificateService} from "../service/certificate.service";

@Component({
  selector: 'app-communication',
  templateUrl: './communication.component.html',
  styleUrls: ['./communication.component.css']
})
export class CommunicationComponent implements OnInit {
  comForm: FormGroup;
  cert: CertInfo[];

  fS:number;
  first: string;
  second: string;
  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {
    this.fS=1;
    this.comForm = this.formBuilder.group({
      first: [''],
      second:['']
    });

    this.certificateService.allCertificates().subscribe(data=>{
      this.cert = data;
    });
  }

  rowSelected(cert:any){
    if(this.fS==1){
      this.comForm.value.first = cert;
      this.first=cert;
    }else{
      this.comForm.value.second = cert;
      this.second=cert;
    }

  }

  buttonR(broj: number){
    this.fS=broj;
  }

  enableCommunication(){
    console.log(this.first + " " + this.second);
    this.certificateService.enableCommunication(this.first,this.second);
  }

}
