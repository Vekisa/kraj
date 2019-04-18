package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xmlb.model.User.Group;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.service.RoleService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Kreiranje rola", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Role.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Role> createRole(@RequestParam(value="name") String name, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.createRole(name), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Edit role", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Role.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Role> editRole(@RequestParam(value="id") Long id,@RequestParam(value="name") String name, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.editRole(id,name), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/addEndPointToRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Add end points to role", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Role.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Role> addEndPointToRole(@RequestParam(value="id") Long id,@RequestParam(value="list") List<Long> list, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.addEndPointsToRole(id, list), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/deleteEndPointFromRole", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Delete end point from role", httpMethod = "DELETE", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Role.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Role> deleteEndPointFromRole(@RequestParam(value="id") Long id,@RequestParam(value="endPointId") Long endPointId, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.deleteEndPointFromRole(id, endPointId), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value="Brisanje grupe", httpMethod = "DELETE", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> deleteRole(@RequestParam(value="id") Long id, HttpServletRequest hr) {
        roleService.deleteRole(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/add_role_to_user/{user_id}/{role_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Dodavanje usera u grupu", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> addRoleToUser(@PathVariable(value="user_id") Long userId, @PathVariable(value="role_id") Long roleId, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.addRoleToUser(userId,roleId), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Dodavanje usera u grupu", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Role>> allRoles(HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.allRoles(), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/remove_role_from_user/{role_id}/{user_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Dodavanje usera u grupu", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> removeRoleFromUser(@PathVariable(value="user_id") Long userId, @PathVariable(value="role_id") Long roleId, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.removeRoleFromUser(userId,roleId), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/removeRoleFromGroup", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Remove role from group", httpMethod = "DELETE", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Group> removeRoleFromGroup(@RequestParam(value="groupId") Long groupId, @RequestParam(value="roleId") Long roleId, HttpServletRequest hr) {
        return new ResponseEntity<>(roleService.removeRoleFromGroup(groupId,roleId), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/allRolesAdded", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All added to group", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Role>> allAddedRoles(@RequestParam(value="id") Long id, HttpServletRequest hr){
        return new ResponseEntity<>(roleService.allAddedRoles(id), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/allMissing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All missing from group", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Role>> allMissingRoles(@RequestParam(value="id") Long id, HttpServletRequest hr){
        return new ResponseEntity<>(roleService.allMissingRoles(id), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/allRolesAddedUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All added to group", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Role>> allAddedRolesUser(@RequestParam(value="id") Long id, HttpServletRequest hr){
        return new ResponseEntity<>(roleService.allAddedRolesUser(id), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/allMissingRolesUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All missing from group", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Role>> allMissingRolesUser(@RequestParam(value="id") Long id, HttpServletRequest hr){
        return new ResponseEntity<>(roleService.allMissingRolesUser(id), HttpStatus.OK);
    }
}
