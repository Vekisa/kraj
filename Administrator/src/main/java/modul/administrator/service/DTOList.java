package modul.administrator.service;

import modul.administrator.dto.*;
import modul.administrator.model.*;

import java.util.ArrayList;
import java.util.List;

public class DTOList {

    public static List<ObjectTypeDTO> objectTypes(List<ObjectType> list){
        List<ObjectTypeDTO> listDTO = new ArrayList<>();
        for(ObjectType obj : list)
            listDTO.add(new ObjectTypeDTO(obj));

        return listDTO;
    }

    public static List<AccommodationTypeDTO> accommodationTypes(List<AccommodationType> list){
        List<AccommodationTypeDTO> listDTO = new ArrayList<>();
        for(AccommodationType obj : list)
            listDTO.add(new AccommodationTypeDTO(obj));

        return listDTO;
    }

    public static List<ExtraOptionDTO> extraOptions(List<ExtraOption> list){
        List<ExtraOptionDTO> listDTO = new ArrayList<>();
        for(ExtraOption obj : list)
            listDTO.add(new ExtraOptionDTO(obj));

        return  listDTO;
    }

    public static List<CommentDTO> comments(List<Comment> list){
        List<CommentDTO> listDTO = new ArrayList<>();
        for(Comment obj : list)
            listDTO.add(new CommentDTO(obj));

        return listDTO;
    }

    public static List<AgentDTO> agents(List<Agent> list){
        List<AgentDTO> listDTO = new ArrayList<>();
        for(Agent obj: list)
            listDTO.add(new AgentDTO(obj));

        return listDTO;
    }

    public static List<RegisteredUserDTO> registeredUsers(List<RegisteredUser> list){
        List<RegisteredUserDTO> listDTO = new ArrayList<>();
        for(RegisteredUser obj: list)
            listDTO.add(new RegisteredUserDTO(obj));

        return  listDTO;
    }
}
