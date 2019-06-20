package modul.rtng.service;

import modul.rtng.dto.RatingDTO;
import modul.rtng.model.Object;
import modul.rtng.model.Rating;
import modul.rtng.repository.ObjectRepository;
import modul.rtng.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ObjectRepository objectRepository;

    public List<RatingDTO> getRatingsForObject(Long objectId){
        Optional<Object> object = objectRepository.findById(objectId);
        if(!object.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object does not exist!");

        return DTOList.ratings(object.get().getRating());
    }

    public Integer getRatingForObject(Long objectId){
        //Treba dodati polje u klasu object
        return null;
    }

    public void rate(Long objectId, int mark){
        Optional<Object> object = objectRepository.findById(objectId);
        if(!object.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Object does not exist!");

        if(!canUserRate(objectId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User can not rate yet!");

        for(Rating rating : object.get().getRating()){
            //prepraviti da se uzima id od trenutnog usera
            if(rating.getRegisteredUser().getId() == 1) {
                rating.setMark(mark);
                ratingRepository.save(rating);
                return;
            }
        }

        Rating rating = new Rating();
        rating.setObject(object.get());
        //setovati trenutnog usera
        rating.setMark(mark);
        ratingRepository.save(rating);
    }

    private Boolean canUserRate(Long objectId){
        //Ovde preuzeti trenutnog usera
       /* RegisteredUser registeredUser = new RegisteredUser();
        Boolean can = false;
        for(Reservation reservation : registeredUser.getReservation())
            if(reservation.getUnit().getObject().getId() == objectId && reservation.getEnd().before(new Date()))
                can = true;

        if(can)
            return true;

        return false;*/
       return true;
    }
}
