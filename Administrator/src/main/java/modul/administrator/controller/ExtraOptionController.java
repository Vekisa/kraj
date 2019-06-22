package modul.administrator.controller;

import modul.administrator.dto.ExtraOptionDTO;
import modul.administrator.service.ExtraOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/extra_option")
public class ExtraOptionController {

    @Autowired
    private ExtraOptionService extraOptionService;

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExtraOptionDTO>> getAll() {

        return new ResponseEntity<>(extraOptionService.getAll(), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExtraOptionDTO> create(@RequestBody ExtraOptionDTO extraOptionDTO) {

        return new ResponseEntity<>(extraOptionService.create(extraOptionDTO), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExtraOptionDTO> update(@RequestBody ExtraOptionDTO extraOptionDTO) {

        return new ResponseEntity<>(extraOptionService.update(extraOptionDTO), HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        extraOptionService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
