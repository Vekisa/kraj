package modul.rtng.controller;

import modul.rtng.PomDTO.Pom;
import modul.rtng.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value= "/{object_id}/{user_id}/{mark}",method = RequestMethod.PUT)
    public ResponseEntity<?> rate(@PathVariable(value="object_id") Long objectId, @PathVariable(value = "user_id") Long userId,@PathVariable(value = "mark") Integer mark){
        
        return new ResponseEntity<>(ratingService.rate(objectId,userId,mark),HttpStatus.OK);
    }

    @RequestMapping(value= "/{object_id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getRatingForObject(@PathVariable(value="object_id") Long objectId){

        return new ResponseEntity<>(ratingService.getRatingForObject(objectId),HttpStatus.OK);
    }

    @RequestMapping(value= "/list_of_ratings",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pom>> getRatingForObject(@RequestBody List<Long> objects){

        return new ResponseEntity<>(ratingService.getListOfRatings(objects),HttpStatus.OK);
    }
}
