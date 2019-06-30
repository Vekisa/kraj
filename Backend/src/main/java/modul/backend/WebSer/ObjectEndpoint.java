package modul.backend.WebSer;

import modul.backend.model.AccommodationType;
import modul.backend.model.Object;
import modul.backend.model.web.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class ObjectEndpoint {

    private static final String NAMESPACE_URI = "http://www.megatravell.com/wobject";

    @Autowired
    ObjectWebServiceImpl objectWebService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObjectRequest")
    @ResponsePayload
    public ObjectResponse getObject(@RequestPayload ObjectRequest request) {
        ObjectResponse response = new ObjectResponse();
        //ObjectWS objectWS=  new ObjectWS();
        //BeanUtils.copyProperties(objectWebService.getObjectById(request.getId()),objectWS);

        Object object = objectWebService.getObjectById(request.getId());

        ObjectWS objectWS = new ObjectWS(object);

        response.setObject(objectWS);

        System.out.println("LOAD ID OBJECT WS"+ request.getId());

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObjectAddRequest")
    @ResponsePayload
    public ObjectAddResponse addObject(@RequestPayload ObjectAddRequest request) {
        ObjectAddResponse response = new ObjectAddResponse();

        ObjectWS objectWS = request.getObject();
        Object object = new Object(objectWS);

        objectWebService.addObject(object);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("New Object created " + object.getName());

        response.setResponseMessage(responseMessage);

        System.out.println("New Object created " + object.getName());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObjectUpdateRequest")
    @ResponsePayload
    public ObjectUpdateResponse updateObject(@RequestPayload ObjectUpdateRequest request) {
        ObjectUpdateResponse response = new ObjectUpdateResponse();

        Object object = new Object(request.getObject());
        ResponseMessage responseMessage = new ResponseMessage();

        if ( !objectWebService.updateObject(object)){
            responseMessage.setMessage("Object not updated , id not valid :  " + object.getId());
        }else
            responseMessage.setMessage("Object updated " + object.getName());


        response.setResponseMessage(responseMessage);

        System.out.println("Object updated " + object.getName());

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObjectAllRequest")
    @ResponsePayload
    public ObjectAllResponse allObjects() {

        ObjectAllResponse objectAllResponse = new ObjectAllResponse();

        List<Object> objects = objectWebService.getAllObjects();

        ArrayList<ObjectWS> objectsWS = new ArrayList<>();

        for(Object obj : objects){
           ObjectWS objectWS= new ObjectWS(obj);
           objectsWS.add(objectWS);
        }

        objectAllResponse.getObject().addAll(objectsWS);

        return objectAllResponse;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObjectDeleteRequest")
    @ResponsePayload
    public ObjectDeleteResponse deleteObject(@RequestPayload ObjectDeleteRequest request) {
        ObjectDeleteResponse response = new ObjectDeleteResponse();
        ResponseMessage responseMessage = new ResponseMessage();

        if (objectWebService.deleteObject(request.getId())){
            responseMessage.setMessage("Object with id " + request.getId() + " deleted!");
        }else
            responseMessage.setMessage("FAILED-Object with id " + request.getId() + " doesn't exist!");

        response.setResponseMessage(responseMessage);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObjectTypeAllRequest")
    @ResponsePayload
    public ObjectTypeAllResponse allObjectType(@RequestPayload ObjectTypeAllRequest request) {
        ObjectTypeAllResponse response = new ObjectTypeAllResponse();

        response.getObjectType().addAll(objectWebService.getAllObjectTypes());

        return response;
    }






}
