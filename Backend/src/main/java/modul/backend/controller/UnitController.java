package modul.backend.controller;
import modul.backend.model.Comment;
import modul.backend.model.Message;
import modul.backend.model.Plan;
import modul.backend.service.UnitService;
import modul.backend.dto.UnitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnitDTO> findById(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(unitService.findById(id), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitDTO>> getAll() {

        return new ResponseEntity<>(unitService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/price_schedule", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Plan>> getPlanForYear(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(unitService.getPlanForYear(id), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Comment>> getComments(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(unitService.getComments(id), HttpStatus.OK);
    }

    @RequestMapping(value="/{unit_id}/comment", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> createComment(@PathVariable(value = "unit_id") Long unitId, @RequestBody Comment comment, OAuth2Authentication oAuth2Authentication) {

        return new ResponseEntity<>(unitService.createComment(unitId,comment,oAuth2Authentication),HttpStatus.OK);
    }

    @RequestMapping(value="/{unit_id}/message", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> creatMessage(@PathVariable(value = "unit_id") Long unitId, @RequestBody Message message, OAuth2Authentication oAuth2Authentication) {

        return new ResponseEntity<>(unitService.createMessage(unitId,message,oAuth2Authentication),HttpStatus.OK);
    }

}
