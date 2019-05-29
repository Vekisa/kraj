import {Component, OnInit, ViewChild} from '@angular/core';
import {RolesService} from "../../service/roles.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup} from "@angular/forms";
import {EndPoint, Group, Roles} from "../../model";
import {GroupService} from "../../service/group.service";
import {init} from "protractor/built/launcher";


@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  @ViewChild('content') private content;

  groups: Group[];
  groupForm: FormGroup;
  modalTitle = "";

  roleForm: FormGroup;


  roles: Roles[];
  rolesAdded: Roles[];

  addingGroup: boolean;
  editingGroup: boolean;


  endPointAdding: boolean;

  viewGroup: boolean;

  modalRef: any;

  selectedGroup: any;


  constructor(private groupService: GroupService, private modalService: NgbModal, private formBuilder: FormBuilder, private rolesService: RolesService) {
  }

  ngOnInit() {

    this.groupForm = this.formBuilder.group({
      name: ""
    });

    this.roleForm = this.formBuilder.group({
      roleSelect: ""
    });

    this.initGroups();

  }

  initGroups() {

    this.groupService.allGroups().subscribe(data => {

      this.groups = data;
    });

  }

  initTableEndPoint() {

    this.rolesService.allRollesAdded(this.selectedGroup.id).subscribe(data => {
      this.rolesAdded = data;
    });

  }

  groupSelected(group: any) {


    this.viewGroup = true;

    this.selectedGroup = group;

    this.initTableEndPoint();

  }

  addGroup() {
    this.endPointAdding = false;

    this.addingGroup = true;
    this.editingGroup = false;

    this.modalTitle = "Add group";

    this.modalRef = this.modalService.open(this.content);

  }

  editGroup(i: number) {
    this.endPointAdding = false;

    this.modalTitle = "Edit group";

    this.addingGroup = false;
    this.editingGroup = true;

    this.selectedGroup = this.groups[i];

    this.groupForm = this.formBuilder.group({
      name: this.selectedGroup.name
    });

    this.modalRef = this.modalService.open(this.content);
  }

  deleteGroup(id: number) {

    this.groupService.deleteGroup(id).subscribe(data => {
      console.log(data)
      this.initGroups();
    });

  }


  onSubmit() {

    if (this.addingGroup) {
      this.groupService.addGroup(this.groupForm.get("name").value).subscribe(data => {
        console.log(data);
        this.initGroups();
      });
    } else if (this.editingGroup) {
      this.groupService.editGroup(this.selectedGroup.id, this.groupForm.get("name").value).subscribe(data => {
        console.log(data);
        this.initGroups();
      });
    }

    this.modalRef.close();
  }

  addGroupRole() {

    this.rolesService.allRollesMissing(this.selectedGroup.id).subscribe(data => {
      this.roles = data;
    });

    this.endPointAdding = true;

    this.addingGroup = false;
    this.editingGroup = false;

    this.modalTitle = "Add role to " + this.selectedGroup.name;

    this.modalRef = this.modalService.open(this.content);

  }


  deleteRole(id: number) {

    this.rolesService.removeRoleFromGroup(id, this.selectedGroup.id).subscribe(data => {
      this.initTableEndPoint();
    });

  }

  onSubmitEnd() {

    this.groupService.addRolesToGroup(this.selectedGroup.id, this.roleForm.get("roleSelect").value).subscribe(data => {

      this.initTableEndPoint();

    });

    this.modalRef.close();

  }


}
