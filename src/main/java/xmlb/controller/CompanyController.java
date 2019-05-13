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
import xmlb.model.Company;
import xmlb.model.User.User;
import xmlb.repository.UserRepository;
import xmlb.service.CompanyService;
import xmlb.service.Logging;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private Logging logging = new Logging(getClass());

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserRepository userRepository;

    private String getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr())")
    @RequestMapping(value = "/allCompanies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Get all companies", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Company>> allCompanies(HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: X");
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr()) AND @accesControllService.workForCompany(#companyId,#hr.getRemoteAddr())")
    @RequestMapping(value = "/addToUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Add to user", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<User> addToUser(@RequestParam(value="id") Long id,@RequestParam(value="companyId") Long companyId, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + id + ", " + companyId);
        return new ResponseEntity<>(companyService.setCompany(id,companyId), HttpStatus.OK);
    }

}
