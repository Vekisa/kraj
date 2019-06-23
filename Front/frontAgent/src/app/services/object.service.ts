import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {JwtResponse} from "../response";
import {catchError} from "rxjs/operators";
import {Adress} from "../model";
import {Object} from "../model";
@Injectable({
  providedIn: 'root'
})
export class ObjectService {
  a: any;
  private objectURL = 'http://localhost:8764/object';
  constructor(private http: HttpClient) { }

  newAddress(address:Adress):Observable<Adress>{
    return this.http.post<Adress>(this.objectURL + "/create_new_address", address).pipe(catchError(err => {
      return throwError(err);
    }));
  }
  newObject(object: Object): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.objectURL + "/create_new_object", object).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  allObjects(): Observable<any>{
    return this.http.get<any>(this.objectURL+ "/getAll");
  }

  findUnits(id: number): Observable<any>{
    return this.http.get<any>(this.objectURL+"/getUnits/"+id);
  }
}
