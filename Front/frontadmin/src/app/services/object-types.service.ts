import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ObjectTypesService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  allObjectTypes(): Observable<any> {
    return this.http.get<any>(this.baseUrl + "/object_type");
  }

}
