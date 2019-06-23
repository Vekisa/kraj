import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
  ActivatedRoute
} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthService} from "../services/auth.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private route: ActivatedRoute,private router:Router,private authService:AuthService){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let snapshot = this.route.snapshot;

    var value = this.authService.isValid();
    if (value != false ){

      this.authService.roles().subscribe(data=>{

        console.log();

        if (data.authorities.indexOf("ROLE_ADMIN")!==-1) {
          console.log("RIGHTS");

            return true;
        }

      });

    }

    this.router.navigate(['sign_in']);
    return false;

  }
  
}
