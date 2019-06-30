package modul.backend.WebSer;

import modul.backend.model.ExtraOption;
import modul.backend.model.Reservation;
import modul.backend.model.web.ReservationWS;

import java.util.List;

public interface ReservationWebService {

        ReservationWS getReservationById(long id);
        List<ReservationWS> getAllReservationsWSAgent(long agentId);
        List<ExtraOption> getAllExtraOptions();

}
