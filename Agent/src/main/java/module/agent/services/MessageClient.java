package module.agent.services;

import module.agent.model.web.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class MessageClient  extends WebServiceGatewaySupport {
    private static final String WS_URI = "http://localhost:8765/soapws/";

    public MessageAllResponse allMessage() {
        MessageAllRequest messageAllRequest = new MessageAllRequest();
        MessageAllResponse messageAllResponse = (MessageAllResponse) getWebServiceTemplate().marshalSendAndReceive
                (messageAllRequest,new SoapActionCallback(WS_URI+"MessageAllRequest"));
        return messageAllResponse;
    }

    public MessageAddResponse addMessage(MessageWS messageWS){
        MessageAddRequest messageAddRequest = new MessageAddRequest();
        messageAddRequest.setMessage(messageWS);
        MessageAddResponse messageAddResponse = (MessageAddResponse) getWebServiceTemplate().marshalSendAndReceive(
                messageAddRequest, new SoapActionCallback(WS_URI+"MessageAddRequest"));
        return messageAddResponse;

    }

}
