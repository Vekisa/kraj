import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  private usersUrl = 'https://localhost:8443/users';
  private rolesUrl = 'https://localhost:8443/roles';
  private endPointUrl = 'https://localhost:8443/endpoint';


  constructor(private http: HttpClient) {
  }

  allroles(): Observable<any> {
    return this.http.get<any>(this.rolesUrl + "/all");
  }

  addRole(name): Observable<any> {
    let params = new HttpParams()
      .append("name", name)

    return this.http.post<any>(this.rolesUrl + "/add", params);
  }

  deleteRole(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)

    return this.http.delete<any>(this.rolesUrl + "/delete", {params: params});
  }

  allEndPoints(): Observable<any> {
    return this.http.get<any>(this.endPointUrl + "/all");
  }

  allEndPointsId(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)

    return this.http.get<any>(this.endPointUrl + "/role", {params: params});
  }

  allEndPointsMissing(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)

    return this.http.get<any>(this.endPointUrl + "/roleMissing", {params: params});
  }


  addPointsMissing(id, list): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
      .append("list", list)

    return this.http.post<any>(this.rolesUrl + "/addEndPointToRole", params);
  }

  deletePointFromRole(id, endPointId): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
      .append("endPointId", endPointId)

    return this.http.delete<any>(this.rolesUrl + "/deleteEndPointFromRole", {params: params});
  }

  editRole(id, name) {
    let params = new HttpParams()
      .append("id", id)
      .append("name", name)

    return this.http.post<any>(this.rolesUrl + "/edit", params);

  }


  allRollesAdded(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
    return this.http.get<any>(this.rolesUrl + "/allRolesAdded", {params: params});
  }

  allRollesMissing(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
    return this.http.get<any>(this.rolesUrl + "/allMissing", {params: params});
  }

  removeRoleFromGroup(roleId, groupId) {
    let params = new HttpParams()
      .append("groupId", groupId)
      .append("roleId", roleId)
    return this.http.delete<any>(this.rolesUrl + "/removeRoleFromGroup", {params: params});

  }


  allRollesUserAdded(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
    return this.http.get<any>(this.rolesUrl + "/allRolesAddedUser", {params: params});
  }

  allRollesUserMissing(id): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
    return this.http.get<any>(this.rolesUrl + "/allMissingRolesUser", {params: params});
  }


  addRolesUsers(id, list): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
      .append("list", list)

    return this.http.post<any>(this.usersUrl + "/addRoleToUser", params);
  }

  addGroupsUsers(id, list): Observable<any> {
    let params = new HttpParams()
      .append("id", id)
      .append("list", list)

    return this.http.post<any>(this.usersUrl + "/addGroupToUser", params);
  }

  removeRoleFromUser(userId, roleId): Observable<any> {
    let params = new HttpParams()
      .append("userId", userId)
      .append("roleId", roleId)
    return this.http.delete<any>(this.usersUrl + "/removeRoleFromUser", {params: params});
  }

  removeGroupFromUser(userId, groupId): Observable<any> {
    let params = new HttpParams()
      .append("userId", userId)
      .append("groupId", groupId)
    return this.http.delete<any>(this.usersUrl + "/removeGroupFromUser", {params: params});
  }


}
