package modul.backend.controller;

import modul.backend.dto.AccommodationTypeDTO;
import modul.backend.dto.ExtraOptionDTO;
import modul.backend.dto.ObjectDTO;
import modul.backend.model.AccommodationType;
import modul.backend.model.ExtraOption;
import modul.backend.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/object")
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    @RequestMapping( value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectDTO> findById(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(objectService.findById(id), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ObjectDTO>> getAll() {

        return new ResponseEntity<>(objectService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/extra_options", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExtraOptionDTO>> getExtraOptions(@PathVariable(value="id") Long id) {

        return new ResponseEntity<>(objectService.getExtraOptions(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/accommodation_types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccommodationTypeDTO>> getAccommodationTypes(@PathVariable(value="id") Long id) {

        return new ResponseEntity<>(objectService.getAccommodationTypes(id), HttpStatus.OK);
    }
}
