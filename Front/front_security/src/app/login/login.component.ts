import {Component, OnInit} from '@angular/core';
import {UserLogin} from '../model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from "../authentication/auth.service";
import {TokenService} from "../authentication/token.service";

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

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private tokenStorage: TokenService) {
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.reloadPage();
    }

    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_ ]*')]],
      password: ['', [Validators.required, Validators.pattern('[a-zA-Z0-9_!?*#/]*')]]
    });
  }

  onSubmit() {
    console.log(this.loginForm);

    this.loginInfo = new UserLogin(
      this.loginForm.get('username').value,
      this.loginForm.get('password').value);

    this.authService.attemptAuth(this.loginInfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUsername(data.username);
        this.tokenStorage.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
        this.authService.checkIP().subscribe(
          data => {
            this.checkIP = false;
          },
          error => {
            this.checkIP = true;
          }
        )
        this.authService.checkUser(this.loginForm.get('username').value.toString()).subscribe(
          data => {
            this.checkU = false;
          },
          error => {
            this.checkU = true;
          }
        )
      }
    );
  }

  reloadPage() {
    window.location.href = "https://localhost:4200/home"
  }


}
