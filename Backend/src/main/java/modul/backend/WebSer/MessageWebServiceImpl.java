package modul.backend.WebSer;

import modul.backend.model.Agent;
import modul.backend.model.Message;
import modul.backend.model.RegisteredUser;
import modul.backend.model.User;
import modul.backend.model.web.MessageWS;
import modul.backend.repository.AgentRepository;
import modul.backend.repository.MessageRepository;
import modul.backend.repository.RegisteredUserRepository;
import modul.backend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageWebServiceImpl implements MessageWebService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;


    @Override
    public List<MessageWS> getAll() {

        List<Message> messages =  messageRepository.findAll();
        List<MessageWS> messageWS = new ArrayList<>();


        for (Message m : messages){

            MessageWS temp = new MessageWS();

            BeanUtils.copyProperties(m,temp);

            temp.setAgentId(m.getAgent().getId());
            temp.setRegisteredUserId(m.getRegisteredUser().getId());
            messageWS.add(temp);
        }

        return messageWS;

    }

    @Override
    public boolean sendMessage(MessageWS messageWS) {

        if (messageWS==null){
            return false;
        }

        Message m = new Message();

        BeanUtils.copyProperties(messageWS,m);

        Optional<Agent> agent = agentRepository.findById(messageWS.getAgentId());

        Optional<RegisteredUser> reg =registeredUserRepository.findById(messageWS.getRegisteredUserId());

        if ( !agent.isPresent() || !reg.isPresent()){
            return false;
        }



        m.setAgent(agent.get());
        m.setRegisteredUser(reg.get());

        messageRepository.save(m);

        return true;
    }
}
