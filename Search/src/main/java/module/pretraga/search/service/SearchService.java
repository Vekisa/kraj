package module.pretraga.search.service;

import module.pretraga.search.model.Search;
import module.pretraga.search.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;


    public ArrayList<Search> getall(){
        return (ArrayList<Search>) searchRepository.findAll();
    }

    public ArrayList<Search> findByPersons(Double persons){
       return searchRepository.findByPersons(persons);
    }

    public ArrayList<Search> findByCategory(int category){
        return searchRepository.findByCategory(category);

    }

    public ArrayList<Search> findByDistance(Long distance){
        return searchRepository.findByDistance(distance);

    }


    public ArrayList<Search> findByStart(Date start){
        return searchRepository.findByStart(start);
    }

    public Optional<Search> findById(Long id){
        return searchRepository.findById(id);
    }

}
