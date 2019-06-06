package module.pretraga.search.controller;


import module.pretraga.search.model.Search;
import module.pretraga.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@CrossOrigin(origins = "https://localhost:4200")
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/hotel")
    public ArrayList<Search> getResult() {
        return searchService.getall();
    }

    @RequestMapping("/{persons}/persons")
    public ArrayList<Search> getResult(@PathVariable("persons") Double persons) {
        return searchService.findByPersons(persons);
    }

    @RequestMapping("/{category}/category")
    public ArrayList<Search> getResult(@PathVariable("category") int category) {
        return searchService.findByCategory(category);
    }

    @RequestMapping("/{distance}/distance")
    public ArrayList<Search> getResult(@PathVariable("distance") Long distance) {
        return searchService.findByDistance(distance);
    }

/*
    @RequestMapping("/{start}/start")
    public ArrayList<Search> getResult(@PathVariable("start") Date start) {
        return searchService.findByStart(start);
    }
*/
}
