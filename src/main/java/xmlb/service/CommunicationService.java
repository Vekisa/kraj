package xmlb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import xmlb.dto.CertificateDTO;
import xmlb.model.Certificate;
import xmlb.model.Communication;
import xmlb.repository.CertificateRepository;
import xmlb.repository.CommunicationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommunicationService {

    @Autowired
    private CommunicationRepository communicationRepository;

    @Autowired
    private CertificateRepository certificateRepository;

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

    public List<CertificateDTO> getCommunicationsOfCertificate(String serialNumber){
        List<CertificateDTO> list = new ArrayList<>();
        List<Communication> communications = communicationRepository.findAll();
        for(Communication communication : communications){
            if(communication.getFirst().equals(serialNumber)){
                Certificate certificate = getCertificateBySerialNmber(communication.getSecond());
                if(certificate!= null)
                    list.add(new CertificateDTO(certificate));
            }else if(communication.getSecond().equals(serialNumber)){
                Certificate certificate = getCertificateBySerialNmber(communication.getFirst());
                if(certificate!= null)
                    list.add(new CertificateDTO(certificate));
            }
        }
        return list;
    }

    private Certificate getCertificateBySerialNmber(String serialNumber){
        List<Certificate> certificates = certificateRepository.findAll();

        for(Certificate certificate : certificates){
            if(certificate.getSerialNumber().equals(serialNumber) && isCertificateOk(certificate))
                return certificate;
        }

        return null;
    }

    private Boolean isCertificateOk(Certificate certificate){
        if(!certificate.getRevoked() && certificate.getStartDate().before(new Date()) && certificate.getEndDate().after(new Date()))
            return true;
        return false;
    }

}
