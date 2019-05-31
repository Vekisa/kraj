import {Component, OnInit} from '@angular/core';
import {TokenService} from "../authentication/token.service";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  isLoggedIn = false;
  mainAdmin = null;

  constructor(private tokenStorage: TokenService) {
  }

  ngOnInit() {

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.mainAdmin = this.tokenStorage.getAuthorities().includes("ROLE_MAIN_ADMIN");
      console.log(this.mainAdmin);
    }

  }

  logout() {

    if (this.isLoggedIn) {
      this.tokenStorage.signOut();
      window.location.href = "https://localhost:4200/"
    }

  }
}