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

  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {

    this.comForm = this.formBuilder.group({
      first: [''],
      second:['']
    });

    this.certificateService.allCertificates().subscribe(data=>{
      this.cert = data;
    });
  }

  rowSelected(cert:any){
    //this.comForm.value.parent = cert;
  }

}
