package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value= "/{alias}/search/{leafs}/{root}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pretrazuje sertifikate", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> search(@PathVariable(value="alias") String alias, @PathVariable(value="leafs") Boolean leafs, @PathVariable(value="root") Boolean root,
                                                       HttpServletRequest hr) {
        return new ResponseEntity<>(certificateService.search(alias, leafs, root),HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value= "/createSS",method = RequestMethod.POST)
    @ApiOperation(value="Kreira novi samopotpisani sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewSSCertificate(@RequestBody CertificateDTO certificateDTO, HttpServletRequest hr) {
        certificateService.createNewSelfSignedCertificate(certificateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL()) AND @accesControllService.hasAccessToCertificate(#certificateDTO.parent)")
    @RequestMapping(value= "/create_new_certificate",method = RequestMethod.POST)
    @ApiOperation(value="Kreira novi sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewCertificate(@RequestBody CertificateDTO certificateDTO, HttpServletRequest hr) {
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

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value= "/all_without_leafs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata bez listova", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificatesWithoutLeafs(HttpServletRequest hr) {
        return new ResponseEntity<>(certificateService.allCertificatesWithoutLeafs(),HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz svih sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificates(HttpServletRequest hr) {
        return new ResponseEntity<>(certificateService.all(),HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value= "/all_without_root", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz svih sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificatesWithoutRoot(HttpServletRequest hr) {
        return new ResponseEntity<>(certificateService.allWithoutRoot(),HttpStatus.OK);
    }

    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())  AND @accesControllService.hasAccessToCertificate(#serialNumber)")
    @RequestMapping(value= "/revoke", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Povlacenje sertifikata", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> revoke(@RequestBody String serialNumber,HttpServletRequest hr) {
        certificateService.revokeCertificate(serialNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /*@RequestMapping(value= "/checkIfValid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Check if valid", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> checkIfValid(@RequestBody String alias) {


        if (certificateService.checkIfValid(alias)){
            return new ResponseEntity<>(new ResponseMessage("Valid"),HttpStatus.OK);
        }else
            return new ResponseEntity<>(new ResponseMessage("NOT Valid!"),HttpStatus.OK);

    }*/

    //Za AIA
    @PreAuthorize("@accesControllService.hasAccess(#hr.getRequestURL())")
    @RequestMapping(value= "/getCertificate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pronalazi sertfikat", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Certificate> getCertificate(@RequestBody String serialNumber,HttpServletRequest hr) {

        return new ResponseEntity<>(certificateService.findBySerialNumber(serialNumber),HttpStatus.OK);
    }

    /*@RequestMapping(value= "/allRevoke", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz svih povucenih sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allRevoke() {
        List<CertificateDTO>lista=certificateService.all();
        List<CertificateDTO>listaP=certificateService.all();
        for(CertificateDTO c: lista){
            if(c.getRevoked())
                listaP.add(c);
        }
        return new ResponseEntity<>(listaP, HttpStatus.OK);
    }*/
}
