package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import xmlb.dto.CertificateDTO;
import xmlb.model.Communication;
import xmlb.model.User.User;
import xmlb.repository.UserRepository;
import xmlb.service.CommunicationService;
import xmlb.service.Logging;
import xmlb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/communication")
public class CommunicationController {

    private Logging logging = new Logging(getClass());

    @Autowired
    private CommunicationService communicationService;

    @Autowired
    private UserService userService;

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr()) AND @accesControllService.hasAccessToCertificate(#first_serial_number,#hr.getRemoteAddr()) " +
            "AND @accesControllService.hasAccessToCertificate(#second_serial_number,#hr.getRemoteAddr())")
    @RequestMapping(value = "/{first_sn}/{second_sn}/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje komunikacije", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Communication.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Communication> createCommunication(@PathVariable(value = "first_sn") String first_serial_number, @PathVariable(value = "second_sn") String second_serial_number,
                                                             HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + first_serial_number + ", " +
                second_serial_number);
        return new ResponseEntity<>(communicationService.createCommunication(first_serial_number, second_serial_number), HttpStatus.CREATED);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr()) AND @accesControllService.hasAccessToCertificate(#first,#hr.getRemoteAddr()) " +
            "AND @accesControllService.hasAccessToCertificate(#second,#hr.getRemoteAddr())")
    @RequestMapping(value = "/{first_alias}/{second_alias}/disable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Brisanje komunikacije", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> delete(@PathVariable(value = "first_alias") String first, @PathVariable(value = "second_alias") String second, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + first + ", " +
                second);
        communicationService.delete(first, second);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(),#hr.getRemoteAddr())")
    @RequestMapping(value = "/{serial_number}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Vraca sve komunikacije za prosledjeni sertifikat", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> getCommunicationsOfCertificate(@PathVariable(value = "serial_number") String serialNumber, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + serialNumber);
        return new ResponseEntity<>(communicationService.getCommunicationsOfCertificate(serialNumber), HttpStatus.OK);
    }
}
