package module.pretraga.search.controller;


import module.pretraga.search.dto.UnitDTO;
import module.pretraga.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitDTO>> search(@RequestParam(value = "city") String city, @RequestParam(value = "startDate") Date startDate,
                                                @RequestParam(value = "endDate") Date endDate, @RequestParam(value = "persons") Integer persons,
                                                @RequestParam(value = "accommodation_type_id") Long accommodationTypeId,
                                                @RequestParam(value = "category") Integer category , @RequestParam(value = "distance") Float distance,
                                                @RequestBody ArrayList<Long> extraOptions) {

        return new ResponseEntity<>(searchService.search(city,startDate,endDate,persons,accommodationTypeId,category,distance,extraOptions), HttpStatus.OK);
    }
}
