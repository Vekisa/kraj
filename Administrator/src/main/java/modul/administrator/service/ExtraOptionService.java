package modul.administrator.service;

import modul.administrator.dto.ExtraOptionDTO;
import modul.administrator.model.ExtraOption;
import modul.administrator.repository.ExtraOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExtraOptionService {

    @Autowired
    private ExtraOptionRepository extraOptionRepository;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<ExtraOptionDTO> getAll(){
        return DTOList.extraOptions(extraOptionRepository.findAll());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public ExtraOptionDTO create(ExtraOptionDTO extraOptionDTO){
        ExtraOption extraOption = new ExtraOption();
        extraOption.setName(extraOptionDTO.getName());
        extraOption.setDescription(extraOptionDTO.getDescription());
        extraOption.setPrice(extraOptionDTO.getPrice());
        extraOptionRepository.save(extraOption);

        return new ExtraOptionDTO(extraOption);
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public ExtraOptionDTO update(ExtraOptionDTO extraOptionDTO){
        Optional<ExtraOption> extraOption = extraOptionRepository.findById(extraOptionDTO.getId());
        if(!extraOption.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Extra option does not exist!");

        extraOption.get().setName(extraOptionDTO.getName());
        extraOption.get().setDescription(extraOptionDTO.getDescription());
        extraOption.get().setPrice(extraOptionDTO.getPrice());
        extraOptionRepository.save(extraOption.get());

        return new ExtraOptionDTO(extraOption.get());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void delete(Long id){
        Optional<ExtraOption> extraOption = extraOptionRepository.findById(id);
        if(!extraOption.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Extra option does not exist!");

        extraOptionRepository.delete(extraOption.get());
    }
}
