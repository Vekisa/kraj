package xmlb.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlb.model.User.EndPoint;
import xmlb.model.User.Group;
import xmlb.service.EndPointsService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/endpoint")
public class EndPointsController {


    @Autowired
    EndPointsService endPointsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All EndPoints", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<EndPoint>> allEndPoints() {
        return new ResponseEntity<>(endPointsService.allEndPoints(), HttpStatus.OK);
    }


    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All EndPoints", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<EndPoint>> roleEndPoints(@RequestParam(value="id") Long id) {
        return new ResponseEntity<>(endPointsService.allEndPointsId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/roleMissing", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="All EndPointsMissing", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<EndPoint>> roleEndPointsMissing(@RequestParam(value="id") Long id) {
        return new ResponseEntity<>(endPointsService.allEndPointsMissingId(id), HttpStatus.OK);
    }



}
