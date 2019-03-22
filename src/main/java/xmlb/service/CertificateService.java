package xmlb.service;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xmlb.KeyStoreReader;
import xmlb.model.CertificateInfo;
import xmlb.model.IssuerData;
import xmlb.model.Revoke;
import xmlb.model.SubjectData;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CertificateService {

    @Value("Xmlsecuritypass")
    private String secret;

    @Value("${password.for.keystore}")
    private String password;

    @Value("${certificate.serial.number}")
    private int serialNumber;

    public List<String> search(String name){

        return null;
    }

    public Boolean check(Long id){

        return null;
    }

    public List<CertificateInfo> allCertificates(){
        File folder = new File("keystores/");
        File[] listOfFiles = folder.listFiles();

        List<CertificateInfo> list = new ArrayList<>();

        CertificateInfo cInfo = new CertificateInfo();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                cInfo = new CertificateInfo();
                cInfo.setAlias(file.getName().substring(0,file.getName().length()-4));
                list.add(cInfo);
            }
        }
        return list;
    }

    public Boolean ifAliasExist(String alias){
        File folder = new File("keystores/");
        File[] listOfFiles = folder.listFiles();

        if(alias.equals("root") || alias.equals("ROOT"))
            return true;

        for (File file : listOfFiles) {
            if (file.getName().substring(0,file.getName().length()-4).equals(alias))
                return true;
        }
        return false;
    }

    public String createNewSelfSignedCertificate(CertificateInfo certificateInfo) {
        if(ifAliasExist(certificateInfo.getAlias()))
            return "Alias exists";

        try {
            System.out.println("Create SS Service!");

            KeyPair keyPairSubject = generateKeyPair();

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate =  certificateInfo.getStartDate();
            Date endDate = certificateInfo.getEndDate();

            System.out.println(startDate);
            System.out.println(endDate);

            System.out.println(certificateInfo.getParent());
            System.out.println(certificateInfo.getCommName());
            System.out.println(certificateInfo.getOrg());
            System.out.println(certificateInfo.getOrgUnit());
            System.out.println(certificateInfo.getCountry());
            System.out.println(certificateInfo.getLoc());
            System.out.println(certificateInfo.getState());


            //Serijski broj sertifikata
            ++serialNumber;
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
            SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), String.valueOf(serialNumber), startDate, endDate);
            IssuerData issuerData = new IssuerData(keyPairSubject.getPrivate(),builder.build());

            CertificateGenerator certificateGenerator = new CertificateGenerator();
            X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuerData);

            KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

            keyStoreWriter.loadKeyStore(null,password.toCharArray());

            keyStoreWriter.write(certificateInfo.getAlias(),keyPairSubject.getPrivate(),password.toCharArray(),cert);

            keyStoreWriter.saveKeyStore("keystores/"+certificateInfo.getAlias()+".p12",password.toCharArray());

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

    public String showKeyStoreContent(String alias, String password) {
        //TODO: Upotrebom klasa iz primeri/pki paketa, prikazati sadrzaj keystore-a, gde korisnik unosi ime i lozinku
        //Dozvoljeno je menjati klase iz primeri/pki paketa
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        String name = "keystores/"+ alias +".p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();
        Certificate cert = keyStoreReader.readCertificate(name,secret,alias);

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,alias,secret.toCharArray(),password.toCharArray());

        X509Certificate certi = (X509Certificate) cert;
        return "Imam GA";

    }

    public IssuerData getIssuerFromCertificate(String alias, String password){
        System.out.println("TRAZIM ISSUERA");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = "keystores/"+ alias +".p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();
        Certificate cert = keyStoreReader.readCertificate(name,password,alias);

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,alias,password.toCharArray(),password.toCharArray());

        return issuerData;
    }

    public IssuerData getIssuerFromRootCertificate(String pass){
        System.out.println("TRAZIM ISSUERA ROOTA " + pass);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = "keystores/root.p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();
        Certificate cert = keyStoreReader.readCertificate(name,pass,"root");
        if(cert == null)
            System.out.println("PUKAO SAM");

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,"root",pass.toCharArray(),pass.toCharArray());

        return issuerData;
    }
    public void createNewIssuedCertificate(CertificateInfo certificateInfo) {
        if(ifAliasExist(certificateInfo.getAlias()))
            return;

        System.out.println("PASS "  + password);
        IssuerData issuer;
        if(certificateInfo.getParent().equals("ROOT")){
            issuer = this.getIssuerFromRootCertificate(certificateInfo.getPassword());
        }else{
            issuer = this.getIssuerFromCertificate(certificateInfo.getParent(),certificateInfo.getPassword());
        }
        System.out.println("NASAO ISSUERA");

        KeyPair keyPairSubject = generateKeyPair();

        //Datumi od kad do kad vazi sertifikat
        Date startDate =  certificateInfo.getStartDate();
        Date endDate = certificateInfo.getEndDate();

        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, certificateInfo.getCommName());
        builder.addRDN(BCStyle.O, certificateInfo.getOrg());
        builder.addRDN(BCStyle.OU, certificateInfo.getOrgUnit());
        builder.addRDN(BCStyle.C, certificateInfo.getCountry());
        builder.addRDN(BCStyle.L, certificateInfo.getLoc());
        builder.addRDN(BCStyle.ST, certificateInfo.getState());


        SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), String.valueOf(serialNumber), startDate, endDate);
        ++serialNumber;
        CertificateGenerator certificateGenerator = new CertificateGenerator();
        X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuer);

        KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

        keyStoreWriter.loadKeyStore(null,password.toCharArray());

        keyStoreWriter.write(certificateInfo.getAlias(),keyPairSubject.getPrivate(),password.toCharArray(),cert);

        keyStoreWriter.saveKeyStore("keystores/"+certificateInfo.getAlias()+".p12",password.toCharArray());

        System.out.println("NAPRAVIO CERT");
    }
}
