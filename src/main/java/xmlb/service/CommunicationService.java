package xmlb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import xmlb.model.CertificateInfo;
import xmlb.model.Communication;
import xmlb.repository.CommunicationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunicationService {

    @Autowired
    private CommunicationRepository communicationRepository;

    public Communication createCommunication(String first, String second){

        if(first.equals(second)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");
        }

        List<Communication> communications = communicationRepository.findAll();

        for(Communication communication : communications){
            if((communication.getFirst().equals(first) && communication.getSecond().equals(second)) || communication.getFirst().equals(second) && communication.getSecond().equals(first)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Communication already exists");
            }
        }

        Communication communication = new Communication();
        communication.setFirst(first);
        communication.setSecond(second);
        communicationRepository.save(communication);
        return communication;
    }

    public void delete(String first, String second){
        List<Communication> communications = communicationRepository.findAll();

        for(Communication communication : communications){
            if((communication.getFirst().equals(first) && communication.getSecond().equals(second)) || communication.getFirst().equals(second) && communication.getSecond().equals(first)){
                communicationRepository.deleteById(communication.getId());
                return;
            }
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Communication does not exist");
    }

    public List<CertificateInfo> getCommunicationsOfCertificate(String alias){
        List<CertificateInfo> list = new ArrayList<>();
        List<Communication> communications = communicationRepository.findAll();
        CertificateInfo ci = null;
        for(Communication communication : communications){
            if(communication.getFirst().equals(alias)){
                ci = new CertificateInfo();
                ci.setAlias(communication.getSecond());
                list.add(ci);
            }else if(communication.getSecond().equals(alias)){
                ci = new CertificateInfo();
                ci.setAlias(communication.getFirst());
                list.add(ci);
            }
        }
        return list;
    }

}
