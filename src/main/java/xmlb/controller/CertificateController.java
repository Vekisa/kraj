package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlb.model.Certificate;
import xmlb.dto.CertificateDTO;
import xmlb.security.ResponseMessage;
import xmlb.service.CertificateService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @RequestMapping(value= "/{alias}/search",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Pretrazuje sertifikate", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> search(@PathVariable(value="alias") String alias) {
        return new ResponseEntity<>(certificateService.search(alias),HttpStatus.OK);
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
    public ResponseEntity<String> createNewSSCertificate(@RequestBody CertificateDTO certificateDTO) {
        System.out.println("Create new certificate!");
        certificateService.createNewSelfSignedCertificate(certificateDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value= "/create_new_certificate",method = RequestMethod.POST)
    @ApiOperation(value="Kreira novi sertifikat", httpMethod = "POST")
    public ResponseEntity<String> createNewCertificate(@RequestBody CertificateDTO certificateDTO) {
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

    @RequestMapping(value= "/all_without_leafs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata bez listova", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificatesWithoutLeafs() {
        return new ResponseEntity<>(certificateService.allCertificatesWithoutLeafs(),HttpStatus.OK);
    }

    @RequestMapping(value= "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> allCertificates() {
        ArrayList<CertificateDTO> ci=(ArrayList<CertificateDTO>) certificateService.allCertificates();
        ArrayList<CertificateDTO> pom= new ArrayList<>();
        /*ArrayList<String> lista= (ArrayList<String>) revokeService.getAliase();
        for(CertificateDTO c:ci){
            if(!c.getAlias().equals(".DS_S")){
                if(certificateService.checkIfValid(c.getAlias())){
                    pom.add(c);
             }
            }
        }*/

        return new ResponseEntity<>(pom,HttpStatus.OK);
    }

    @RequestMapping(value= "/allDb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Certificate>> allCertificatesDB() {

        List<Certificate> lista = certificateService.allCertificatesDB();

        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @RequestMapping(value= "/allL", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Prikaz sertifikata koji nisu povuceni", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> certificatesL() {
        ArrayList<CertificateDTO> ci=(ArrayList<CertificateDTO>) certificateService.allCertificates();
        ArrayList<CertificateDTO> pom= new ArrayList<>();
        /*ArrayList<String> lista= (ArrayList<String>) revokeService.getPovuceneAliasi();
        for(CertificateDTO c:ci){
            if(!lista.contains(c.getAlias()))
                pom.add(c);
        }*/

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

        //Revoke revoke=new Revoke();
        //revoke.setAlias(alias);
        //revoke.setLeaf(false);
        //ArrayList<String> lista= (ArrayList<String>) revokeService.getPovuceneAliasi();
        //if(!lista.contains(revoke.getAlias())) {
            //revokeService.newRevoke(revoke);
            //return new ResponseEntity<>(HttpStatus.OK);
        //}
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value= "/checkIfValid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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

    }

}
