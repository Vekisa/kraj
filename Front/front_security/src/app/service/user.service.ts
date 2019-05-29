import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'https://localhost:8443/users';


  constructor(private http: HttpClient) {
  }


}
