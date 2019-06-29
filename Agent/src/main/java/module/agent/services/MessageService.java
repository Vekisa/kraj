package module.agent.services;

import module.agent.model.Agent;
import module.agent.model.Message;
import module.agent.model.RegisteredUser;
import module.agent.model.User;
import module.agent.repository.AgentRepository;
import module.agent.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AgentRepository agentRepository;

    public Message createMessage(Message message, OAuth2Authentication oAuth2Authentication){
        message.setFromUser(userService.getUser(oAuth2Authentication.getName()).getId());
        return messageRepository.save(message);
    }

    public List<Message> getMessages(OAuth2Authentication oAuth2Authentication)
    {   List<Message> m=new ArrayList<>();
        User u=userService.getUser(oAuth2Authentication.getName());

        for(Message me: messageRepository.findAll()){
            if(me.getAgent().getId()==u.getId())
                m.add(me);
        }
        return m;
    }

    public List<Message> fromUser(Long id, OAuth2Authentication oAuth2Authentication){
        List<Message> mes=new ArrayList<>();
        User u=userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.getId()).get();
        for(Message m: a.getMessage()){
            if(m.getRegisteredUser().getId()==id){
                System.out.println("mes " + m.getText());
                 mes.add(m);
            }

        }

        Collections.sort(mes, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if (o1.getPostingDate() == null || o2.getPostingDate() == null)
                    return 0;
                return o1.getPostingDate().compareTo(o2.getPostingDate());
            }
        });

        for(Message m : mes){
            System.out.println(m.getText() + "   " + m.getPostingDate());
        }
        return mes;
    }

    public List<RegisteredUser> users(OAuth2Authentication oAuth2Authentication){
        List<RegisteredUser> mes=new ArrayList<>();
        User u=userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.getId()).get();

        for(Message m: a.getMessage()){
            if(!mes.contains(m.getRegisteredUser())) {
                mes.add(m.getRegisteredUser());
            }
        }
        return mes;
    }

    public boolean messageSeen(Message m){
        m.setSeen(true);
        messageRepository.save(m);
        return true;
    }


}
