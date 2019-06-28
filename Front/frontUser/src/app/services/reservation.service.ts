import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {Reservation} from "../../../../frontAgent/src/app/model";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private baseReservationURL = 'http://localhost:8762/reservation/reservation';

  constructor(private http: HttpClient) { }

  getAllForUser():Observable<any>{
    return this.http.get<any>(this.baseReservationURL + "/get_all_for_user").pipe(catchError(err => {
      return throwError(err);
    }));
  };

  reserve(reservation: Reservation):Observable<any>{
    return this.http.post(this.baseReservationURL + "/create",reservation).pipe(catchError( err => {
      return throwError(err);
    }));
  }

  checkUnit(reservation: Reservation):Observable<any>{
    return this.http.put(this.baseReservationURL + "/checkReservation",reservation).pipe(catchError( err => {
      return throwError(err);
    }));
  }

  getPrice(reservation: Reservation):Observable<any>{
    return this.http.put(this.baseReservationURL + "/checkReservationAndCal",reservation).pipe(catchError( err => {
      return throwError(err);
    }));
  }

}
