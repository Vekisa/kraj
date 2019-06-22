import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpRequest, HttpClient, HttpHeaders} from "@angular/common/http";
import {HttpInterceptor} from "@angular/common/http";
import { Observable } from "rxjs";
import {Router} from "@angular/router";
import {AuthService} from "../services/auth.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor
{

  constructor(private _router: Router,private authService:AuthService) { }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>
  {
    console.log("interceptor radi ")

    let token = this.authService.getToken();

    if (token){


        request = request.clone({headers: request.headers.set('Authorization', 'Bearer ' + token)});
        request = request.clone({headers: request.headers.set('Content-type', 'application/x-www-form-urlencoded; charset=utf-8 ')});

    }

    return next.handle(request);

  }
}
