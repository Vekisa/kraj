package modul.administrator.controller;

import modul.administrator.dto.AgentDTO;
import modul.administrator.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgentDTO>> getNotApproved() {

        return new ResponseEntity<>(agentService.getAll(), HttpStatus.OK);
    }

    @RequestMapping( method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentDTO> addAgent(@RequestBody AgentDTO agentDTO) {

        return new ResponseEntity<>(agentService.addAgent(agentDTO), HttpStatus.OK);
    }
}
