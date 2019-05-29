import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private groupUrl = 'https://localhost:8443/groups';

  constructor(private http: HttpClient) {
  }

  allGroups(): Observable<any> {
    return this.http.get<any>(this.groupUrl + "/all_groups");
  }

  addGroup(name): Observable<any> {
    let params = new HttpParams()
      .append("name", name)

    return this.http.post<any>(this.groupUrl + "/add", params);
  }

  editGroup(id, name): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
      .append("name", name)
    return this.http.put<any>(this.groupUrl, params);
  }

  deleteGroup(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)

    return this.http.delete<any>(this.groupUrl, {params: params});
  }

  addRolesToGroup(id, list): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
      .append("list", list)

    return this.http.post<any>(this.groupUrl + "/addRoleToGroup", params);
  }

  allGroupsUserAdded(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
    return this.http.get<any>(this.groupUrl + "/allUserGroups", {params: params});
  }

  allGroupsUserMissing(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
    return this.http.get<any>(this.groupUrl + "/allUserMissingGroups", {params: params});
  }


}
