package modul.rtng.service;

import modul.rtng.PomDTO.Pom;
import modul.rtng.model.Mark;
import modul.rtng.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class RatingService {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Pom> getListOfRatings(List<Long> objects){
        List<Pom> mark = new ArrayList<>();
        for(Long id : objects){
            List<Mark> mark1 = markRepository.findObjectMarks(id);
            Pom pom = new Pom();
            pom.setOobjectId(id);
            pom.setRating(getRatingForObject(id));
            mark.add(pom);

        }

        return mark;
    }

    public Double getRatingForObject(Long objectId){
       List<Mark> marks = markRepository.findObjectMarks(objectId);
       double mr = 0;

       for(Mark mark : marks)
           mr += mark.getMark();

        if(mr == 0)
            return 2.5;
        else
            return mr / marks.size();
    }

    public Mark rate(Long objectId, Long userId, int mark) {

        if(canUserRate(objectId,userId)) {
            Mark markObj = new Mark();
            markObj.setMark(mark);
            markObj.setObjectId(objectId);
            markObj.setUserId(userId);
            markRepository.save(markObj);
            return markObj;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can not rate!");
        }
    }
    private Boolean canUserRate(Long objectId, Long userId){

        ResponseEntity<String> exchange = restTemplate.exchange("http://backend-service/backend/object/" + userId + "/" + objectId, HttpMethod.POST, null, String.class);
        return true;
    }
}
