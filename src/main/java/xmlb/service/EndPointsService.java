package xmlb.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import xmlb.model.User.EndPoint;
import xmlb.model.User.Role;
import xmlb.model.User.User;
import xmlb.repository.EndPointRepository;
import xmlb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EndPointsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private EndPointRepository endPointRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    private String getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        return user.get().getUsername();
    }

    public  void updateDatabase(Map<RequestMappingInfo, HandlerMethod> map){
        EndPoint endPoint;
        String path = "";
        String[] parts;
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
            path = entry.getKey().toString();
            path = path.substring(1,path.length()-1);
            parts = path.split(",");
            parts = parts[0].split(" ");
            endPoint = new EndPoint(parts[1],parts[0]);
            endPointRepository.save(endPoint);
        }
    }

    public List<EndPoint> allEndPoints(){
        LOGGER.info("IN FUNC: Success");
        return endPointRepository.findAll();

    }

    public List<EndPoint> allEndPointsId(Long id){

       Role role  = roleService.getRole(id);
        LOGGER.info("IN FUNC: Success");
       return role.getEndPoints();

    }

    public List<EndPoint> allEndPointsMissingId(Long id){

        Role role  = roleService.getRole(id);

        List<EndPoint> roleEndPoints = role.getEndPoints();

        List<EndPoint> allEndPoints = endPointRepository.findAll();

        ArrayList<EndPoint> notAddedEndPoints = new ArrayList<>();

        for (EndPoint endPoint:roleEndPoints){
            allEndPoints.remove(endPoint);
        }
        LOGGER.info("IN FUNC: Success");
        return allEndPoints;

    }

    public EndPoint getEndPoint(Long id ){

        Optional<EndPoint> optionalEndPoint = endPointRepository.findById(id);

        if (!optionalEndPoint.isPresent()){
            LOGGER.error("IN FUNC: End point doesn't exist!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "End point doesn't exist!");
        }

        LOGGER.info("Success");
        return optionalEndPoint.get();

    }

    public EndPoint saveEndPoint(EndPoint endPoint){
        LOGGER.info("Success");
        return endPointRepository.save(endPoint);
    }




}
