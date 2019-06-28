package module.agent.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import module.agent.model.Message;
import module.agent.model.RegisteredUser;
import module.agent.model.User;
import module.agent.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/create_message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Kreiranje poruke", httpMethod = "POST", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Message.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Message> createMessage(@RequestBody Message message, OAuth2Authentication oAuth2Authentication){
        System.out.println("Uslo ");

        return new ResponseEntity<>(messageService.createMessage(message, oAuth2Authentication), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Sve poruke", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Message.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Message>> getAll( OAuth2Authentication oAuth2Authentication){
        return new ResponseEntity<>(messageService.getMessages(oAuth2Authentication), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUSers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Svi korisnici koji su slali poruke", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<RegisteredUser>> getAllUsers( OAuth2Authentication oAuth2Authentication){
        System.out.println("korisnik " + oAuth2Authentication.getName());
        return new ResponseEntity<>(messageService.users(oAuth2Authentication), HttpStatus.OK);
    }

    @RequestMapping(value = "/getFromUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Prepiska sa nekim korisnikom", httpMethod = "GET", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Message.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<List<Message>> getFromUser(@PathVariable Long id, OAuth2Authentication oAuth2Authentication){
        return new ResponseEntity<>(messageService.fromUser(id, oAuth2Authentication), HttpStatus.OK);
    }

    @RequestMapping(value = "/seen", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Poruka procitana", httpMethod = "PUT", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Message.class),
            @ApiResponse(code = 204, message = "No Content."),
            @ApiResponse(code = 400, message = "Bad Request.")
    })
    public ResponseEntity<Boolean> seenMessage(@RequestBody Message m){
        System.out.println("Uslo update ");
        return new ResponseEntity<Boolean>(messageService.messageSeen(m), HttpStatus.OK);
    }
}
