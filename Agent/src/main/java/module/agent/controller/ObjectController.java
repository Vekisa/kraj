package module.agent.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import module.agent.model.Adress;
import module.agent.model.Object;
import module.agent.model.Unit;
import module.agent.services.AdressService;
import module.agent.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/object")
public class ObjectController {
    @Autowired
    private ObjectService objectService;

    @Autowired
    private AdressService adressService;

    @RequestMapping(value = "/create_new_object", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje objekta", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Object> createObject(@RequestBody Object object){
        System.out.println("Uslo object    " );
        return new ResponseEntity<>(objectService.create(object), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Svi objekti", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Object.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Object>> getAll(){
        System.out.println("Uslo object getALl");
        return new ResponseEntity<>(objectService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create_new_address", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje adrese", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Adress.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Adress> createaAddress(@RequestBody Adress address){
        System.out.println("Uslo address    " );
        return new ResponseEntity<>(adressService.create(address), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getUnits/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Sve jedinice u objektu", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Unit.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Unit>> getUnits(@PathVariable Long id){
        System.out.println("Uslo object getALl units");
        return new ResponseEntity<>(objectService.getUnits(id), HttpStatus.OK);
    }
}
