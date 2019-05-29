import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private companyUrl = 'https://localhost:8443/companies';


  constructor(private http: HttpClient) {
  }

  allCertificates(): Observable<any> {
    return this.http.get<any>(this.companyUrl + "/allCompanies");
  }


  addCompanyUser(id, companyId): Observable<any> {

    let params = new HttpParams()
      .append("id", id)
      .append("companyId", companyId);

    console.log(params);

    return this.http.post(`${this.companyUrl}/addToUser`, params);

  }


}
