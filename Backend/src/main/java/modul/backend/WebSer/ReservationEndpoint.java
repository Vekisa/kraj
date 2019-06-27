package modul.backend.WebSer;

import modul.backend.model.*;
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
    ReservationImplementation reservationImplementation;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ReservationRequest")
    @ResponsePayload
    public ReservationResponse getReservation(@RequestPayload ReservationRequest request) {
        ReservationResponse response = new ReservationResponse();
        //response.setReservation(reservationImplementation.getReservationById(request.getId()));

        Reservation reservation = new Reservation();

        Unit tu = new Unit();
        tu.setId(2);

        RegisteredUser user = new RegisteredUser();

        user.setId(3);

        Reservation temp = new Reservation(null,new Date(),true,null,500,new Unit());
        temp.setId(1);
        temp.setUnit(tu);
        temp.setRegisteredUser(user);

        response.setReservation(temp);

        System.out.println("DESAVA SEE "+ request.getId());

        return response;
    }


}
