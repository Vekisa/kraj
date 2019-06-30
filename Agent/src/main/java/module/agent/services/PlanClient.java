package module.agent.services;

import module.agent.model.Plan;
import module.agent.model.web.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class PlanClient extends WebServiceGatewaySupport {
    private static final String WS_URI = "http://localhost:8765/soapws/";

    public PlanUpdateResponse updatePlan(Plan plan){
        PlanUpdateRequest planUpdateRequest = new PlanUpdateRequest();
        planUpdateRequest.setPlan(plan);
        PlanUpdateResponse planUpdateResponse = (PlanUpdateResponse) getWebServiceTemplate().marshalSendAndReceive(
                planUpdateRequest, new SoapActionCallback(WS_URI+"PlanUpdateRequest"));
        return planUpdateResponse;
    }
}
