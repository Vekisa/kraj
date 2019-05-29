import {Component, OnInit} from '@angular/core';
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
  certComm: CertInfo[];

  fS: number;
  first: string;
  second: string;

  searchTextF: any;
  searchTextS: any;

  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) {
  }

  ngOnInit() {
    this.fS = 1;
    this.comForm = this.formBuilder.group({
      first: [''],
      second: ['']
    });

    this.certificateService.allCertificates().subscribe(data => {
      this.cert = data;
    });
  }

  rowSelected(cert: any) {
    if (this.fS == 1) {
      this.comForm.value.first = cert;
      this.first = cert;
    } else {
      this.comForm.value.second = cert;
      this.second = cert;
    }

    this.certificateService.communications(cert).subscribe(data => {
      this.certComm = data;
    });

  }

  buttonR(broj: number) {
    this.fS = broj;
  }

  enableCommunication() {
    console.log(this.first + " " + this.second);
    this.certificateService.enableCommunication(this.first, this.second).then(value =>
      this.certificateService.communications(this.first).subscribe(data => {
        this.certComm = data;
      })
    );
  }

  disableCommunication(alias: string) {
    console.log(this.first + " " + alias);
    if (alias == this.first) {
      this.certificateService.disableCommunication(this.first, this.second).then(value =>
        this.certificateService.communications(this.first).subscribe(data => {
          this.certComm = data;
        }));
    } else {
      this.certificateService.disableCommunication(this.first, alias).then(value =>
        this.certificateService.communications(this.first).subscribe(data => {
          this.certComm = data;
        })
      );
    }

  }

  searchS(searchValue: string) {
    if (searchValue == undefined || searchValue == "") {
      this.certificateService.allCertificates().subscribe(data => {
        this.cert = data;
      });
    } else {
      this.certificateService.search(searchValue, true, true).subscribe(data => {
        this.cert = data;
      });
    }

  }

  searchF(searchValue: string) {
    if (searchValue == undefined || searchValue == "") {
      this.certificateService.communications(this.first).subscribe(data => {
        this.certComm = data;
      });
    } else {
      this.certComm = this.certComm.filter(x => x.alias.includes(searchValue));
    }
  }

}
