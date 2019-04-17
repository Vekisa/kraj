import {Component, NgModule, OnInit, Pipe} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CertificateService} from "../service/certificate.service";
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

  searchText:any;

  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService) { }

  ngOnInit() {

    this.roott= false;
    this.leaf = false;
    this.selectType = "Intermediate";

    this.certForm = this.formBuilder.group({
      parent: [''],
      country: ['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      state: ['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      locality: ['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      organization: ['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      organizationUnit:['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      commonName: ['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      startDate: null,
      endDate: null,
      alias:['',[Validators.required, Validators.pattern('[a-zA-Z_]*')]],
      password: ['',[Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*'), Validators.minLength(8)]],
      leaf:[''],
      serialNumber:['']
    });

    this.certificateService.allCertificatesWithoutLeafs().subscribe(data=>{
      this.cert = data;
      this.certTemp = data;
    });
  }

  onSubmit(){

    console.log(this.certForm.value);

    this.certForm.value.leaf=this.leaf;

    this.certificateService.newCertificate(this.certForm.value).subscribe( value =>
      this.certificateService.allCertificatesWithoutLeafs().subscribe(data=>{
        this.cert = data;
        this.certTemp = data;
      }),err => {
        alert(err.error.message);
      }
    );
  }

  rowSelected(cert:any){
    this.certForm.value.parent = cert;
    this.inputVar = cert;
    this.par = cert;
  }

  search(searchValue : string) {
    if(searchValue == undefined || searchValue == ""){
      this.certificateService.allCertificatesWithoutLeafs().subscribe(data=>{
        this.cert = data;
      });
    }else {
      this.certificateService.search(searchValue, false, true).subscribe(data => {
        this.cert = data;
      });
    }
  }

  mySelectHandler(){

    if (this.selectType==="Root") {
      this.roott = true;
      this.leaf = false;
    }else if (this.selectType==="Leaf"){
      this.roott = false;
      this.leaf = true;
    }else{
      this.roott = false;
      this.leaf = false;
    }

  }

}
