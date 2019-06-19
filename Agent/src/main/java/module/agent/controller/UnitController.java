package module.agent.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import module.agent.model.Plan;
import module.agent.model.PriceSchedule;
import module.agent.model.Unit;
import module.agent.model.Image;
import module.agent.services.ImageService;
import module.agent.services.PlanService;
import module.agent.services.PriceScheduleService;
import module.agent.services.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;
    @Autowired
    private PlanService planService;
    @Autowired
    private PriceScheduleService priceScheduleService;
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/create_new_unit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje smestajne jedinice", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Unit.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit){
        System.out.println("Uslo ");
        for(Image image : unit.getImage()){
            imageService.create(image);
        }
        return new ResponseEntity<>(unitService.create(unit), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Sve smestajne jedinice", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Unit.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Unit>> getAll(){
        System.out.println("Uslo getAll");
        return new ResponseEntity<List<Unit>>(unitService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create_new_plan", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje smestajne jedinice", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Plan.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan){
        System.out.println("Uslo plan " + plan.getFrom() + " fromat " + new Date());
        return new ResponseEntity<>(planService.create(plan), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create_new_priceS", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje smestajne jedinice", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PriceSchedule.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<PriceSchedule> createPlanM(@RequestBody PriceSchedule priceSchedule){
        for(Plan plan : priceSchedule.getPlan()){
            planService.create(plan);
        }
        return new ResponseEntity<>(priceScheduleService.create(priceSchedule), HttpStatus.CREATED);
    }
}
