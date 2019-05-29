package xmlb.service;

import xmlb.dto.CertificateDTO;
import xmlb.model.Certificate;

import java.util.ArrayList;
import java.util.List;

public class CreateDTOList {

    public static List<CertificateDTO> certificates(List<Certificate> certificates) {
        List<CertificateDTO> certificateDTO = new ArrayList<>();
        for (Certificate certificate : certificates)
            certificateDTO.add(new CertificateDTO(certificate));

        return certificateDTO;
    }
}
