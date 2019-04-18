import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
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

  onSubmit() {
    this.authService.signUp(this.signUpForm.value).subscribe(
      data => {
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.reloadPage();
      }, error => {
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
      }
    );
  }


  reloadPage() {
    window.location.href = "https://localhost:4200/"
  }
}
