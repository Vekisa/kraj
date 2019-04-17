package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlb.model.Company;
import xmlb.model.User.User;
import xmlb.service.CompanyService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/allCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Get all companies", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Company>> allCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @RequestMapping(value = "/addToUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Add to user", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> addToUser(@RequestParam(value="id") Long id,@RequestParam(value="companyId") Long companyId) {
        return new ResponseEntity<>(companyService.setCompany(id,companyId), HttpStatus.OK);
    }

}
