package module.agent.services;

import module.agent.model.Plan;
import module.agent.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Plan create(Plan plan){
        return planRepository.save(plan);
    }
}
