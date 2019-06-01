package modul.administrator.controller;

import modul.administrator.dto.RegisteredUserDTO;
import modul.administrator.service.RegisteredUSerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registered_user")
public class RegisteredUserController {

    @Autowired
    private RegisteredUSerService registeredUSerService;

    @RequestMapping( value="/activate/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserDTO> activate(@PathVariable("id") Long id) {

        return new ResponseEntity<>(registeredUSerService.activate(id), HttpStatus.OK);
    }

    @RequestMapping( value="/deactivate/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserDTO> deactivate(@PathVariable("id") Long id) {

        return new ResponseEntity<>(registeredUSerService.deactivate(id), HttpStatus.OK);
    }

    @RequestMapping( value="/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUserDTO> delete(@PathVariable("id") Long id) {

        return new ResponseEntity<>(registeredUSerService.delete(id), HttpStatus.OK);
    }
}
