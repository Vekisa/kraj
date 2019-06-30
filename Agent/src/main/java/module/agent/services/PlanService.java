package module.agent.services;

import module.agent.model.Plan;
import module.agent.repository.AgentRepository;
import module.agent.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;


    public Plan create(Plan plan){

        if(plan.getFromDate().after(plan.getToDate()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "From date must be before to date ");
        return planRepository.save(plan);
    }
}
