package modul.backend.WebSer;

import modul.backend.model.Reservation;
import modul.backend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationWebServiceImpl implements ReservationWebService {
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation getReservationById(long id) {
        return reservationRepository.findById(id).get();
    }
}
