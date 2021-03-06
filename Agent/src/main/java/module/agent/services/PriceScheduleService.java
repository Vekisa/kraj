package module.agent.services;

import module.agent.model.PriceSchedule;
import module.agent.repository.PriceScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PriceScheduleService {

    @Autowired
    private PriceScheduleRepository priceScheduleRepository;


    public PriceSchedule create(PriceSchedule priceSchedule){
        priceSchedule.setMade(new Date());
        return priceScheduleRepository.save(priceSchedule);
    }
}
