import { Component, OnInit } from '@angular/core';
import {CertificateService} from "../service/certificate.service";

import {Observable} from "rxjs";
import {CertInfo} from "../model";

@Component({
  selector: 'app-revoke',
  templateUrl: './revoke.component.html',
  styleUrls: ['./revoke.component.css']
})
export class RevokeComponent implements OnInit {

  searchText: any;

  constructor( private certificateService: CertificateService) { }

  certificates: CertInfo[];

  ngOnInit() {
    this.certificateService.allCertificates().subscribe(data=>{
      this.certificates = data;
    });
  }

  revoke(alias: string){
    this.certificateService.revokeCertificate(alias).then(value => {
      this.certificateService.allCertificates().subscribe(data=>{
        this.certificates = data;
      });
    });

  }

  search(searchValue : string) {
    console.log("sv:" + searchValue);
    if(searchValue == undefined || searchValue == ""){
      this.certificateService.allCertificates().subscribe(data=>{
        this.certificates = data;
      });
    }else {
      this.certificateService.search(searchValue).subscribe(data => {
        this.certificates = data;
      });
    }
  }
}
