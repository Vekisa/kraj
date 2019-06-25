package modul.administrator.service;

import modul.administrator.dto.AgentDTO;
import modul.administrator.model.Users.Agent;
import modul.administrator.model.Users.Role;
import modul.administrator.model.Users.VerificationToken;
import modul.administrator.repository.AgentRepository;
import modul.administrator.repository.RoleRepository;
import modul.administrator.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Autowired
    private RestTemplate restTemplate;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<AgentDTO> getAll(){
        return DTOList.agents(agentRepository.findAll());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String addAgent(AgentDTO agentDTO){

        agentDTO.setPassword(generatePassword(10));

//        Role roleReg = roleRepository.findByName("ROLE_REG")
//                .orElseThrow(() -> new RuntimeException("Role can't be found!"));
//
//        Role roleAgent = roleRepository.findByName("ROLE_AGENT")
//                .orElseThrow(() -> new RuntimeException("Role can't be found!"));
//
//        ArrayList<Role> roles = new ArrayList<>();
//
//        roles.add(roleReg);
//        roles.add(roleAgent);
//
//        agent.setRoles(roles);
//
//        agentRepository.save(agent);
//
//        String token = UUID.randomUUID().toString();
//
//        VerificationToken myToken = new VerificationToken(token, agent);
//        agent.setVerificationToken(myToken);
//        tokenRepository.save(myToken);
//
//        emailSenderService.sendCompleteRegistration(agent);

        HttpEntity<AgentDTO> requestEntity = new HttpEntity<>(agentDTO);

        ResponseEntity<String> exchange = restTemplate.exchange("http://oauth-service/user/saveAgent", HttpMethod.POST, requestEntity, String.class);

        System.out.println(exchange.getBody());

        return exchange.getBody();
    }


    public static String generatePassword(int length) {
        Random RANDOM = new SecureRandom();
        String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }


}
