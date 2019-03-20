import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {User} from "../model";
import {AuthService} from "../authentication/auth.service";
import {ConfirmPasswordValidator} from "../validation/confirm-pass.validator";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  signUpForm: FormGroup;
  user: User;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';

  submitted = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit() {

    this.signUpForm = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      username: [''],
      email: [''],
      password: [''],
      passwordConfirm: ['']
    }, {
      validator: ConfirmPasswordValidator.validate.bind(this)

    });


  }

  onSubmit() {

    this.authService.signUp(this.signUpForm.value).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false;
      }, error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }

    );



  }


  reloadPage() {
    window.location.href = "http://localhost:4200/"
  }









}
