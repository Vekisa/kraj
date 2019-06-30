package modul.backend.WebSer;

import modul.backend.model.Object;
import modul.backend.model.Reservation;
import modul.backend.model.Unit;
import modul.backend.model.web.UnitWS;
import modul.backend.repository.AccommodationTypeRepository;
import modul.backend.repository.UnitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UnitWebServiceImpl implements UnitWebService {

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    AccommodationTypeRepository accommodationTypeRepository;

    @Autowired
    ObjectWebServiceImpl objectWebService;

    @Override
    public List<Unit> getAllUnits() {
      return unitRepository.findAll();
    }

    @Override
    public UnitWS getUnitById(long unitId) {
        Optional<Unit> unitOptional = unitRepository.findById(unitId);
        if(!unitOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UnitWS does not exist!");

        Unit unit = unitOptional.get();

        UnitWS unitWS = new UnitWS();
        BeanUtils.copyProperties(unit,unitWS);

        unitWS.setObjectId(unit.getObject().getId());
        unitWS.setAccomodationTypeId(unit.getAccommodationType().getId());
        for(Reservation r : unit.getReservation()){
            unitWS.getReservationId().add(r.getId());
        }
        return unitWS;
    }

    @Override
    public void addUnit(UnitWS unitWS) {

        Unit unit = new Unit();

        BeanUtils.copyProperties(unitWS,unit);

        unit.setAccommodationType(accommodationTypeRepository.getOne(unitWS.getId()));
        unit.setObject(objectWebService.getObjectById(unitWS.getObjectId()));

        unitRepository.save(unit);
    }

    @Override
    public boolean updateUnit(UnitWS unitWS) {

        if (unitWS==null)
            return false;

        Unit unit = unitRepository.getOne(unitWS.getId());

        BeanUtils.copyProperties(unitWS,unit);


        unitRepository.save(unit);

        return true;
    }

    @Override
    public boolean deleteUnit(long id) {

        Optional<Unit> unitOptional = unitRepository.findById(id);

        if(!unitOptional.isPresent()){
            return false;
        }else
            unitRepository.deleteById(id);

        return true;
    }
}
