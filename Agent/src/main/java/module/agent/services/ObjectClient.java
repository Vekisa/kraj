package module.agent.services;

import module.agent.model.web.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ObjectClient extends WebServiceGatewaySupport {

    private static final String WS_URI = "http://localhost:8765/soapws/";

    public ObjectResponse getObject(long id) {
        ObjectRequest request = new ObjectRequest();
        request.setId(id);
        ObjectResponse response = (ObjectResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(WS_URI+"ObjectRequest"));
        return response;
    }

    public ObjectAllResponse allObject() {
        ObjectAllRequest objectAllRequest = new ObjectAllRequest();
        ObjectFactory objectFactory = new ObjectFactory();
        ObjectAllResponse objectAllResponse = (ObjectAllResponse) getWebServiceTemplate().marshalSendAndReceive
                (objectAllRequest,new SoapActionCallback(WS_URI+"ObjectAllRequest"));
        return objectAllResponse;
    }

    public ObjectAddResponse addObject(ObjectWS objectWS){
        ObjectAddRequest objectAddRequest = new ObjectAddRequest();
        objectAddRequest.setObject(objectWS);
        ObjectAddResponse objectAddResponse = (ObjectAddResponse) getWebServiceTemplate().marshalSendAndReceive(
                objectAddRequest, new SoapActionCallback(WS_URI+"ObjectAddRequest"));
        return objectAddResponse;

    }

    public ObjectUpdateResponse updateObject(ObjectWS objectWS){
        ObjectUpdateRequest objectUpdateRequest = new ObjectUpdateRequest();
        objectUpdateRequest.setObject(objectWS);
        ObjectUpdateResponse objectUpdateResponse = (ObjectUpdateResponse) getWebServiceTemplate().marshalSendAndReceive(
                objectUpdateRequest, new SoapActionCallback(WS_URI+"ObjectUpdateRequest"));
        return objectUpdateResponse;
    }

    public ObjectDeleteResponse deleteObject(long id) {
        ObjectDeleteRequest request = new ObjectDeleteRequest();
        request.setId(id);
        ObjectDeleteResponse response = (ObjectDeleteResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(WS_URI+"ObjectDeleteRequest"));
        return response;
    }



}
