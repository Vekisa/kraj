import {Component, NgModule, OnInit, Pipe} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CertificateService} from "../service/certificate.service";
import {NgbDate, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';

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
  selectType: string;

  roott: boolean;
  leaf: boolean;

  searchText: any;

  fromDate: NgbDate;
  toDate: NgbDate;
  toDateMax: NgbDate;


  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService, private calendar: NgbCalendar) {
  }

  ngOnInit() {

    this.roott = false;
    this.leaf = false;
    this.selectType = "Intermediate";

    this.certForm = this.formBuilder.group({
      parent: [''],
      country: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      state: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      locality: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      organization: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      organizationUnit: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
      commonName: ['', [Validators.required, Validators.pattern('[a-zA-Z_: ]*')]],
      startDate: null,
      endDate: null,
      alias: ['', [Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      password: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*'), Validators.minLength(8)]],
      leaf: [''],
      serialNumber: ['']
    });

    this.certificateService.allCertificatesWithoutLeafs().subscribe(data => {
      this.cert = data;
      this.certTemp = data;
    });

    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 1);

  }

  onSubmit() {

    const fromDate = this.certForm.get('startDate').value;
    const toDate = this.certForm.get('endDate').value;

    const fromDateDate = new Date(fromDate.year, fromDate.month - 1, fromDate.day, 0, 0, 0);
    const toDateDate = new Date(toDate.year, toDate.month - 1, toDate.day, 0, 0, 0);

    this.certForm.patchValue({
      startDate: fromDateDate,
      endDate: toDateDate

    });

    console.log(this.certForm.value);

    this.certForm.value.leaf = this.leaf;

    this.certificateService.newCertificate(this.certForm.value).subscribe(value =>
        this.certificateService.allCertificatesWithoutLeafs().subscribe(data => {
          this.cert = data;
          this.certTemp = data;
        }), err => {
        alert(err.error.message);
      }
    );
  }

  rowSelected(cert: any) {
    this.certForm.value.parent = cert;
    this.inputVar = cert;
    this.par = cert;
  }

  search(searchValue: string) {
    if (searchValue == undefined || searchValue == "") {
      this.certificateService.allCertificatesWithoutLeafs().subscribe(data => {
        this.cert = data;
      });
    } else {
      this.certificateService.search(searchValue, false, true).subscribe(data => {
        this.cert = data;
      });
    }
  }

  mySelectHandler() {

    if (this.selectType === "Root") {
      this.roott = true;
      this.leaf = false;
    } else if (this.selectType === "Leaf") {
      this.roott = false;
      this.leaf = true;
    } else {
      this.roott = false;
      this.leaf = false;
    }

  }

  onDateSelected() {

    console.log('Promena');

    this.toDate = this.calendar.getNext(this.certForm.get('startDate').value, 'd', 1);

    this.certForm.patchValue({
      endDate: this.toDate

    });

  }

}
