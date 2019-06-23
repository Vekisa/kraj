import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Adress} from "../../../../frontAgent/src/app/model";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private baseURL = 'http://localhost:8888/object';

  constructor(private http: HttpClient) { }

  search(city:string, startDate:Date, endDate:Date, persons:number, ):Observable<any>{
    return this.http.get<any>(this.baseURL+ "/search").pipe(catchError(err => {
      return throwError(err);
    }));
  }
}
