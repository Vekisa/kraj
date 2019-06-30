package module.agent.services;

import module.agent.model.web.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ReservationsClient extends WebServiceGatewaySupport {
    private static final String WS_URI = "http://localhost:8765/soapws/";

    public ReservationResponse getReservation(long id) {
        ReservationRequest request = new ReservationRequest();
        request.setId(id);
        ReservationResponse response = (ReservationResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(WS_URI+"ReservationRequest"));
        return response;
    }


    public ReservationAllResponse allReservations() {
        ReservationAllRequest reservationAllRequest = new ReservationAllRequest();
        ReservationAllResponse reservationAllResponse = (ReservationAllResponse) getWebServiceTemplate().marshalSendAndReceive
                (reservationAllRequest,new SoapActionCallback(WS_URI+"ReservationAllRequest"));
        return reservationAllResponse;
    }

    public ReservationAddResponse addReservations(ReservationWS reservationWS){
        ReservationAddRequest reservationAddRequest = new ReservationAddRequest();
        reservationAddRequest.setReservation(reservationWS);
        ReservationAddResponse reservationAddResponse = (ReservationAddResponse) getWebServiceTemplate().marshalSendAndReceive(
                reservationAddRequest, new SoapActionCallback(WS_URI+"ReservationAddRequest"));
        return reservationAddResponse;

    }

    public ReservationUpdateResponse updateReservation(ReservationWS reservationWS){
        ReservationUpdateRequest reservationUpdateRequest = new ReservationUpdateRequest();
        reservationUpdateRequest.setReservation(reservationWS);
        ReservationUpdateResponse reservationUpdateResponse = (ReservationUpdateResponse) getWebServiceTemplate().marshalSendAndReceive(
                reservationUpdateRequest, new SoapActionCallback(WS_URI+"ObjectUpdateRequest"));
        return reservationUpdateResponse;
    }

    public ReservationDeleteResponse deleteReservations(long id) {
        ReservationDeleteRequest request = new ReservationDeleteRequest();
        request.setId(id);
        ReservationDeleteResponse response = (ReservationDeleteResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(WS_URI+"ReservationDeleteRequest"));
        return response;
    }

}
