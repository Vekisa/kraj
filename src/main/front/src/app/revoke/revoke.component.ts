import { Component, OnInit } from '@angular/core';
import {CertificateService} from "../service/certificate.service";

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
    this.certificateService.allCertificatesWithoutRoot().subscribe(data=>{
      this.certificates = data;
    });
  }

  revoke(alias: string){
    this.certificateService.revokeCertificate(alias).then(value => {
      this.certificateService.allCertificatesWithoutRoot().subscribe(data=>{
        this.certificates = data;
      });
    });
  }

  search(searchValue : string) {
    if(searchValue == undefined || searchValue == ""){
      this.certificateService.allCertificatesWithoutRoot().subscribe(data=>{
        this.certificates = data;
      });
    }else {
      this.certificateService.search(searchValue,true,false).subscribe(data => {
        this.certificates = data;
      });
    }
  }
}
