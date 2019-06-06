package module.pretraga.search.repository;

import module.pretraga.search.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Date;

public interface SearchRepository extends JpaRepository<Search, Long> {

    public ArrayList<Search> findByPersons(Double persons);
    public ArrayList<Search> findByStart(Date start);

    public ArrayList<Search> findByCategory(int category);

    public ArrayList<Search> findByDistance(Long distance);
}