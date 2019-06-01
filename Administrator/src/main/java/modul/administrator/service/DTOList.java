package modul.administrator.service;

import modul.administrator.dto.ObjectTypeDTO;
import modul.administrator.model.ObjectType;

import java.util.ArrayList;
import java.util.List;

public class DTOList {

    public static List<ObjectTypeDTO> objectTypes(List<ObjectType> list){
        List<ObjectTypeDTO> listDTO = new ArrayList<>();
        for(ObjectType obj : list)
            listDTO.add(new ObjectTypeDTO(obj));

        return listDTO;
    }
}
