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

@Endpoint
public class UnitEndpoint {

    private static final String NAMESPACE_URI = "http://www.megatravell.com/unit";

    @Autowired
    UnitWebServiceImpl unitWebService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnitRequest")
    @ResponsePayload
    public UnitResponse getUnit(@RequestPayload UnitRequest request) {
        UnitResponse response = new UnitResponse();

        response.setUnit(unitWebService.getUnitById(request.getId()));
        System.out.println("LOAD ID OBJECT WS"+ request.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnitAddRequest")
    @ResponsePayload
    public UnitAddResponse addObject(@RequestPayload UnitAddRequest request) {
        UnitAddResponse response = new UnitAddResponse();

        unitWebService.addUnit(request.getUnit());

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("New unit created ");

        response.setResponseMessage(responseMessage);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnitUpdateRequest")
    @ResponsePayload
    public UnitUpdateResponse updateObject(@RequestPayload UnitUpdateRequest request) {
        UnitUpdateResponse response = new UnitUpdateResponse();

        unitWebService.updateUnit(request.getUnit());

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("Unit updated ");

        response.setResponseMessage(responseMessage);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UnitDeleteRequest")
    @ResponsePayload
    public UnitDeleteResponse deleteUnit(@RequestPayload UnitDeleteRequest request) {
        UnitDeleteResponse response = new UnitDeleteResponse();
        ResponseMessage responseMessage = new ResponseMessage();

        if (unitWebService.deleteUnit(request.getId())){
            responseMessage.setMessage("Unit with id " + request.getId() + " deleted!");
        }else
            responseMessage.setMessage("FAILED-Unit with id " + request.getId() + " doesn't exist!");

        response.setResponseMessage(responseMessage);

        return response;
    }





}
