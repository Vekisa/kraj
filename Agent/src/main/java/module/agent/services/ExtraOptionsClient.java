package module.agent.services;

import module.agent.model.web.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ExtraOptionsClient extends WebServiceGatewaySupport {
    private static final String WS_URI = "http://localhost:8765/soapws/";

    public ExtraOptionAllResponse allExtraOption() {
        ExtraOptionAllRequest extraOptionAllRequest = new ExtraOptionAllRequest();
        ExtraOptionAllResponse extraOptionAllResponse = (ExtraOptionAllResponse) getWebServiceTemplate().marshalSendAndReceive
                (extraOptionAllRequest,new SoapActionCallback(WS_URI+"ExtraOptionAllRequest"));
        return extraOptionAllResponse;
    }

}

