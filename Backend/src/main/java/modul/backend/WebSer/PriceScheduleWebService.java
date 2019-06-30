package modul.backend.WebSer;

import modul.backend.model.Plan;
import modul.backend.model.PriceSchedule;

import java.util.List;

public interface PriceScheduleWebService {

    List<PriceSchedule> getAll();
    PriceSchedule getOne(long id);
    boolean addOne(PriceSchedule priceSchedule);
    boolean updateOne(PriceSchedule priceSchedule);

    Plan getPlan(long id);
    boolean updatePlan(Plan plan);
}
