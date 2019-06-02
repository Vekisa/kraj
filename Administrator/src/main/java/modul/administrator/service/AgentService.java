package modul.administrator.service;

import modul.administrator.dto.AgentDTO;
import modul.administrator.model.Adress;
import modul.administrator.model.Agent;
import modul.administrator.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public List<AgentDTO> getAll(){
        return DTOList.agents(agentRepository.findAll());
    }

    public AgentDTO addAgent(AgentDTO agentDTO){
        Agent agent = new Agent();
        agent.setFirstName(agentDTO.getFirstName());
        agent.setLastName(agentDTO.getLastName());
        //DODATI ADRESU
        agent.setBussinesRegistrationNumber(agentDTO.getBussinesRegistrationNumber());

        agentRepository.save(agent);

        return new AgentDTO(agent);
    }

}
