package modul.backend.WebSer;

import modul.backend.model.Object;
import modul.backend.model.Unit;
import modul.backend.model.web.UnitWS;

import java.util.List;

public interface UnitWebService {

    List<Unit> getAllUnits();
    UnitWS getUnitById(long unitId);
    void addUnit(UnitWS unitWS);
    boolean updateUnit(UnitWS unitWS);
    boolean deleteUnit(long id);


}
