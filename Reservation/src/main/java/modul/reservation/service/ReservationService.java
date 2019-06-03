package modul.reservation.service;

import modul.backend.model.Reservation;
import modul.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public void delete(Reservation reservation){
        reservationRepository.delete(reservation);
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
}
