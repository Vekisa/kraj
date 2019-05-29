import {Component, OnInit, ViewChild} from '@angular/core';
import {Group, Roles, User} from "../../model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CertificateService} from "../../service/certificate.service";
import {RolesService} from "../../service/roles.service";
import {GroupService} from "../../service/group.service";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-user-roles',
  templateUrl: './user-roles.component.html',
  styleUrls: ['./user-roles.component.css']
})
export class UserRolesComponent implements OnInit {

  @ViewChild('content') private content;

  users: User[];

  roles: Roles[];
  groups: Group[];

  rolesAdded: Roles[];
  groupsAdded: Group[];

  roleForm: FormGroup;
  groupForm: FormGroup;

  selectedUser: any;

  enableSelected: boolean;

  addRoleModal: boolean;
  addGroupModal: boolean;

  modalTitle = "";

  modalRef: any;

  constructor(private modalService: NgbModal, private certificateService: CertificateService, private rolesService: RolesService, private groupService: GroupService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.roleForm = this.formBuilder.group({
      roleSelect: ""
    });

    this.groupForm = this.formBuilder.group({
      groupSelect: ""
    });

    this.certificateService.allUsers().subscribe(data => {
      this.users = data;

    });

  }

  initTables() {

    this.rolesService.allRollesUserAdded(this.selectedUser.id).subscribe(data => {

      this.rolesAdded = data;
    });

    this.groupService.allGroupsUserAdded(this.selectedUser.id).subscribe(data => {

      this.groupsAdded = data;
    });

  }

  userSelected(user) {


    this.enableSelected = true;

    this.selectedUser = user;

    this.initTables();


  }


  addRole() {

    this.addRoleModal = true;
    this.addGroupModal = false;

    this.rolesService.allRollesUserMissing(this.selectedUser.id).subscribe(data => {

      this.roles = data;
    });


    this.modalRef = this.modalService.open(this.content);


  }

  addGroup() {
    this.addRoleModal = false;
    this.addGroupModal = true;

    this.groupService.allGroupsUserMissing(this.selectedUser.id).subscribe(data => {

      this.groups = data;
    });

    this.modalRef = this.modalService.open(this.content);
  }

  onSubmitRole() {

    this.rolesService.addRolesUsers(this.selectedUser.id, this.roleForm.get("roleSelect").value).subscribe(data => {
      this.initTables();
    });

    this.modalRef.close();

  }


  onSubmitGroup() {
    this.rolesService.addGroupsUsers(this.selectedUser.id, this.groupForm.get("groupSelect").value).subscribe(data => {
      this.initTables();
    });

    this.modalRef.close();

  }

  deleteRole(id: number) {

    this.rolesService.removeRoleFromUser(this.selectedUser.id, id).subscribe(data => {

      this.initTables();
    });

  }

  deleteGroup(id: number) {

    this.rolesService.removeGroupFromUser(this.selectedUser.id, id).subscribe(data => {
      this.initTables();
    });

  }

}
