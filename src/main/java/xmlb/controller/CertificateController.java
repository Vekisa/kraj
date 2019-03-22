package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlb.model.CertificateInfo;
import xmlb.service.CertificateService;

import java.security.cert.X509Certificate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @RequestMapping(value= "/search/{id}/{name}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pretrazuje sertifikate", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<String>> search(@PathVariable(value="id") Long id, @PathVariable(value="name") String name) {
        return new ResponseEntity<>(certificateService.search(name),HttpStatus.OK);
    }

    @RequestMapping(value= "/check/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Proverava validnost sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Boolean> check(@PathVariable(value="id") Long id) {
        return new ResponseEntity<>(certificateService.check(id),HttpStatus.OK);
    }


    @RequestMapping(value= "/createSS",method = RequestMethod.POST)
    @ApiOperation(value="Kreira novi samopotpisani sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewSSCertificate(@RequestBody CertificateInfo certificateInfo) {
        System.out.println("Create new certificate!");
        certificateService.createNewSelfSignedCertificate(certificateInfo);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value= "/create_new_certificate",method = RequestMethod.POST)
    @ApiOperation(value="Kreira novi samopotpisani sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewCertificate(@RequestBody CertificateInfo certificateInfo) {
        certificateService.createNewIssuedCertificate(certificateInfo);
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

    @RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateInfo>> allCertificates() {
        return new ResponseEntity<>(certificateService.allCertificates(),HttpStatus.OK);
    }
}
