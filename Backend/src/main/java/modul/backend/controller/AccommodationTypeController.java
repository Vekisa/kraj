package modul.backend.controller;

import modul.backend.dto.AccommodationTypeDTO;
import modul.backend.service.AccommodationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/accommodation_type")
public class AccommodationTypeController {

    @Autowired
    private AccommodationTypeService accommodationTypeService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccommodationTypeDTO>> getAll(){

        return new ResponseEntity<>(accommodationTypeService.getAll(), HttpStatus.OK);
    }
}
