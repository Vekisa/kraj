package modul.reservation.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import modul.reservation.model.Reservation;
import modul.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje rezervacije", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.save(reservation), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ispis rezervacija", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Reservation>> getReservations(){
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get_all_for_user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Ispis rezervacija", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Reservation>> getReservationsForUser(OAuth2Authentication user){
        return new ResponseEntity<>(reservationService.getReservationsForUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkReservation", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Provera da li je jedinica rezervisana", httpMethod = "PUT", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Boolean> reservations(@RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.checkReservation(reservation), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkReservationAndCal", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Provera da li je jedinica rezervisana i racuna cenu", httpMethod = "PUT", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Double> reservationsAndCal(@RequestBody Reservation reservation){
        if(reservationService.checkReservation(reservation)){
            return new ResponseEntity<>(reservationService.calculatePrice(reservation), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Double(0), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update rezervacije", httpMethod = "PUT", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){
        return new ResponseEntity<Reservation>(reservationService.update(reservation, reservation.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Otkaz rezervacije", httpMethod = "DELETE", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Reservation.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity cancelReservation(@PathVariable Long reservation){
        reservationService.delete(reservation);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
