import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {NewPass, RegisteredUser} from "../model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmPasswordValidator} from "../validation/confirm-pass.validator";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  @ViewChild('content',{static: true}) private content;

  profileFormGroup: FormGroup;
  passFormGroup: FormGroup;

  user:RegisteredUser;

  openPass = false;
  userDetail = false;

  inp1 = false;
  inp2 = false;
  inp3 = false;

  name = false;
  username = false;
  email = false;

  input1 = '';
  input2 = '';
  input3 = '';

  errorSub = '';

  submitError = false;

  modalRef: any;

  constructor(private authService:AuthService,private modalService: NgbModal,private fb: FormBuilder) { }

  ngOnInit() {

    this.initUser();

    this.profileFormGroup = this.fb.group({
      input1: null,
      input2: null,
      input3: null
    });

  }

  initUser() {

    this.authService.user().subscribe(data=>{
      this.user = data;
    });

  }

  initForm() {
    this.userDetail = true;
    this.openPass = false;
    this.submitError = false;
  }

  editEmail() {

    this.initForm();

    this.input1 = 'Email';

    this.profileFormGroup = this.fb.group({
      input1: this.user.email,
      input2: null,
      input3: null
    });

    this.inp1 = true;
    this.inp2 = false;
    this.inp3 = false;

    this.name = false;
    this.username = false;
    this.email = true;

    this.modalRef = this.modalService.open(this.content);
  }

  editName() {

    this.initForm();

    this.modalRef = this.modalService.open(this.content);

    this.input1 = 'First name';
    this.input2 = 'Last name';

    this.profileFormGroup = this.fb.group({
      input1: this.user.firstName,
      input2: this.user.lastName,
      input3: null
    });

    this.inp1 = true;
    this.inp2 = true;
    this.inp3 = false;

    this.name = true;
    this.username = false;
    this.email = false;

  }


  editPass() {

    this.userDetail = false;
    this.openPass = true;

    this.submitError = false;
    this.resetPassField();

    this.modalRef = this.modalService.open(this.content);
  }

  editUsername() {

    this.initForm();

    this.input1 = 'Username';

    this.profileFormGroup = this.fb.group({
      input1: this.user.username,
      input2: null,
      input3: null
    });

    this.inp1 = true;
    this.inp2 = false;
    this.inp3 = false;

    this.name = false;
    this.username = true;
    this.email = false;


    this.modalRef = this.modalService.open(this.content);
  }


  changePass() {

    const newPass = new NewPass(
      this.passFormGroup.get('oldPassword').value,
      this.passFormGroup.get('password').value
    );

    this.authService.changePass(newPass).subscribe(data=> {
        this.initUser();
        this.modalRef.close();

    },
      error => {
        this.errorSub = 'Bad Credentials';

        this.submitError = true;

        this.resetPassField();

      });

  }

  onClicked() {

    this.modalRef.close();
  }


  submit() {

    this.submitError = false;

    if (this.name === true) {
      console.log('NAME');

      const u = new RegisteredUser(
        null,
        this.profileFormGroup.get('input1').value,
        this.profileFormGroup.get('input2').value,
        null,
        null,
        null,
        null,
        null
      );


      this.authService.changeName(u).subscribe(data=>{
        this.initUser();
      },error => {
        this.errorSub = error.error.message;
        this.submitError = true;
      });


    } else if (this.username === true) {

      console.log('USERNAME');
      const u = new RegisteredUser(
        null,
        null,
        null,
        null,
        null,
        this.profileFormGroup.get('input1').value,
        null,
        null
      );

      this.authService.changeUserName(u).subscribe(data=>{
        console.log(data);
        this.authService.saveToken(data);
        this.reloadPage();
      },error => {
        this.errorSub = error.error.message;
        this.submitError = true;
      });

    } else if (this.email === true) {
      console.log('EMAIL');
      const u = new RegisteredUser(
        null,
        null,
        null,
        null,
        this.profileFormGroup.get('input1').value,
        null,
        null,
        null
      );

      this.authService.changeEmail(u).subscribe(data => {

        this.initUser();

      },error =>{
        this.errorSub = error.error.message;

        this.submitError = true;
      });


    }

    console.log(this.submitError);

    if (!this.submitError) {
      this.modalRef.close();
    }

    this.initUser();

    console.log(this.profileFormGroup.value);
  }



  resetPassField() {

    this.passFormGroup = this.fb.group({
      oldPassword: [''],
      password: [''],
      passwordConfirm: ['']
    }, {
      validator: ConfirmPasswordValidator.validate.bind(this)

    });

  }

  reloadPage() {
    window.location.reload();
  }




}
