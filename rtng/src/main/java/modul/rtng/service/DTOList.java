package modul.rtng.service;

import modul.rtng.dto.RatingDTO;
import modul.rtng.dto.RegisteredUserDTO;
import modul.rtng.model.Rating;
import modul.rtng.model.RegisteredUser;

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
