package xmlb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xmlb.dto.CertificateDTO;
import xmlb.model.Communication;
import xmlb.service.CommunicationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/communication")
public class CommunicationController {

    @Autowired
    private CommunicationService communicationService;

    @RequestMapping(value= "/{first_alias}/{second_alias}/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Kreiranje komunikacije", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Communication.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Communication> createCommunication(@PathVariable(value="first_alias") String first_alias, @PathVariable(value="second_alias") String second_alias ) {
        return new ResponseEntity<>(communicationService.createCommunication(first_alias,second_alias), HttpStatus.CREATED);
    }

    @RequestMapping(value= "/{first_alias}/{second_alias}/disable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Brisanje komunikacije", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<?> delete(@PathVariable(value="first_alias") String first, @PathVariable(value="second_alias") String second) {
        communicationService.delete(first,second);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value= "/{alias}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Vraca sve komunikacije za prosledjeni sertifikat", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<CertificateDTO>> getCommunicationsOfCertificate(@PathVariable(value="alias") String alias ) {
        return new ResponseEntity<>(communicationService.getCommunicationsOfCertificate(alias),HttpStatus.OK);
    }
}
