import {Component, OnInit, ViewChild} from '@angular/core';
import {Company, User} from "../../model";
import {CertificateService} from "../../service/certificate.service";
import {FormBuilder, FormGroup} from "@angular/forms"
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {CompanyService} from "../../service/company.service";


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[];
  companies: Company[];
  searchText: any;
  userForm: FormGroup;

  selectedUser: any;

  constructor(private formBuilder: FormBuilder, private certificateService: CertificateService, private modalService: NgbModal,
              private companyService: CompanyService) {
  }

  @ViewChild('content') private content;

  modalRef: any

  ngOnInit() {

    this.initTable();

    this.companyService.allCertificates().subscribe(data => {
      this.companies = data;
      console.log(data);
    })

    this.userForm = this.formBuilder.group({
      company: "",
    });
  }

  initTable() {

    this.certificateService.allUsers().subscribe(data => {
      this.users = data;

    })

  }

  enable(id: number) {
    this.certificateService.enable(id).then(value =>
      this.initTable()
    );

  }

  disable(id: number) {
    this.certificateService.disable(id).then(value =>
      this.initTable()
    )
  }

  search(searchValue: string) {
    console.log("sv:" + searchValue);
    if (searchValue == undefined || searchValue == "") {
      this.certificateService.allUsers().subscribe(data => {
        this.users = data;
      });
    } else {
      this.certificateService.searchUsers(searchValue).subscribe(data => {
        this.users = data;
      });
    }
  }

  addCompany(id: number) {
    this.modalRef = this.modalService.open(this.content);
    let select: any;

    if (this.users[id].company)
      select = this.users[id].company.id
    else
      select = 0

    this.userForm = this.formBuilder.group({
      company: select
    });
    this.selectedUser = this.users[id].id;

    console.log(this.users[id])
  }

  onSubmit() {

    console.log()

    const companyId = this.userForm.get('company').value

    this.companyService.addCompanyUser(this.selectedUser, companyId).toPromise().then(data => {
      console.log(data);
      this.initTable();
    })

    this.modalRef.close();

  }
}
