package module.agent.services;

import module.agent.model.Message;
import module.agent.model.Users.RegisteredUser;
import module.agent.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public List<Message> fromUser(Long id){
        List<Message> mes=new ArrayList<>();
        for(Message m: messageRepository.findAll()){
            if(m.getRegisteredUser().getId()==id)
                mes.add(m);
        }
        return mes;
    }

    public List<RegisteredUser> users(){
        List<RegisteredUser> mes=new ArrayList<>();
        for(Message m: messageRepository.findAll()){
            if(!mes.contains(m.getRegisteredUser()))
                mes.add(m.getRegisteredUser());
        }
        return mes;
    }
}
