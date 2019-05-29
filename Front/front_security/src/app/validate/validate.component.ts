import {Component, OnInit} from '@angular/core';
import {CertificateDB, CertInfo} from "../model";
import {CertificateService} from "../service/certificate.service";

@Component({
  selector: 'app-validate',
  templateUrl: './validate.component.html',
  styleUrls: ['./validate.component.css']
})
export class ValidateComponent implements OnInit {

  certi: CertInfo[];
  info: any;

  constructor(private certificateService: CertificateService) {
  }

  ngOnInit() {

    this.certificateService.allCertificates().subscribe(data => {
      this.certi = data;
    })

  }

  check(string) {

    this.certificateService.checkValidate(string).then(data => {

      this.info = string + " - " + data.message;

    })

  }

}
