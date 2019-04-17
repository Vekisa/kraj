package xmlb.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import xmlb.dto.CertificateDTO;
import xmlb.model.Certificate;
import xmlb.model.Communication;
import xmlb.model.Regex;
import xmlb.repository.CertificateRepository;
import xmlb.repository.CommunicationRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CommunicationService {

    @Autowired
    private CommunicationRepository communicationRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    public Communication createCommunication(String first, String second){
        if(!Pattern.matches(Regex.serialNumber,first) || !Pattern.matches(Regex.serialNumber,second))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad serial numbers");

        if(first.equals(second))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

        Certificate certificateFirst = getCertificateBySerialNumber(first);
        Certificate certificateSecond = getCertificateBySerialNumber(second);

        if(certificateFirst == null || certificateSecond == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Bad serial number");

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

        if(!Pattern.matches(Regex.serialNumber,first) || !Pattern.matches(Regex.serialNumber,second))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad serial numbers");

        if(first == null || second == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameters");

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
        if(serialNumber == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad parameter");

        List<CertificateDTO> list = new ArrayList<>();
        List<Communication> communications = communicationRepository.findAll();
        for(Communication communication : communications){
            if(communication.getFirst().equals(serialNumber)){
                Certificate certificate = getCertificateBySerialNumber(communication.getSecond());
                if(certificate!= null)
                    list.add(new CertificateDTO(certificate));
            }else if(communication.getSecond().equals(serialNumber)){
                Certificate certificate = getCertificateBySerialNumber(communication.getFirst());
                if(certificate!= null)
                    list.add(new CertificateDTO(certificate));
            }
        }
        return list;
    }

    private Certificate getCertificateBySerialNumber(String serialNumber){
        List<Certificate> certificates = certificateRepository.findAll();

        for(Certificate certificate : certificates){
            if(certificate.getSerialNumber().equals(serialNumber) && isCertificateOk(certificate))
                return certificate;
        }

        return null;
    }

    private Boolean isCertificateOk(Certificate certificate){
        if(!certificate.getRevoked() && certificate.getEndDate().after(new Date()))
            return true;
        return false;
    }

}
