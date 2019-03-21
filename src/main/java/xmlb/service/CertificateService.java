package xmlb.service;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.springframework.stereotype.Service;
import xmlb.CertificateGenerator;
import xmlb.KeyStoreWriter;
import xmlb.model.SubjectData;
import xmlb.model.IssuerData;
import java.security.*;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CertificateService {

    public List<String> search(Long id, String name){

        return null;
    }

    public Boolean check(Long id){

        return null;
    }

    public String createNewSelfSignedCertificate() {
        try {
            System.out.println("USLO S");
            KeyPair keyPairSubject = generateKeyPair();

            System.out.println("Privatni "+keyPairSubject.getPrivate().getEncoded());
            System.out.println("Public "+keyPairSubject.getPublic().getEncoded());

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date();
            Date endDate = iso8601Formater.parse("2022-04-01");

            //Serijski broj sertifikata
            String sn="1";
            //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, "localhost:8080");
            builder.addRDN(BCStyle.O, "Megatravel");
            builder.addRDN(BCStyle.C, "RS");
            builder.addRDN(BCStyle.L, "Novi Sad");
            builder.addRDN(BCStyle.ST, "Serbia");
            builder.addRDN(BCStyle.E, "megatravel@email.com");

            //Kreiraju se podaci za sertifikat, sto ukljucuje:
            // - javni kljuc koji se vezuje za sertifikat
            // - podatke o vlasniku
            // - serijski broj sertifikata
            // - od kada do kada vazi sertifikat
            SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
            IssuerData issuerData = new IssuerData(keyPairSubject.getPrivate(),builder.build());

            CertificateGenerator certificateGenerator = new CertificateGenerator();
            X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuerData);


            KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

            keyStoreWriter.loadKeyStore(null,"test".toCharArray());

            keyStoreWriter.write("self",keyPairSubject.getPrivate(),"test".toCharArray(),cert);

            keyStoreWriter.saveKeyStore("noviKeyStr"+".p12","test".toCharArray());
            return "kreirano";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "nije";

    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
