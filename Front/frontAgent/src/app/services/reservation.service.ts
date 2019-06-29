import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Reservation} from "../model";
import {Observable, throwError} from "rxjs";
import {JwtResponse} from "../response";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private reservationURL = 'http://localhost:8762/reservation/reservation';

  constructor(private http: HttpClient) { }

  checkReservation(reservation: Reservation): Observable<number>{
    return this.http.put<number>(this.reservationURL + "/checkReservationAndCal", reservation);
  }

  createReservation(reservation: Reservation): Observable<JwtResponse>{
    return this.http.post<JwtResponse>(this.reservationURL+"/createReservation", reservation).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  updateReservation(reservation: Reservation): Observable<JwtResponse>{
    return this.http.put<JwtResponse>(this.reservationURL+"/update", reservation).pipe(catchError(err => {
      return throwError(err);
    }));
  }

  getReservations(): Observable<any>{
    return this.http.get<any>(this.reservationURL+"/getAll").pipe(catchError(err => {
      return throwError(err);
    }));
  }

  cancelReservations(reservation: number): Observable<JwtResponse>{
    return this.http.delete<JwtResponse>(this.reservationURL+"/cancel/"+reservation).pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
