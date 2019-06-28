package modul.backend.WebSer;

import modul.backend.model.Reservation;
import modul.backend.model.Unit;
import modul.backend.model.web.ReservationRequest;
import modul.backend.model.web.ReservationResponse;
import modul.backend.model.web.ReservationWS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Date;

@Endpoint
public class ReservationEndpoint {

    private static final String NAMESPACE_URI = "http://www.megatravell.com/service";

    @Autowired
    ReservationWebService reservationImplementation;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ReservationRequest")
    @ResponsePayload
    public ReservationResponse getReservation(@RequestPayload ReservationRequest request) {
        ReservationResponse response = new ReservationResponse();
        ReservationWS rese = new ReservationWS();
        BeanUtils.copyProperties(reservationImplementation.getReservationById(request.getId()),rese);
        response.setReservation(rese);

        System.out.println("DESAVA SEE "+ request.getId());

        return response;
    }








}
