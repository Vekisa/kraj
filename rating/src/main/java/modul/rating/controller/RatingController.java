package modul.rating.controller;

import modul.rating.dto.RatingDTO;
import modul.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value= "/{object_id}/{mark}",method = RequestMethod.PUT)
    public ResponseEntity<?> rate(@PathVariable(value="object_id") Long objectId, @PathVariable(value = "mark") Integer mark){

        ratingService.rate(objectId,mark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value= "/all/{object_id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RatingDTO>> getRatingsForObject(@PathVariable(value="object_id") Long objectId){

        return new ResponseEntity<>(ratingService.getRatingsForObject(objectId),HttpStatus.OK);
    }

    @RequestMapping(value= "/{object_id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getRatingForObject(@PathVariable(value="object_id") Long objectId){

        return new ResponseEntity<>(ratingService.getRatingForObject(objectId),HttpStatus.OK);
    }
}