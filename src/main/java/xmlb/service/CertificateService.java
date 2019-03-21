package xmlb.service;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xmlb.KeyStoreReader;
import xmlb.model.CertificateInfo;
import xmlb.model.IssuerData;
import xmlb.model.SubjectData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CertificateService {

    @Value("Xmlsecuritypass")
    private String secret;

    public List<String> search(Long id, String name){

        return null;
    }

    public Boolean check(Long id){

        return null;
    }

    public String createNewSelfSignedCertificate(CertificateInfo certificateInfo) {

        try {
            System.out.println("Create SS Service!");

            KeyPair keyPairSubject = generateKeyPair();

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate =  certificateInfo.getStartDate();
            Date endDate = certificateInfo.getEndDate();

            System.out.println(startDate);
            System.out.println(endDate);

            System.out.println(certificateInfo.getCommName());
            System.out.println( certificateInfo.getOrg());
            System.out.println(certificateInfo.getOrgUnit());
            System.out.println(certificateInfo.getCountry());
            System.out.println(certificateInfo.getLoc());
            System.out.println(certificateInfo.getState());


            //Serijski broj sertifikata
            String sn="1";
            //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, certificateInfo.getCommName());
            builder.addRDN(BCStyle.O, certificateInfo.getOrg());
            builder.addRDN(BCStyle.OU, certificateInfo.getOrgUnit());
            builder.addRDN(BCStyle.C, certificateInfo.getCountry());
            builder.addRDN(BCStyle.L, certificateInfo.getLoc());
            builder.addRDN(BCStyle.ST, certificateInfo.getState());


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

            keyStoreWriter.loadKeyStore(null,secret.toCharArray());

            keyStoreWriter.write(certificateInfo.getAlias(),keyPairSubject.getPrivate(),secret.toCharArray(),cert);

            keyStoreWriter.saveKeyStore("SecurityKeyStore"+".p12",secret.toCharArray());

            return "Successful";

        } catch (Exception e) {
            return e.getMessage();
        }


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

    public String showKeyStoreContent(String alias) {
        //TODO: Upotrebom klasa iz primeri/pki paketa, prikazati sadrzaj keystore-a, gde korisnik unosi ime i lozinku
        //Dozvoljeno je menjati klase iz primeri/pki paketa
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        String name = "keyStore.p12";
        String password = "test";


        KeyStoreReader keyStoreReader = new KeyStoreReader();
        Certificate cert = keyStoreReader.readCertificate(name,password,alias);

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,alias,password.toCharArray(),password.toCharArray());

        X509Certificate certi = (X509Certificate) cert;

        return "Imam GA";

    }

    public void createNewIssuedCertificate(IssuerData issuer,X509Certificate c) {
        //TODO: Upotrebom klasa iz primeri/pki paketa, prikazati sadrzaj keystore-a, gde korisnik unosi sve potrebne podatke
        //Radi ustede vremena hardkodovati podatke vezane za subjekta sertifikata

        try {
            KeyPair keyPairSubject = generateKeyPair();

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = new Date();
            Date endDate = iso8601Formater.parse("2022-04-01");

            //Serijski broj sertifikata
            String sn="2";
            //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, "localhost:8443");
            builder.addRDN(BCStyle.O, "Megatravel");
            builder.addRDN(BCStyle.OU, "Megatravel London");
            builder.addRDN(BCStyle.C, "UK");
            builder.addRDN(BCStyle.L, "London");
            builder.addRDN(BCStyle.ST, "UK");

            //Kreiraju se podaci za sertifikat, sto ukljucuje:
            // - javni kljuc koji se vezuje za sertifikat
            // - podatke o vlasniku
            // - serijski broj sertifikata
            // - od kada do kada vazi sertifikat

            SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);

            CertificateGenerator certificateGenerator = new CertificateGenerator();
            X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuer);



            cert.verify(c.getPublicKey());


            KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

            keyStoreWriter.loadKeyStore(null,"test".toCharArray());

            keyStoreWriter.write("new",keyPairSubject.getPrivate(),"test".toCharArray(),cert);

            keyStoreWriter.saveKeyStore("newPersonal"+".p12","test".toCharArray());


        } catch (ParseException e) {
            e.printStackTrace();
        }catch(CertificateException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }catch (SignatureException e) {
            System.out.println("\nValidacija neuspesna :(");
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

    }
}
