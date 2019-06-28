package modul.administrator.service;

import modul.administrator.dto.RegisteredUserDTO;
import modul.administrator.model.RegisteredUser;
import modul.administrator.repository.RegisteredUserRepository;
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
public class RegisteredUSerService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<RegisteredUserDTO> getAll(){
        return DTOList.registeredUsers(registeredUserRepository.findAll());
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public RegisteredUserDTO activate(Long id){
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User option does not exist!");

        user.get().setIsEnabled(true);
        registeredUserRepository.save(user.get());

        return new RegisteredUserDTO(user.get());
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public RegisteredUserDTO deactivate(Long id){
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User option does not exist!");

        user.get().setIsEnabled(false);
        registeredUserRepository.save(user.get());

        return new RegisteredUserDTO(user.get());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public RegisteredUserDTO delete(Long id){
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User option does not exist!");

        registeredUserRepository.delete(user.get());

        return new RegisteredUserDTO(user.get());
    }


}
