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
import xmlb.service.GroupService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Kreiranje grupe", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Group> createGroup(@RequestParam(value="name") String name, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.createGroup(name), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value="Brisanje grupe", httpMethod = "DELETE", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> deleteGroup(@RequestParam(value="id") Long id, HttpServletRequest hr) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value="Edit grupe", httpMethod = "PUT", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> editGroup(@RequestParam(value="id") Long id,@RequestParam(value="name") String name, HttpServletRequest hr) {

        return new ResponseEntity<>(groupService.editGroup(id,name),HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Add user to group", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> addUserInGroup(@RequestParam(value="user_id") Long userId, @RequestParam(value="group_id") Long groupId, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.addUserInGroup(userId,groupId), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/removeUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Delete user from group", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> removeUserFromGroup(@RequestParam(value="user_id") Long userId, @RequestParam(value="group_id") Long groupId, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.removeUserFromGroup(userId,groupId), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/all_groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Sve grupe", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Group>> allGroups(HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.allGroups(), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/users_from_group/{id_group}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Useri iz zadate grupe", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<User>> allGroups(@PathVariable(value="id_group") Long id, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.usersFromGroup(id), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/addRoleToGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Add role to group", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Group> addRoleToGroup(@RequestParam(value="id") Long id,@RequestParam(value="list") List<Long> list, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.rolesToGroup(id, list), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/allUserGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All user groups", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Group>> allUserGroups(@RequestParam(value="id") Long id, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.allGroupsUser(id), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/allUserMissingGroups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All user groups", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Group>> allUserMissingGroups(@RequestParam(value="id") Long id, HttpServletRequest hr) {
        return new ResponseEntity<>(groupService.allMissingGroupsUser(id), HttpStatus.OK);
    }




}
