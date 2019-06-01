package modul.administrator.service;

import modul.administrator.dto.RegisteredUserDTO;
import modul.administrator.model.RegisteredUser;
import modul.administrator.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class RegisteredUSerService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public RegisteredUserDTO activate(Long id){
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User option does not exist!");

        user.get().setAktivan(true);
        registeredUserRepository.save(user.get());

        return new RegisteredUserDTO(user.get());
    }

    public RegisteredUserDTO deactivate(Long id){
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User option does not exist!");

        user.get().setAktivan(false);
        registeredUserRepository.save(user.get());

        return new RegisteredUserDTO(user.get());
    }

    public RegisteredUserDTO delete(Long id){
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if(!user.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User option does not exist!");

        //Treba ubaciti polje novoooo i onda srediti
        registeredUserRepository.save(user.get());

        return new RegisteredUserDTO(user.get());
    }


}
