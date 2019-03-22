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

  constructor( private certificateService: CertificateService) { }

  certificates: Observable<CertInfo[]>;

  ngOnInit() {
    this.certificates = this.certificateService.allCertificatesL();
  }

  revoke(alias: string){
    this.certificateService.revokeCertificate(alias).then(value => {
      this.certificates = this.certificateService.allCertificatesL();
    });

  }
}
