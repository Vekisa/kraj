package modul.search.service;



import modul.search.dto.AccommodationTypeDTO;
import modul.search.dto.ExtraOptionDTO;
import modul.search.dto.ObjectDTO;
import modul.search.dto.UnitDTO;
import modul.search.model.AccommodationType;
import modul.search.model.ExtraOption;
import modul.search.model.Object;
import modul.search.model.Unit;

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
