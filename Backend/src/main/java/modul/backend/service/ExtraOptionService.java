package modul.backend.service;

import modul.backend.dto.ExtraOptionDTO;
import modul.backend.model.ExtraOption;
import modul.backend.repository.ExtraOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraOptionService {

    @Autowired
    private ExtraOptionRepository extraOptionRepository;

    public List<ExtraOptionDTO> getAll(){
        return DTOList.extraOptions(extraOptionRepository.findAll());
    }

    public List<ExtraOption> getAllEO(){
        return extraOptionRepository.findAll();
    }

}
