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
import xmlb.model.User.User;
import xmlb.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('MAIN_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Vraca sve usere", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<User>> allCUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MAIN_ADMIN')")
    @RequestMapping(value = "/{text}/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pretraga usera", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<User>> search(@PathVariable(value="text") String text) {
        return new ResponseEntity<>(userService.search(text), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MAIN_ADMIN')")
    @RequestMapping(value = "{id}/enable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Enable usera", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> enable(@PathVariable(value="id") Long id) {
        return new ResponseEntity<>(userService.enableUser(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('MAIN_ADMIN')")
    @RequestMapping(value = "{id}/disable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Enable usera", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> disable(@PathVariable(value="id") Long id) {
        return new ResponseEntity<>(userService.disableUser(id), HttpStatus.OK);
    }

}
