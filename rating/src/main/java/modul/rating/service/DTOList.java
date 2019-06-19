package modul.rating.service;

import modul.rating.dto.RatingDTO;
import modul.rating.dto.RegisteredUserDTO;
import modul.rating.model.*;

import java.util.ArrayList;
import java.util.List;

public class DTOList {

    public static List<RegisteredUserDTO> registeredUsers(List<RegisteredUser> list){
        List<RegisteredUserDTO> listDTO = new ArrayList<>();
        for(RegisteredUser obj: list)
            listDTO.add(new RegisteredUserDTO(obj));

        return  listDTO;
    }

    public static List<RatingDTO> ratings(List<Rating> list){
        List<RatingDTO> listDTO = new ArrayList<>();
        for(Rating obj: list)
            listDTO.add(new RatingDTO(obj));

        return  listDTO;
    }
}
