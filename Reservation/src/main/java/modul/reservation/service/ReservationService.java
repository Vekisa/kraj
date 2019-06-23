package modul.reservation.service;

import modul.backend.model.Reservation;
import modul.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        if(!checkReservation(reservation)){
                return null;
        }
        return reservationRepository.save(reservation);
    }

    //proveriti da li brise i iz liste korisnika...
    public void delete(Reservation reservation){

        if(reservation.getPossibleCancellationDate().before(new Date())){
            System.out.println("Datum sada " + new Date() + "  datum za otkaz " + reservation.getPossibleCancellationDate());
            reservationRepository.delete(reservation);

        }

    }

    public Optional<Reservation> findByID(Long id){
        return reservationRepository.findById(id);
    }

    public void update(Reservation reservation, Long id){
        Optional<Reservation> pom= findByID(id);
        if(pom.isPresent()){
            pom.get().setEnd(reservation.getEnd());
            pom.get().setConfirmed(reservation.isConfirmed());
            pom.get().setPossibleCancellationDate(reservation.getPossibleCancellationDate());
            pom.get().setPrice(reservation.getPrice());
            pom.get().setRegisteredUser(reservation.getRegisteredUser());
            pom.get().setStart(reservation.getStart());
            pom.get().setUnit(reservation.getUnit());
        }
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

}
