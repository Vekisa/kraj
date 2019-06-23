package modul.backend.controller;

import modul.backend.dto.ExtraOptionDTO;
import modul.backend.service.ExtraOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/extra_option")
public class ExtraOptionController {

    @Autowired
    private ExtraOptionService extraOptionService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ExtraOptionDTO>> getAll(){

        return new ResponseEntity<>(extraOptionService.getAll(), HttpStatus.OK);
    }

}
