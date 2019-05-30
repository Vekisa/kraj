package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.logstash.logback.argument.StructuredArguments;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import xmlb.model.Certificate;
import xmlb.dto.CertificateDTO;
import xmlb.security.ResponseMessage;
import xmlb.service.CertificateService;
import xmlb.service.Logging;
import xmlb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    private Logging logging = new Logging(getClass());

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserService userService;

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr())")
    @RequestMapping(value = "/{alias}/search/{leafs}/{root}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pretrazuje sertifikate", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> search(@PathVariable(value = "alias") String alias, @PathVariable(value = "leafs") Boolean leafs, @PathVariable(value = "root") Boolean root,
                                                       HttpServletRequest hr) {
        // logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: "
        //      + alias + ", " + leafs + ", " + root);
        return new ResponseEntity<>(certificateService.search(alias, leafs, root), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL() , #hr.getRemoteAddr())")
    @RequestMapping(value = "/createSS", method = RequestMethod.POST)
    @ApiOperation(value = "Kreira novi samopotpisani sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewSSCertificate(@RequestBody CertificateDTO certificateDTO, HttpServletRequest hr) {
        //logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + certificateDTO.toString());
        logging.printPar(hr.getRequestURL().toString(), userService.getCurrentUser(), hr.getRemoteAddr(), null);
        certificateService.createNewSelfSignedCertificate(certificateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr()) AND @accesControllService.hasAccessToCertificate(#certificateDTO.parent , #hr.getRemoteAddr())")
    @RequestMapping(value = "/create_new_certificate", method = RequestMethod.POST)
    @ApiOperation(value = "Kreira novi sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewCertificate(@RequestBody CertificateDTO certificateDTO, HttpServletRequest hr) throws CertificateException, CertIOException, OperatorCreationException {
        logging.printPar(hr.getRequestURL().toString(), userService.getCurrentUser(), hr.getRemoteAddr(), null);
        certificateService.createNewIssuedCertificate(certificateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /* @RequestMapping(value= "/show/{alias}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<String> show(@PathVariable(value="alias") String alias) {
        return new ResponseEntity<>(certificateService.showKeyStoreContent(alias),HttpStatus.OK);
    }*/

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr())")
    @RequestMapping(value = "/all_without_leafs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Prikaz sertifikata bez listova", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificatesWithoutLeafs(HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: X");
        return new ResponseEntity<>(certificateService.allCertificatesWithoutLeafs(), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr())")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Prikaz svih sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificates(HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: X");
        return new ResponseEntity<>(certificateService.all(), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr())")
    @RequestMapping(value = "/all_without_root", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Prikaz svih sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificatesWithoutRoot(HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: X");
        return new ResponseEntity<>(certificateService.allWithoutRoot(), HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr())  AND @accesControllService.hasAccessToCertificate(#serialNumber, #hr.getRemoteAddr())")
    @RequestMapping(value = "/revoke", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Povlacenje sertifikata", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> revoke(@RequestBody String serialNumber, HttpServletRequest hr) {
        //logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + serialNumber);
        logging.printPar(hr.getRequestURL().toString(), userService.getCurrentUser(), hr.getRemoteAddr(), serialNumber);
        certificateService.revokeCertificate(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL(), #hr.getRemoteAddr())")
    @RequestMapping(value = "/checkIfValid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Check if valid", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> checkIfValid(@RequestBody String alias, HttpServletRequest hr) {
        logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + alias);
        if (certificateService.checkIfValid(alias)) {
            logging.printInfo("IN FUNC: Valid");
            return new ResponseEntity<>(new ResponseMessage("Valid"), HttpStatus.OK);
        } else
            logging.printInfo("IN FUNC: Not valid");
        return new ResponseEntity<>(new ResponseMessage("NOT Valid!"), HttpStatus.OK);
    }

    //Za AIA
    // @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value = "/getCertificate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Pronalazi sertfikat", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Certificate> getCertificate(@RequestBody String serialNumber) {
        //0logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: " + serialNumber);
        return new ResponseEntity<>(certificateService.findBySerialNumber(serialNumber), HttpStatus.OK);
    }

    @RequestMapping(value = "/allRevoke", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Prikaz svih povucenih sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allRevoke() {
        //logging.printInfo("ENDPOINT: " + hr.getRequestURL() + " USER: " + userService.getCurrentUser() + " IP ADDRESS: " + hr.getRemoteAddr() + " PARAMETERS: ");

        List<CertificateDTO> lista = certificateService.all();
        List<CertificateDTO> listaP = certificateService.all();
        for (CertificateDTO c : lista) {
            if (c.getRevoked())
                listaP.add(c);
        }

        //logging.printInfo("IN FUNC: Success");
        return new ResponseEntity<>(listaP, HttpStatus.OK);
    }
}
