package modul.reservation.service;

import modul.reservation.model.*;
import modul.reservation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AgentRepository agentRepository;

    public Reservation saveAgent(Reservation reservation, OAuth2Authentication oAuth2Authentication) {

        User user=userService.getUser(oAuth2Authentication.getName());
        Agent a=agentRepository.findById(user.getId()).get();
        if (!checkReservation(reservation) && !reservation.getUnit().getObject().getAgent().contains(a)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit is not available!");
        }
        reservation.setCancelled(false);
        reservation.setPossibleCancellationDate(new Date(reservation.getStart().getTime()-(reservation.getUnit().getCancellation()*24*60*60*1000)));
        reservation.setPrice(calculatePrice(reservation));
        reservation.setConfirmed(false);
        return reservationRepository.save(reservation);
    }

    public Reservation saveUser(Reservation reservation, OAuth2Authentication oAuth2Authentication) {

        if (!checkReservation(reservation)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit is not available!");
        }
        RegisteredUser registeredUser=getRegisteredUser(oAuth2Authentication.getName());
        reservation.setRegisteredUser(registeredUser);
        reservation.setCancelled(false);
        reservation.setPossibleCancellationDate(new Date(reservation.getStart().getTime()-(reservation.getUnit().getCancellation()*24*60*60*1000)));
        reservation.setPrice(calculatePrice(reservation));
        reservation.setConfirmed(false);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsForUser(OAuth2Authentication user) {
        String username = user.getUserAuthentication().getName();
        RegisteredUser userObj = getRegisteredUser(username);
        User userObj1 = userService.getUser(username);
        Optional<RegisteredUser> usr = registeredUserRepository.findById(userObj.getId());
        if (!usr.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not logged in");

        return usr.get().getReservation();
    }

    public Reservation getReservation (Long id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (!reservation.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation does not exist!");

        return reservation.get();
    }

    public RegisteredUser getRegisteredUser (String username){
        RegisteredUser user = registeredUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username or email : " + username));
        return user;
    }


    //proveriti da li brise i iz liste korisnika...
    public void delete (Long id){
        System.out.println("brisanje   " + id);
        Reservation reservation = reservationRepository.findById(id).get();
        System.out.println(reservation.getPossibleCancellationDate());
        if (reservation.getPossibleCancellationDate().before(new Date())) {
            System.out.println("Datum sada " + new Date() + "  datum za otkaz " + reservation.getPossibleCancellationDate());

            reservationRepository.delete(reservation);

        }

    }


    public Reservation confirme (Reservation reservation, Long id, OAuth2Authentication oAuth2Authentication){
        User u=userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.getId()).get();
        Optional<Reservation> pom = reservationRepository.findById(id);
        if (pom.isPresent()) {
            if(pom.get().getUnit().getObject().getAgent().contains(a)) {
                pom.get().setConfirmed(reservation.isConfirmed());
                pom.get().setCancelled(reservation.isCancelled());
                reservationRepository.save(pom.get());
                return pom.get();
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only agent of unit can update!");

            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");

    }

    public List<Reservation> findByUnit (Long id, OAuth2Authentication oAuth2Authentication){
        User u = userService.getUser(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.getId()).get();
        List<Reservation> pom = new ArrayList<>();
        for (Reservation r : reservationRepository.findAllByUnitId(id)) {
            if (r.getUnit().getObject().getAgent().contains(a)) {
                pom.add(r);
            }
        }
        return pom;
    }

    public List<Reservation> findAll (OAuth2Authentication oAuth2Authentication){
        Optional<User> u = userRepository.findByUsername(oAuth2Authentication.getName());
        Agent a= agentRepository.findById(u.get().getId()).get();
        List<Reservation> pom = new ArrayList<>();
        for (Reservation r : reservationRepository.findAll()) {
            if (r.getUnit().getObject().getAgent().contains(a)) {
                pom.add(r);
            }
        }
        return pom;
    }

    public Boolean checkReservation (Reservation reservation){
        if(reservation.getEnd().before(reservation.getStart())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Start date must be before end date!");
        }
        List<Reservation> res = reservationRepository.findAllByUnitId(reservation.getUnit().getId());
        List<Reservation> lista=new ArrayList<>();
        for(Reservation r: res){
            if(!r.isCancelled())
                lista.add(r);
        }
        for (Reservation r : lista) {
            System.out.println("r " + r.getStart() + "   " + r.getEnd());
            if ((r.getStart().before(reservation.getStart()) && reservation.getStart().before(r.getEnd()))
                    || (r.getStart().before(reservation.getEnd()) && r.getEnd().after(reservation.getEnd()))
                    || (r.getStart().before(reservation.getStart()) && r.getEnd().after(reservation.getEnd())))
                return false;
        }
        return true;
    }

    public double calculatePrice (Reservation reservation) {
        Optional<Unit> unit = unitRepository.findById(reservation.getUnit().getId());
        if (!unit.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit does not exist!");
        Unit u = unit.get();
        System.out.println(" unit " + u.getId() + "   " + u.getPriceSchedule().size());

        double price = 0;
        Date s = reservation.getStart();

        System.out.println(u.getId() + " " + reservation.getStart() + " " + reservation.getEnd());
        for (PriceSchedule ps : u.getPriceSchedule()) {
            for (Plan p : ps.getPlan()) {
                System.out.println(" plan " + p.getId() + " " + p.getFromDate() +" to " + p.getToDate());
                if (p.getFromDate().before(reservation.getStart()) && p.getToDate().after(reservation.getStart())) {
                    while (s.before(p.getToDate())) {
                        if (p.getFromDate().before(reservation.getStart()) && p.getToDate().after(reservation.getStart())) {
                            while (s.before(p.getToDate())) {
                                if (p.isPerPerson()) {
                                    price += u.getPerson() * p.getPrice();
                                } else
                                    price += p.getPrice();
                                s = new Date(s.getTime() + (1000 * 60 * 60 * 24));
                                if (s.equals(reservation.getEnd()))
                                    return price;
                            }
                        } else if (p.getFromDate().after(reservation.getStart()) && p.getToDate().before(reservation.getEnd())) {
                            while (s.before(p.getToDate())) {
                                if (p.isPerPerson()) {
                                    price += u.getPerson() * p.getPrice();
                                } else
                                    price += p.getPrice();
                                s = new Date(s.getTime() + (1000 * 60 * 60 * 24));
                                if (s.equals(reservation.getEnd()))
                                    return price;
                            }
                        } else if (p.getFromDate().after(reservation.getStart()) && p.getToDate().after(reservation.getEnd())) {
                            while (s.before(p.getToDate())) {
                                if (p.isPerPerson()) {
                                    price += u.getPerson() * p.getPrice();
                                } else
                                    price += p.getPrice();
                                s = new Date(s.getTime() + (1000 * 60 * 60 * 24));
                                if (s.equals(reservation.getEnd()))
                                    return price;
                            }
                        } else if (p.getFromDate().before(reservation.getStart()) && p.getToDate().after(reservation.getEnd())) {
                            while (s.before(p.getToDate())) {
                                if (p.isPerPerson()) {
                                    price += u.getPerson() * p.getPrice();
                                } else
                                    price += p.getPrice();
                                s = new Date(s.getTime() + (1000 * 60 * 60 * 24));
                                if (s.equals(reservation.getEnd()))
                                    return price;
                            }
                        }
                    }
                }
            }
        }
        return price;
    }


}