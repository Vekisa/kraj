package modul.administrator.service;

import modul.administrator.dto.AgentDTO;
import modul.administrator.model.Agent;
import modul.administrator.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<AgentDTO> getAll(){
        return DTOList.agents(agentRepository.findAll());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public AgentDTO addAgent(AgentDTO agentDTO){
        Agent agent = new Agent();
        agent.setFirstName(agentDTO.getFirstName());
        agent.setLastName(agentDTO.getLastName());
        agent.setEmail(agentDTO.getEmail());
        agent.setBussinesRegistrationNumber(agentDTO.getBussinesRegistrationNumber());

        agentRepository.save(agent);

        emailSenderService.sendAgentRequest(agentDTO.getEmail());

        return new AgentDTO(agent);
    }

}
