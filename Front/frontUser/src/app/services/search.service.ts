import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private baseURL = 'http://localhost:8762/search';
  private url = "";
  constructor(private http: HttpClient) { }

  search(city:string, startDate:Date, endDate:Date, persons:number, accTypes: any[], category: any[], distance: number, additionalTypes: any[]):Observable<any>{
  this.url = this.baseURL + "?city=" + city + "&start_date=" + startDate + "&end_date=" + endDate + "&persons=" + persons + "&distance=" + distance;

    if(accTypes.length > 0){
      this.url += "&accommodation_types=";
      accTypes.forEach(  value =>{
        this.url += value.item_id + ",";
      });
      this.url = this.url.substring(0,this.url.length-1);
    }

    if(category.length > 0){
      this.url += "&category=";
      category.forEach(  value =>{
        this.url += value.item_id + ",";
      });
      this.url = this.url.substring(0,this.url.length-1);
    }

    if(additionalTypes.length > 0){
      this.url += "&extra_options=";
      additionalTypes.forEach(  value =>{
        this.url += value.item_id + ",";
      });
      this.url = this.url.substring(0,this.url.length-1);
    }

    console.log("URL: " + this.url);

    return this.http.get<any>(this.url).pipe(catchError(err => {
      console.log("ERROR");
      return throwError(err);
    }));
  }

}
