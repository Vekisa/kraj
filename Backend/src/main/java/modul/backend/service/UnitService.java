package modul.backend.service;

import modul.backend.dto.UnitDTO;
import modul.backend.model.*;
import modul.backend.model.Object;
import modul.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private MessageRepository messageRepository;

    public UnitDTO findById(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UnitWS does not exist!");

        return new UnitDTO(unit.get());
    }

    public List<UnitDTO> getAll(){
        return DTOList.units(unitRepository.findAll());
    }

    public List<Comment> getComments(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UnitWS does not exist!");

        List<Comment> comments = commentRepository.getForUnit(id);

        return comments;
    }

    public List<Plan> getPlanForYear(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UnitWS does not exit!");

        PriceSchedule pS = null;
        if(!unit.get().getPriceSchedule().isEmpty()){
            System.out.println("NISAM PRAZAN");
            pS = unit.get().getPriceSchedule().get(0);
            for(PriceSchedule priceSchedule : unit.get().getPriceSchedule())
                if(pS.getMade().before(priceSchedule.getMade()))
                    pS = priceSchedule;

            List<Plan> plans = new ArrayList<>();

            for(Plan p : pS.getPlan()) {
                System.out.println("POREDIM: " + p.getToDate() + " " + new Date());
                if (p.getToDate().after(new Date())) {
                    System.out.println("UBACIO");
                    plans.add(p);
                }
            }

            return plans;
        }

        return new ArrayList<>();

    }

    public Message createMessage(Long id, Message message, OAuth2Authentication oAuth2Authentication){
//        Optional<Unit> unit = unitRepository.findById(id);
//        if(!unit.isPresent())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UnitWS does not exist");
//        RegisteredUser user = getRegisteredUser(oAuth2Authentication);
//
//        if(!canUserSendMessage(unit.get(),user))
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot send message!");
//
//        Optional<Agent> agent = agentRepository.findById(unit.get().getAgent().getId());
//        if(!agent.isPresent())
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Something went wrong!");
//
//        message.setRegisteredUser(user);
//        message.setAgent(agent.get());
//        message.setPostingDate(new Date());
//        message.setSeen(false);
//        message.setFromUser(user.getId());
//        messageRepository.save(message);
//
//        return message;
        return null;
    }

    public Comment createComment(Long id, Comment comment,OAuth2Authentication oAuth2Authentication){
        RegisteredUser user = getRegisteredUser(oAuth2Authentication);
        Optional<Unit> unit = unitRepository.findById(id);
        Optional<Object> object = objectRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"UnitWS does not exist!");

        if(!canUserComment(unit.get(),user))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannor comment this unit!");

        comment.setRegisteredUser(user);
        comment.setDateOfPublication(new Date());
        comment.setObject(object.get());
        comment.setApproved(false);
        commentRepository.save(comment);
        return comment;
    }

    boolean canUserComment(Unit unit,RegisteredUser user){
        for(Reservation reservation : user.getReservation()){
            if(reservation.getRegisteredUser().getId() == user.getId() && reservation.isConfirmed() && reservation.getEnd().before(new Date()))
                return true;
        }
        return false;
    }

    boolean canUserSendMessage(Unit unit,RegisteredUser user){
        for(Reservation reservation : user.getReservation()){
            if(reservation.getUnit().getId() == unit.getId())
                return true;
        }
        return false;
    }

    public RegisteredUser getRegisteredUser(OAuth2Authentication oAuth2Authentication){

        String username = oAuth2Authentication.getUserAuthentication().getName();

        Optional<RegisteredUser> user = registeredUserRepository.findByUsername(username);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!");
        return user.get();
    }
}
