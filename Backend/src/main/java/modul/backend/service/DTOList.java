package modul.backend.service;

import modul.backend.dto.AccommodationTypeDTO;
import modul.backend.dto.ExtraOptionDTO;
import modul.backend.dto.ObjectDTO;
import modul.backend.dto.UnitDTO;
import modul.backend.model.AccommodationType;
import modul.backend.model.ExtraOption;
import modul.backend.model.Object;
import modul.backend.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class DTOList {

    public static List<ObjectDTO> objects(List<Object> list){
        List<ObjectDTO> listDTO = new ArrayList<>();
        for(Object obj: list)
            listDTO.add(new ObjectDTO(obj));

        return  listDTO;
    }

    public static List<UnitDTO> units(List<Unit> list){
        List<UnitDTO> listDTO = new ArrayList<>();
        for(Unit obj: list)
            listDTO.add(new UnitDTO(obj));

        return  listDTO;
    }

    public static List<ExtraOptionDTO> extraOptions(List<ExtraOption> list){
        List<ExtraOptionDTO> listDTO = new ArrayList<>();
        for(ExtraOption obj : list)
            listDTO.add(new ExtraOptionDTO(obj));

        return  listDTO;
    }

    public static List<AccommodationTypeDTO> accommodationTypes(List<AccommodationType> list){
        List<AccommodationTypeDTO> listDTO = new ArrayList<>();
        for(AccommodationType obj : list)
            listDTO.add(new AccommodationTypeDTO(obj));

        return listDTO;
    }
}
