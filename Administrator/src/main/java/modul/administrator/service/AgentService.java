package modul.administrator.service;

import modul.administrator.dto.AgentDTO;
import modul.administrator.model.Agent;
import modul.administrator.model.Role;
import modul.administrator.model.VerificationToken;
import modul.administrator.repository.AgentRepository;
import modul.administrator.repository.RoleRepository;
import modul.administrator.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        agent.setEnabled(false);
        agent.setVerified(false);

        Role roleReg = roleRepository.findByName("ROLE_REG")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        Role roleAgent = roleRepository.findByName("ROLE_AGENT")
                .orElseThrow(() -> new RuntimeException("Role can't be found!"));

        ArrayList<Role> roles = new ArrayList<>();
        
        roles.add(roleReg);
        roles.add(roleAgent);

        agent.setRoles(roles);

        agentRepository.save(agent);

        String token = UUID.randomUUID().toString();

        VerificationToken myToken = new VerificationToken(token, agent);
        agent.setVerificationToken(myToken);
        tokenRepository.save(myToken);

        emailSenderService.sendCompleteRegistration(agent);

        return new AgentDTO(agent);
    }


}
