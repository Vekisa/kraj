package modul.backend.WebSer;

import modul.backend.model.Object;
import modul.backend.model.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MessageEndpoint {

    private static final String NAMESPACE_URI = "http://www.megatravell.com/message";

    @Autowired
    MessageWebServiceImpl messageWebService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MessageAddRequest")
    @ResponsePayload
    public MessageAddResponse addMessage(@RequestPayload MessageAddRequest request) {
        MessageAddResponse response = new MessageAddResponse();
        ResponseMessage responseMessage = new ResponseMessage();
        if (messageWebService.sendMessage(request.getMessage())){
            responseMessage.setMessage("Message wasn't sent.");
        }else{
            responseMessage.setMessage("Message sent.");
        }



        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MessageAllRequest")
    @ResponsePayload
    public MessageAllResponse allMessages(@RequestPayload MessageAllRequest request) {
        MessageAllResponse response = new MessageAllResponse();

        response.getMessage().addAll(messageWebService.getAll());

        return response;
    }


}
