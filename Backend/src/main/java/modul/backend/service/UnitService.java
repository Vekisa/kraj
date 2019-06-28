package modul.backend.service;

import modul.backend.dto.UnitDTO;
import modul.backend.model.*;
import modul.backend.repository.CommentRepository;
import modul.backend.repository.RegisteredUserRepository;
import modul.backend.repository.UnitRepository;
import modul.backend.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public UnitDTO findById(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit does not exist!");

        return new UnitDTO(unit.get());
    }

    public List<UnitDTO> getAll(){
        return DTOList.units(unitRepository.findAll());
    }

    public List<Comment> getComments(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit does not exist!");

        List<Comment> comments = commentRepository.getForUnit(id);

        return comments;
    }

    public List<Plan> getPlanForYear(Long id){
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit does not exit!");

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

    public Comment createComment(Long id, Comment comment,OAuth2Authentication oAuth2Authentication){
        RegisteredUser user = getRegisteredUser(oAuth2Authentication);
        Optional<Unit> unit = unitRepository.findById(id);
        if(!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Unit does not exist!");

        if(!canUserComment(unit.get(),user))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannor comment this unit!");

        comment.setRegisteredUser(user);
        comment.setDateOfPublication(new Date());
        comment.setUnit(unit.get());
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

    public RegisteredUser getRegisteredUser(OAuth2Authentication oAuth2Authentication){

        String username = oAuth2Authentication.getUserAuthentication().getName();

        Optional<RegisteredUser> user = registeredUserRepository.findByUsername(username);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!");
        return user.get();
    }
}
