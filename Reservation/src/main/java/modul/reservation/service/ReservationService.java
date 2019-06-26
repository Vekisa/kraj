package modul.reservation.service;

import modul.reservation.model.Plan;
import modul.reservation.model.PriceSchedule;
import modul.reservation.model.Reservation;
import modul.reservation.model.Unit;
import modul.reservation.model.Users.RegisteredUser;
import modul.reservation.model.Users.User;
import modul.reservation.repository.RegisteredUserRepository;
import modul.reservation.repository.ReservationRepository;
import modul.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public Reservation save(Reservation reservation) {
        if(!checkReservation(reservation)){
                return null;
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsForUser(OAuth2Authentication user){
        String username = user.getUserAuthentication().getName();

        User userObj = getUser(username);
        Optional<RegisteredUser> usr = registeredUserRepository.findById(userObj.getId());
        if(!usr.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User is not logged in");

        return usr.get().getReservation();

    }

    public User getUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username or email : " + username));
        return user;
    }

    //proveriti da li brise i iz liste korisnika...
    public void delete(Long id){
        System.out.println("brisanje   " + id);
        Reservation reservation= reservationRepository.findById(id).get();
        System.out.println(reservation.getPossibleCancellationDate());
        if(reservation.getPossibleCancellationDate().before(new Date())){
            System.out.println("Datum sada " + new Date() + "  datum za otkaz " + reservation.getPossibleCancellationDate());

            reservationRepository.delete(reservation);

        }

    }

    public Optional<Reservation> findByID(Long id){
        return reservationRepository.findById(id);
    }

    public Reservation update(Reservation reservation, Long id){
        Optional<Reservation> pom= findByID(id);
        if(pom.isPresent()){
            pom.get().setEnd(reservation.getEnd());
            pom.get().setConfirmed(reservation.isConfirmed());
            pom.get().setPossibleCancellationDate(reservation.getPossibleCancellationDate());
            pom.get().setPrice(reservation.getPrice());
            pom.get().setRegisteredUser(reservation.getRegisteredUser());
            pom.get().setStart(reservation.getStart());
            pom.get().setUnit(reservation.getUnit());
            reservationRepository.save(pom.get());
            return pom.get();
        }
        return null;
    }

    public List<Reservation> findByUnit(Long id){
        List<Reservation> all= findAll();
        List<Reservation> sameId = new ArrayList<>();
        for(Reservation r: all){
            if(r.getUnit().getId()==id) {
                sameId.add(r);
            }
        }
        return sameId;
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public Boolean checkReservation(Reservation reservation){
        List<Reservation> lista=findByUnit(reservation.getUnit().getId());
        for(Reservation r : lista){
            System.out.println("r " + r.getStart() + "   " + r.getEnd());
            if((r.getStart().before(reservation.getStart()) && reservation.getStart().before(r.getEnd()))
                    || (r.getStart().before(reservation.getEnd()) && r.getEnd().after(reservation.getEnd()))
                    || (r.getStart().before(reservation.getStart()) && r.getEnd().after(reservation.getEnd())))
                return false;
        }
        return true;
    }

    public double calculatePrice(Reservation reservation){
        Unit u = reservation.getUnit();
        double price=0;
        Date s=reservation.getStart();
        for(PriceSchedule ps: u.getPriceSchedule()){
            for(Plan p: ps.getPlan()){
                if(p.getFrom().before(reservation.getStart()) && p.getTo().after(reservation.getStart())){
                    while(s.before(p.getTo())){
                        if(p.isPerPerson()){
                            price+=u.getAdults().doubleValue()*p.getPrice();
                        }else
                            price+=p.getPrice();
                        s=new Date(s.getTime() + (1000 * 60 * 60 * 24));
                        if(s.equals(reservation.getEnd()))
                            return price;
                    }
                } else if(p.getFrom().after(reservation.getStart()) && p.getTo().before(reservation.getEnd())){
                    while(s.before(p.getTo())){
                        if(p.isPerPerson()){
                            price+=u.getAdults().doubleValue()*p.getPrice();
                        }else
                            price+=p.getPrice();
                        s=new Date(s.getTime() + (1000 * 60 * 60 * 24));
                        if(s.equals(reservation.getEnd()))
                            return price;
                    }
                }else if(p.getFrom().after(reservation.getStart()) && p.getTo().after(reservation.getEnd())){
                    while(s.before(p.getTo())){
                        if(p.isPerPerson()){
                            price+=u.getAdults().doubleValue()*p.getPrice();
                        }else
                            price+=p.getPrice();
                        s=new Date(s.getTime() + (1000 * 60 * 60 * 24));
                        if(s.equals(reservation.getEnd()))
                            return price;
                    }
                }else if(p.getFrom().before(reservation.getStart()) && p.getTo().after(reservation.getEnd())){
                    while(s.before(p.getTo())){
                        if(p.isPerPerson()){
                            price+=u.getAdults().doubleValue()*p.getPrice();
                        }else
                            price+=p.getPrice();
                        s=new Date(s.getTime() + (1000 * 60 * 60 * 24));
                        if(s.equals(reservation.getEnd()))
                            return price;
                    }
                }
            }
        }
        return  0;
    }
}
