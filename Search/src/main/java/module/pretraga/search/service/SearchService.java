 package module.pretraga.search.service;


import module.pretraga.search.dto.UnitDTO;
import module.pretraga.search.model.ExtraOption;
import module.pretraga.search.model.Reservation;
import module.pretraga.search.model.Object;
import module.pretraga.search.model.Unit;
import module.pretraga.search.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private UnitRepository unitRepository;

    public List<UnitDTO> search(String city, Date startDate, Date endDate, Integer persons, Long accommodatioTypeId, Integer category, Float distance, ArrayList<Long> extraOptions){
        if(city == null || startDate == null || endDate == null || persons == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        boolean index = true;

        System.out.println("Ovo: " + city + " " + startDate + " " + endDate + " " + persons + " " + accommodatioTypeId + " " +  category + " " + distance);

        List<Unit> units = unitRepository.findByCityAndPersons(city,persons);
        List<Unit> freeUnits = new ArrayList<>();
        for(Unit unit : units) {
            index = true;
            if (isUnitFree(unit, startDate, endDate))
                continue;

            if (accommodatioTypeId != null)
                if (unit.getAccommodationType().getId() != accommodatioTypeId)
                    continue;

            if(category!= null)
                if(unit.getObject().getCategory() != category)
                    continue;

            //Treba ubacti za udaljenost

            if(!extraOptions.isEmpty()){
                for(Long extraOptionId : extraOptions){
                    if(!containsExtraOption(extraOptionId,unit.getObject()))
                        index = false;
                }

                if(!index)
                    continue;
            }

            freeUnits.add(unit);

        }

        return DTOList.units(freeUnits);
    }

    private Boolean isUnitFree(Unit unit, Date startDate, Date endDate){

        for(Reservation reservation : unit.getReservation()){
            if((reservation.getStart().before(reservation.getStart()) && reservation.getStart().before(reservation.getEnd()))
                    || (reservation.getStart().before(reservation.getEnd()) && reservation.getEnd().after(reservation.getEnd()))
                    || (reservation.getStart().before(reservation.getStart()) && reservation.getEnd().after(reservation.getEnd())))
                return false;
        }

        return true;
    }

    private Boolean containsExtraOption(Long id, Object object){
        for(ExtraOption extraOption : object.getExtraOption())
            if(id == extraOption.getId())
                return true;

        return false;
    }


}
