package modul.backend.WebSer;

import modul.backend.model.ExtraOption;
import modul.backend.model.Includes;
import modul.backend.model.Reservation;
import modul.backend.model.User;
import modul.backend.model.web.IncludesWS;
import modul.backend.model.web.ReservationWS;
import modul.backend.repository.ReservationRepository;
import modul.backend.repository.UserRepository;
import modul.backend.service.ExtraOptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationWebServiceImpl implements ReservationWebService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ExtraOptionService extraOptionService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReservationWS getReservationById(long id) {

        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if(!reservationOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object does not exist");

        ReservationWS reservationWS = new ReservationWS();

        BeanUtils.copyProperties(reservationOptional.get(),reservationWS);

        reservationWS.setUnitId(reservationOptional.get().getUnit().getId());
        reservationWS.setRegisteredUserId(reservationOptional.get().getRegisteredUser().getId());

        for (Includes i:reservationOptional.get().getIncludes()){
            IncludesWS includesWS = new IncludesWS();
            BeanUtils.copyProperties(reservationOptional.get(),includesWS);

            includesWS.setReservationId(i.getReservation().getId());
            reservationWS.getIncludes().add(includesWS);
        }

        return reservationWS;
    }

    @Override
    public List<ReservationWS> getAllReservationsWSAgent(long agentId) {

        Optional<User> u = userRepository.findById(agentId);



        return null;
    }





    @Override
    public List<ExtraOption> getAllExtraOptions() {
        return extraOptionService.getAllEO();
    }
}
