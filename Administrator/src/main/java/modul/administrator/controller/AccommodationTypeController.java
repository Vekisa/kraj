package modul.administrator.controller;

import modul.administrator.dto.AccommodationTypeDTO;
import modul.administrator.dto.ObjectTypeDTO;
import modul.administrator.service.AccommodationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accommodation_type")
public class AccommodationTypeController {

    @Autowired
    private AccommodationTypeService accommodationTypeService;

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccommodationTypeDTO>> getAll() {

        return new ResponseEntity<>(accommodationTypeService.getAll(), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationTypeDTO> create(@RequestBody AccommodationTypeDTO accommodationTypeDTO) {

        return new ResponseEntity<>(accommodationTypeService.create(accommodationTypeDTO), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccommodationTypeDTO> update(@RequestBody AccommodationTypeDTO accommodationTypeDTO) {

        return new ResponseEntity<>(accommodationTypeService.update(accommodationTypeDTO), HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        accommodationTypeService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
