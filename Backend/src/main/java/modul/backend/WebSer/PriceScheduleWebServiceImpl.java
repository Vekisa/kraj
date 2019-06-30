package modul.backend.WebSer;

import modul.backend.model.Plan;
import modul.backend.model.PriceSchedule;
import modul.backend.repository.PlanRepository;
import modul.backend.repository.PriceScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceScheduleWebServiceImpl implements PriceScheduleWebService{

    @Autowired
    PriceScheduleRepository priceScheduleRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public List<PriceSchedule> getAll() {

        ArrayList<PriceSchedule> priceSchedules = new ArrayList<>();

        for (PriceSchedule p : priceScheduleRepository.findAll()){
            priceSchedules.add(p);
        }

        return priceSchedules;
    }

    @Override
    public PriceSchedule getOne(long id) {
        Optional<PriceSchedule> priceSchedule = priceScheduleRepository.findById(id);

        if (!priceSchedule.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PriceSchedule does not exist");
        }
        return  priceSchedule.get();
    }

    @Override
    public boolean addOne(PriceSchedule priceSchedule) {
        if (priceSchedule==null) {
            return false;

        }

        priceScheduleRepository.save(priceSchedule);
        return true;
    }

    @Override
    public boolean updateOne(PriceSchedule priceSchedule) {

        priceScheduleRepository.save(getOne(priceSchedule.getId()));
        return true;
    }

    @Override
    public Plan getPlan(long id) {

        Optional<Plan> plan = planRepository.findById(id);

        if (!plan.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plan does not exist");
        }

        return plan.get();


    }

    @Override
    public boolean updatePlan(Plan plan) {

        Plan pl = getPlan(plan.getId());

        planRepository.save(pl);
        return true;
    }
}
