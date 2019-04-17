package xmlb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import xmlb.model.User.EndPoint;
import xmlb.repository.EndPointRepository;

import java.util.Map;

@Service
public class EndPointsService {

    @Autowired
    private EndPointRepository endPointRepository;

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
}
