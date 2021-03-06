import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegisteredUser} from "../model";
import {AuthService} from "../services/auth.service";
import {ConfirmPasswordValidator} from "../validation/confirm-pass.validator";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signUpForm: FormGroup;
  user: RegisteredUser;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  spinner = false;

  submitted = false;

  constructor(private route:Router,private formBuilder: FormBuilder, private authService: AuthService) { }

  ngOnInit() {

    var state = this.authService.isValid();

    this.authService.roles().subscribe(data=>{
      console.log('Ulogovan ')
      window.location.href='';

    });

    this.signUpForm = this.formBuilder.group({

      firstName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(30)]],
      lastName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*'), Validators.maxLength(30)]],
      username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_ ]*'), Validators.maxLength(15)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*'), Validators.minLength(8), Validators.maxLength(30)]],
      passwordConfirm: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*'), Validators.minLength(8), Validators.maxLength(30)]]
    }, {
      validator: ConfirmPasswordValidator.validate.bind(this)
    });

  }

  onSubmit(){
    this.spinner = true;
    this.authService.saveUser(this.signUpForm.value).subscribe(
      data=>{
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        window.location.href='';
      },error =>{
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;

      });
  }


}
