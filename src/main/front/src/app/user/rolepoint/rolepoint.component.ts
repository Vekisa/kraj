import {Component, OnInit, ViewChild} from '@angular/core';
import {RolesService} from "../../service/roles.service";
import {EndPoint, Roles} from "../../model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-rolepoint',
  templateUrl: './rolepoint.component.html',
  styleUrls: ['./rolepoint.component.css']
})
export class RolepointComponent implements OnInit {

  roles : Roles[];
  endPoints: EndPoint[];
  endPointsAdded: EndPoint[];
  roleForm: FormGroup;
  endPointForm: FormGroup;

  addEndPoint:boolean;
  addRoleModal:boolean;

  selectedEndPoint:boolean;

  roleEdit:boolean;
  roleAdd:boolean;

  modalTitle="";

  modalRef: any;

  selectedRole:any;

  editingRole:any;

  constructor(private rolesService:RolesService,private modalService: NgbModal,private formBuilder: FormBuilder) { }

  @ViewChild('content') private content;

  ngOnInit() {
    this.init();

    this.roleForm = this.formBuilder.group({
      name:""
    });

    this.endPointForm = this.formBuilder.group({
      endPointSelect:""
    });

  }

  init(){

    this.rolesService.allroles().subscribe(data=>{

      this.roles = data;
    });

  }

  initTableEndPoint(){

    this.rolesService.allEndPointsId(this.selectedRole.id).subscribe(data=>{
      this.endPointsAdded = data;
    });

  }

  roleSelected(role){

    this.selectedEndPoint = true;

    this.selectedRole = role;

    this.initTableEndPoint();

  }

  addRole(){

    this.roleAdd=true;
    this.roleEdit=false;

    this.addRoleModal = true;
    this.addEndPoint = false;

    this.modalTitle = "Add role";


    this.modalRef = this.modalService.open(this.content);

  }

  editRole(i:number){
    this.roleAdd=false;
    this.roleEdit=true;

    this.addRoleModal = true;
    this.addEndPoint = false;

    this.editingRole = this.roles[i];

    this.roleForm = this.formBuilder.group({
      name:this.editingRole.name
    });

    this.modalTitle = "Edit role";

    this.modalRef = this.modalService.open(this.content);

  }

  deleteRole(id){

    this.rolesService.deleteRole(id).subscribe(data=>{
      this.init();
    });

  }

  onSubmit(){

    if (this.roleAdd){
      this.rolesService.addRole(this.roleForm.get(name).value).subscribe(data=>{
        this.init();
        console.log(data);
      });

    }else if (this.roleEdit){


      this.rolesService.editRole(this.editingRole.id,this.roleForm.get("name").value).subscribe(data=>{
          this.init();
      });

    }


    this.modalRef.close();

  }

  onSubmitEnd(){
    console.log(this.endPointForm.get("endPointSelect").value)

    this.rolesService.addPointsMissing(this.selectedRole.id,this.endPointForm.get("endPointSelect").value).subscribe(data=>{

      console.log(data);
      this.initTableEndPoint();
    });

    this.modalRef.close();
  }

  addPointRole(){

    this.rolesService.allEndPointsMissing(this.selectedRole.id).subscribe(data=>{
      this.endPoints = data;
      console.log(data);
    });

    this.addRoleModal = false;
    this.addEndPoint = true;

    this.modalTitle = "Add End Points to " + this.selectedRole.name;

    this.modalRef = this.modalService.open(this.content);


  }

  deleteEndPoint(id){

    this.rolesService.deletePointFromRole(this.selectedRole.id,id).subscribe(data=>{
      console.log(data);
      this.initTableEndPoint();
    });

  }

}





