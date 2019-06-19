package modul.rating.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/rating")
public class RatingController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String findById() {

        return "Rating system";
    }
}