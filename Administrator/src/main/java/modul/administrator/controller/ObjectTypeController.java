package modul.administrator.controller;

import modul.administrator.dto.ObjectTypeDTO;
import modul.administrator.service.ObjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/object_type")
public class ObjectTypeController {

    @Autowired
    private ObjectTypeService objectTypeService;

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ObjectTypeDTO>> getAll() {

        return new ResponseEntity<>(objectTypeService.getAll(), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectTypeDTO> create(@RequestBody ObjectTypeDTO objectTypeDTO) {

        return new ResponseEntity<>(objectTypeService.create(objectTypeDTO), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectTypeDTO> update(@RequestBody ObjectTypeDTO objectTypeDTO) {

        return new ResponseEntity<>(objectTypeService.update(objectTypeDTO), HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        objectTypeService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
