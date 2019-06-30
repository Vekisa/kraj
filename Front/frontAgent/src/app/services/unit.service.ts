import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Image, PriceSchedule, Unit} from "../model";
import {catchError} from "rxjs/operators";
import {JwtResponse} from "src/app/response";


@Injectable({
  providedIn: 'root'
})
export class UnitService {
  private unitURL = 'http://localhost:8762/agent/unit';


  constructor(private http: HttpClient) { }

  newUnit(unit: Unit): Observable<Unit> {
    console.log("uslo");
    return this.http.post<Unit>(this.unitURL + "/create_new_unit", unit).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  updateUnit(ps: PriceSchedule, id:number): Observable<Unit> {
    console.log("uslo update "  + id);
    return this.http.put<Unit>(this.unitURL + "/update_unit/" + id, ps).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  allUnits(): Observable<any> {
    return this.http.get<any>(this.unitURL + "/getAll");
  }

  newImage(image: any): Observable<Image>{
    console.log("uslo image");
    return this.http.post<Image>(this.unitURL + "/save_image", image).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  getImage(id: number): Observable<any>{
    return this.http.get<any>(this.unitURL + "/get_images/" + id).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  findById(id: number): Observable<Unit>{
    return this.http.get<Unit>(this.unitURL + "/find_unit/" + id).pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
