package xmlb.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xmlb.model.User.EndPoint;
import xmlb.model.User.Group;
import xmlb.model.User.User;
import xmlb.repository.UserRepository;
import xmlb.service.EndPointsService;
import xmlb.service.Logging;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/endpoint")
public class EndPointsController {

    private Logging logging = new Logging(getClass());

    @Autowired
    EndPointsService endPointsService;

    @Autowired
    private UserRepository userRepository;

    private String getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr())")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All EndPoints", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<EndPoint>> allEndPoints(HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: X");
        return new ResponseEntity<>(endPointsService.allEndPoints(), HttpStatus.OK);
    }


    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr())")
    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All EndPoints", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<EndPoint>> roleEndPoints(@RequestParam(value="id") Long id, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + id);
        return new ResponseEntity<>(endPointsService.allEndPointsId(id), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr())")
    @RequestMapping(value = "/roleMissing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All EndPointsMissing", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<EndPoint>> roleEndPointsMissing(@RequestParam(value="id") Long id, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + id);
        return new ResponseEntity<>(endPointsService.allEndPointsMissingId(id), HttpStatus.OK);
    }
}
