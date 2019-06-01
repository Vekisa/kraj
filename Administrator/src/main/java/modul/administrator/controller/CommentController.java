package modul.administrator.controller;

import modul.administrator.dto.CommentDTO;
import modul.administrator.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping( value = "/approve/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> approve(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(commentService.approve(id), HttpStatus.OK);
    }

    @RequestMapping( value = "/forbid/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentDTO> forbid(@PathVariable(value = "id") Long id) {

        return new ResponseEntity<>(commentService.forbid(id), HttpStatus.OK);
    }
}
