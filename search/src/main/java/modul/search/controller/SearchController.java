package modul.search.controller;

import modul.search.dto.UnitDTO;
import modul.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UnitDTO>> findById(@RequestParam(value = "city") String city,
                                                  @RequestParam(value = "start_date") @DateTimeFormat(pattern="MMddyyyy") Date startDate,
                                                  @RequestParam(value = "end_date") @DateTimeFormat(pattern="MMddyyyy") Date endDate,
                                                  @RequestParam(value = "persons") Integer persons,
                                                  @RequestParam(value = "distance") Long addressId,
                                                  @RequestParam(value = "accommodation_type_id", required=false) Long accommodationTypeId,
                                                  @RequestParam(value = "category", required=false) Integer category,
                                                  @RequestParam(value = "distance", required=false) Float distance,
                                                  @RequestBody(required=false) ArrayList<Long> extraOptions) {

        return new ResponseEntity<>(searchService.search(city,startDate,endDate,persons,addressId,accommodationTypeId,category,distance,extraOptions), HttpStatus.OK);
    }
}
