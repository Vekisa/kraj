package modul.backend.WebSer;

import modul.backend.model.Reservation;
import modul.backend.model.Unit;
import modul.backend.model.web.*;
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
        response.setReservation(reservationImplementation.getReservationById(request.getId()));

        System.out.println("DESAVA SEE "+ request.getId());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ReservationAllRequest")
    @ResponsePayload
    public ReservationAllResponse getAllReservations(@RequestPayload ReservationAllRequest request) {
        ReservationAllResponse response = new ReservationAllResponse();

        reservationImplementation.getAllReservationsWSAgent(request.getAgentId());
        response.getReservation().addAll(reservationImplementation.getAllReservationsWSAgent(request.getAgentId()));

        System.out.println("DESAVA SEE preuzimenje svih rezervacija "+ request.getAgentId());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ExtraOptionAllRequest")
    @ResponsePayload
    public ExtraOptionAllResponse getAllReservations() {
        ExtraOptionAllResponse response = new ExtraOptionAllResponse();

        response.getExtraOption().addAll(reservationImplementation.getAllExtraOptions());

        System.out.println("DESAVA SEE preuzimenje svih dodatnih usluga ");

        return response;
    }











}
