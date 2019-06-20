package modul.search.service;

import modul.search.dto.UnitDTO;
import modul.search.model.Adress;
import modul.search.model.ExtraOption;
import modul.search.model.Reservation;
import modul.search.model.Unit;
import modul.search.repository.AddressRepository;
import modul.search.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<UnitDTO> search(String city, Date startDate, Date endDate, Integer persons, Long addressId, Long accommodationTypeId, Integer category, Float distance, ArrayList<Long> extraOptions){
        System.out.println("PRISTIGLO: " + city + " " + startDate + " " + endDate + " " + persons + " " + accommodationTypeId + " " + category + " " + distance + " " + extraOptions);
        if(city == null || startDate == null || endDate == null || persons == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");


        List<Unit> units = unitRepository.findAll();
        List<Unit> okUnits = new ArrayList<>();

        for(Unit unit : units){
            if(unit.getObject().getAdress().getCity() != city)
                continue;
            if(!checkReservation(unit,startDate,endDate))
                continue;

            if(unit.getAdults().intValue() <  persons)
                continue;

            if(accommodationTypeId != null){
                if(unit.getAccommodationType().getId() != accommodationTypeId)
                    continue;
            }

            if(category != null){
                if(unit.getObject().getCategory() != category)
                    continue;
            }

            if(distance != null){
                Optional<Adress> address = addressRepository.findById(addressId);
                if(!address.isPresent())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found");

                if(distance(address.get().getLatitude().doubleValue(),address.get().getLongitude().doubleValue(),
                        unit.getObject().getAdress().getLatitude().doubleValue(),unit.getObject().getAdress().getLongitude().doubleValue()) > distance)
                    continue;

            }

            Boolean flag = true;
            if(extraOptions != null){
                for(Long id : extraOptions)
                    if(!hasExtraOption(id,unit))
                        flag = false;

                continue;
            }

            okUnits.add(unit);

        }
        return DTOList.units(okUnits);
    }

    private Boolean checkReservation(Unit unit, Date startDate, Date endDate){
        for(Reservation r : unit.getReservation()){
            if((r.getStart().before(startDate) && startDate.before(r.getEnd()))
                    || (r.getStart().before(endDate) && r.getEnd().after(endDate))
                    || (r.getStart().before(startDate) && r.getEnd().after(endDate)))
                return false;
        }
        return true;
    }

    private Boolean hasExtraOption(Long extraOptionId,Unit unit){
        for(ExtraOption extraOption : unit.getObject().getExtraOption())
            if(extraOption.getId() == extraOptionId)
                return true;

        return false;
    }

    public static double distance(double lat1, double lng1,
                                  double lat2, double lng2){
        double a = (lat1-lat2)*distPerLat(lat1);
        double b = (lng1-lng2)*distPerLng(lat1);
        return Math.sqrt(a*a+b*b);
    }

    private static double distPerLng(double lat){
        return 0.0003121092*Math.pow(lat, 4)
                +0.0101182384*Math.pow(lat, 3)
                -17.2385140059*lat*lat
                +5.5485277537*lat+111301.967182595;
    }

    private static double distPerLat(double lat){
        return -0.000000487305676*Math.pow(lat, 4)
                -0.0033668574*Math.pow(lat, 3)
                +0.4601181791*lat*lat
                -1.4558127346*lat+110579.25662316;
    }
}