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
import xmlb.model.Revoke;
import xmlb.security.SignUpRequest;
import xmlb.service.CertificateService;
import xmlb.service.RevokeService;

import javax.validation.Valid;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private RevokeService revokeService;

    @RequestMapping(value= "/{alias}/search",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pretrazuje sertifikate", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateInfo>> search(@PathVariable(value="alias") String alias) {
        return new ResponseEntity<>(certificateService.search(alias),HttpStatus.OK);
    }

    @RequestMapping(value= "/{alias}/search_without_leafs",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pretrazuje sertifikate", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateInfo>> searchWithoutLeafs(@PathVariable(value="alias") String alias) {
        return new ResponseEntity<>(certificateService.search2(alias),HttpStatus.OK);
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
        if(certificateInfo.getLeaf()){
            Revoke leaf=new Revoke();
            leaf.setLeaf(true);
            leaf.setAlias(certificateInfo.getAlias());
            revokeService.newRevoke(leaf);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

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

    @RequestMapping(value= "/all_without_leafs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata bez listova", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateInfo>> allCertificatesWithoutLeafs() {
        return new ResponseEntity<>(certificateService.allCertificatesBL(),HttpStatus.OK);
    }


    @RequestMapping(value= "/allL", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata koji nisu povuceni", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateInfo>> certificatesL() {
        ArrayList<CertificateInfo> ci=(ArrayList<CertificateInfo>) certificateService.allCertificates();
        ArrayList<CertificateInfo> pom= new ArrayList<>();
        ArrayList<String> lista= (ArrayList<String>) revokeService.getPovuceneAliasi();
        for(CertificateInfo c:ci){
            if(!lista.contains(c.getAlias()))
                pom.add(c);
        }

        return new ResponseEntity<>(pom,HttpStatus.OK);
    }

    @RequestMapping(value= "/revoke", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Povlacenje sertifikata", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<String> revoke(@RequestBody String alias) {

        Revoke revoke=new Revoke();
        revoke.setAlias(alias);
        revoke.setLeaf(false);
        ArrayList<String> lista= (ArrayList<String>) revokeService.getPovuceneAliasi();
        if(!lista.contains(revoke.getAlias())) {
            revokeService.newRevoke(revoke);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
