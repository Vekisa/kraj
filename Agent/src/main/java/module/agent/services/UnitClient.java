package module.agent.services;

import module.agent.model.web.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class UnitClient extends WebServiceGatewaySupport {

    private static final String WS_URI = "http://localhost:8765/soapws/";

    public UnitResponse getUnit(long id){
        UnitRequest request=new UnitRequest();
        request.setId(id);
        UnitResponse response=(UnitResponse) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(WS_URI+"UnitRequest"));
        return response;
    }

    public UnitAllResponse allUnit(){
        UnitAllRequest unitAllRequest=new UnitAllRequest();
        UnitAllResponse unitAllResponse=(UnitAllResponse) getWebServiceTemplate().marshalSendAndReceive(unitAllRequest, new SoapActionCallback(WS_URI+"UnitAllRequest"));
        return unitAllResponse;
    }

    public UnitAddResponse addUnit(UnitWS unitWS){
        UnitAddRequest unitAddRequest=new UnitAddRequest();
        unitAddRequest.setUnit(unitWS);
        UnitAddResponse unitAddResponse = (UnitAddResponse) getWebServiceTemplate().marshalSendAndReceive(unitAddRequest, new SoapActionCallback(WS_URI+"UnitAddRequest"));
        return unitAddResponse;
    }

    public UnitUpdateResponse updateUnit(UnitWS unitWS){
        UnitUpdateRequest unitUpdateRequest=new UnitUpdateRequest();
        unitUpdateRequest.setUnit(unitWS);
        UnitUpdateResponse unitUpdateResponse = (UnitUpdateResponse) getWebServiceTemplate().marshalSendAndReceive(unitUpdateRequest, new SoapActionCallback(WS_URI+"UnitUpdateRequest"));
        return unitUpdateResponse;
    }

    public UnitDeleteResponse unitDeleteResponse(long id){
        UnitDeleteRequest request=new UnitDeleteRequest();
        request.setId(id);
        UnitDeleteResponse response= (UnitDeleteResponse)  getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(WS_URI+"UnitDeleteRequest"));
        return response;
    }
}
