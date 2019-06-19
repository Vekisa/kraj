import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Unit} from "../model";
import {catchError} from "rxjs/operators";
import {JwtResponse} from "src/app/response";

@Injectable({
  providedIn: 'root'
})
export class UnitService {
  private unitURL = 'http://localhost:8888/unit';

  constructor(private http: HttpClient) { }

  newUnit(unit: Unit): Observable<JwtResponse> {
    console.log("uslo");
    return this.http.post<JwtResponse>(this.unitURL + "/create_new_unit", unit).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  allUnits(): Observable<any> {
    return this.http.get<any>(this.unitURL + "/getAll");
  }
}
