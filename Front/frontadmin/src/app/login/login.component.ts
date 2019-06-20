import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserLogin} from "../model";
import {AuthService} from "../services/auth.service";



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  buttonName = 'Log in';
  form: any = {};
  loginDiv = true;
  signupDiv = false;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  private loginInfo: UserLogin;
  loginForm: FormGroup;
  checkIP = false;
  checkU = false;

  constructor(private formBuilder:FormBuilder,private authService:AuthService) { }

  ngOnInit() {

    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_ ]*')]],
      password: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*')]]
    });
  }

  onSubmit(){

    console.log(this.loginForm);

    this.loginInfo = new UserLogin(
      this.loginForm.get('username').value,
      this.loginForm.get('password').value);

    this.authService.login(this.loginInfo);

  }



}
