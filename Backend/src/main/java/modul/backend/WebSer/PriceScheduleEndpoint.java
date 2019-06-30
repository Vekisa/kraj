package modul.backend.WebSer;

import modul.backend.model.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PriceScheduleEndpoint {

    private static final String NAMESPACE_URI = "http://www.megatravell.com/plan";

    @Autowired
    PriceScheduleWebService priceScheduleWebService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PriceScheduleRequest")
    @ResponsePayload
    public PriceScheduleResponse getSchedule(@RequestPayload PriceScheduleRequest request) {
        PriceScheduleResponse response = new PriceScheduleResponse();

        response.setPriceSchedule(priceScheduleWebService.getOne(request.getId()));

        System.out.println("LOAD ID OBJECT WS"+ request.getId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PriceScheduleAllRequest")
    @ResponsePayload
    public PriceScheduleAllResponse getAll() {
        PriceScheduleAllResponse response = new PriceScheduleAllResponse();

        response.getPriceSchedule().addAll(priceScheduleWebService.getAll());

        System.out.println("ALL SCHEDULE");
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PriceScheduleAddRequest")
    @ResponsePayload
    public PriceScheduleAddResponse addSchedule(@RequestPayload PriceScheduleAddRequest request) {
        PriceScheduleAddResponse response = new PriceScheduleAddResponse();
        ResponseMessage responseMessage = new ResponseMessage();

        if (!priceScheduleWebService.addOne(request.getPriceSchedule())){
            responseMessage.setMessage("Schedule wasn't saved !");
        }else
            responseMessage.setMessage("Schedule saved !");


        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PriceScheduleUpdateRequest")
    @ResponsePayload
    public PriceScheduleUpdateResponse updateSchedule(@RequestPayload PriceScheduleUpdateRequest request) {
        PriceScheduleUpdateResponse response = new PriceScheduleUpdateResponse();
        ResponseMessage responseMessage = new ResponseMessage();

        if (!priceScheduleWebService.updateOne(request.getPriceSchedule())){
            responseMessage.setMessage("Schedule wasn't updated !");
        }else
            responseMessage.setMessage("Schedule updated !");


        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PlanRequest")
    @ResponsePayload
    public PlanResponse getPlan(@RequestPayload PlanRequest request) {
        PlanResponse response = new PlanResponse();

        response.setPlan(priceScheduleWebService.getPlan(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PlanUpdateRequest")
    @ResponsePayload
    public PlanUpdateResponse updatePlan(@RequestPayload PlanUpdateRequest request) {
        PlanUpdateResponse response = new PlanUpdateResponse();
        ResponseMessage responseMessage = new ResponseMessage();

        if (!priceScheduleWebService.updatePlan(request.getPlan())){
            responseMessage.setMessage("Plan wasn't updated");
        }else
            responseMessage.setMessage("Plan updated");


        return response;
    }










}
