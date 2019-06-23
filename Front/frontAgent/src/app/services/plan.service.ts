import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Plan, PriceSchedule} from "../model";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {JwtResponse} from "../response";

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  private planURL = 'http://localhost:8762/reservation/unit';
  constructor(private http: HttpClient) { }

  newPlan(plan:Plan):Observable<JwtResponse>{
    return this.http.post<JwtResponse>(this.planURL + "/create_new_plan", plan).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  newPlanList(priceSchedule: PriceSchedule):Observable<PriceSchedule>{
    return this.http.post<PriceSchedule>(this.planURL + "/create_new_priceS", priceSchedule).pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
