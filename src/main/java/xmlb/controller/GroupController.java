package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlb.model.User.Group;
import xmlb.model.User.User;
import xmlb.service.GroupService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Kreiranje grupe", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Group> createGroup(@PathVariable(value="name") String name) {
        return new ResponseEntity<>(groupService.createGroup(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value="Brisanje grupe", httpMethod = "DELETE", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Group.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> deleteGroup(@PathVariable(value="id") Long id) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @RequestMapping(value = "/add_user/{user_id}/{group_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Dodavanje usera u grupu", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> addUserInGroup(@PathVariable(value="user_id") Long userId, @PathVariable(value="group_id") Long groupId) {
        return new ResponseEntity<>(groupService.addUserInGroup(userId,groupId), HttpStatus.OK);
    }

    @RequestMapping(value = "/remove_user_from_group/{user_id}/{group_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Dodavanje usera u grupu", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> removeUserFromGroup(@PathVariable(value="user_id") Long userId, @PathVariable(value="group_id") Long groupId) {
        return new ResponseEntity<>(groupService.removeUserFromGroup(userId,groupId), HttpStatus.OK);
    }

    @RequestMapping(value = "/all_groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Sve grupe", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Group>> allGroups() {
        return new ResponseEntity<>(groupService.allGroups(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users_from_group/{id_group}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Useri iz zadate grupe", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<User>> allGroups(@PathVariable(value="id_group") Long id) {
        return new ResponseEntity<>(groupService.usersFromGroup(id), HttpStatus.OK);
    }

}
