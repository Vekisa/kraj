package xmlb.service;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xmlb.KeyStoreReader;
import xmlb.dto.CertificateDTO;
import xmlb.model.*;
import xmlb.model.Certificate;
import xmlb.repository.CertificateRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
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

    @Autowired
    public CertificateRepository certificateRepository;


    public List<CertificateDTO> search(String alias){ //Svi osim povucenih
        File folder = new File("keystores/");
        File[] listOfFiles = folder.listFiles();

        List<CertificateDTO> list = new ArrayList<>();
        /*List<String> revoke= rs.getPovuceneAliasi();
        CertificateDTO cInfo = new CertificateDTO();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName().substring(0,file.getName().length()-4).contains(alias)) {
                    cInfo = new CertificateDTO();
                    cInfo.setAlias(file.getName().substring(0, file.getName().length() - 4));
                    if(!revoke.contains(cInfo.getAlias()))
                         list.add(cInfo);
                }
            }
        }*/
        return list;
    }

    public List<CertificateDTO> search2(String alias){ //bez listova i bez povucenih
        File folder = new File("keystores/");
        File[] listOfFiles = folder.listFiles();

        List<CertificateDTO> list = new ArrayList<>();
       /* List<String> revoke= rs.getAliase();
        CertificateDTO cInfo = new CertificateDTO();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                if(file.getName().substring(0,file.getName().length()-4).contains(alias)) {
                    cInfo = new CertificateDTO();
                    cInfo.setAlias(file.getName().substring(0, file.getName().length() - 4));
                    if(!revoke.contains(cInfo.getAlias()))
                        list.add(cInfo);
                }
            }
        }*/
        return list;
    }

    public Boolean check(Long id){

        return null;
    }

    public List<CertificateDTO> allCertificates(){
        File folder = new File("keystores/");
        File[] listOfFiles = folder.listFiles();

        List<CertificateDTO> list = new ArrayList<>();
        /*List<String> revoke= rs.getAliase();

        CertificateDTO cInfo = new CertificateDTO();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                cInfo = new CertificateDTO();
                cInfo.setAlias(file.getName().substring(0,file.getName().length()-4));
                if(!revoke.contains(cInfo.getAlias()))
                    list.add(cInfo);
            }
        }*/
        return list;
    }

    public List<CertificateDTO> allCertificatesBL(){ //bez listova
        File folder = new File("keystores/");
        File[] listOfFiles = folder.listFiles();


       /* List<String> revoke= rs.getAliase();

        List<CertificateDTO> list = new ArrayList<>();

        CertificateDTO cInfo = new CertificateDTO();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                cInfo = new CertificateDTO();
                cInfo.setAlias(file.getName().substring(0,file.getName().length()-4));

                if(!cInfo.getAlias().equals(".DS_S")){
                    if(checkIfValid(cInfo.getAlias())){
                        list.add(cInfo);
                    }
                }

            }
        }
        return list;*/
       return null;
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

    public String createNewSelfSignedCertificate(CertificateDTO certificateDTO) {
        if(ifAliasExist(certificateDTO.getAlias()))
            return "Alias exists";

        try {
            System.out.println("Create SS Service!");

            KeyPair keyPairSubject = generateKeyPair();

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate =  certificateDTO.getStartDate();
            Date endDate = certificateDTO.getEndDate();

            System.out.println(startDate);
            System.out.println(endDate);

            System.out.println(certificateDTO.getParent());
            System.out.println(certificateDTO.getCommName());
            System.out.println(certificateDTO.getOrg());
            System.out.println(certificateDTO.getOrgUnit());
            System.out.println(certificateDTO.getCountry());
            System.out.println(certificateDTO.getLoc());
            System.out.println(certificateDTO.getState());


            //Serijski broj sertifikata
            ++serialNumber;
            //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, certificateDTO.getCommName());
            builder.addRDN(BCStyle.O, certificateDTO.getOrg());
            builder.addRDN(BCStyle.OU, certificateDTO.getOrgUnit());
            builder.addRDN(BCStyle.C, certificateDTO.getCountry());
            builder.addRDN(BCStyle.L, certificateDTO.getLoc());
            builder.addRDN(BCStyle.ST, certificateDTO.getState());


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

            keyStoreWriter.write(certificateDTO.getAlias(),keyPairSubject.getPrivate(),password.toCharArray(),cert);

            keyStoreWriter.saveKeyStore("keystores/"+ certificateDTO.getAlias()+".p12",password.toCharArray());

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
        java.security.cert.Certificate cert = keyStoreReader.readCertificate(name,secret,alias);

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,alias,secret.toCharArray(),password.toCharArray());

        X509Certificate certi = (X509Certificate) cert;
        return "Imam GA";

    }

    public IssuerData getIssuerFromCertificate(String alias, String password){
        System.out.println("TRAZIM ISSUERA");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = "keystores/"+ alias +".p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();
        java.security.cert.Certificate cert = keyStoreReader.readCertificate(name,password,alias);

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,alias,password.toCharArray(),password.toCharArray());

        return issuerData;
    }

    public IssuerData getIssuerFromRootCertificate(String pass){
        System.out.println("TRAZIM ISSUERA ROOTA " + pass);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = "keystores/root.p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();
        java.security.cert.Certificate cert = keyStoreReader.readCertificate(name,pass,"root");
        if(cert == null)
            System.out.println("PUKAO SAM");

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,"root",pass.toCharArray(),pass.toCharArray());

        return issuerData;
    }
    public void createNewIssuedCertificate(CertificateDTO certificateDTO) {
        if(ifAliasExist(certificateDTO.getAlias()))
            return;

        System.out.println("PASS "  + password);
        IssuerData issuer;
        if(certificateDTO.getParent().equals("ROOT")){
            issuer = this.getIssuerFromRootCertificate(certificateDTO.getPassword());
        }else{
            issuer = this.getIssuerFromCertificate(certificateDTO.getParent(), certificateDTO.getPassword());
        }
        System.out.println("NASAO ISSUERA");

        KeyPair keyPairSubject = generateKeyPair();

        //Datumi od kad do kad vazi sertifikat
        Date startDate =  certificateDTO.getStartDate();
        Date endDate = certificateDTO.getEndDate();

        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, certificateDTO.getCommName());
        builder.addRDN(BCStyle.O, certificateDTO.getOrg());
        builder.addRDN(BCStyle.OU, certificateDTO.getOrgUnit());
        builder.addRDN(BCStyle.C, certificateDTO.getCountry());
        builder.addRDN(BCStyle.L, certificateDTO.getLoc());
        builder.addRDN(BCStyle.ST, certificateDTO.getState());


        String sn = BigInteger.valueOf(System.currentTimeMillis()).toString();

        SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);

        Certificate certificate = new Certificate(certificateDTO.getAlias(),
                sn,
                certificateDTO.getStartDate(),
                certificateDTO.getEndDate(),
                certificateDTO.getParent());


        CertificateGenerator certificateGenerator = new CertificateGenerator();
        X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuer);

        KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

        keyStoreWriter.loadKeyStore(null,password.toCharArray());

        keyStoreWriter.write(certificateDTO.getAlias(),keyPairSubject.getPrivate(),password.toCharArray(),cert);

        keyStoreWriter.saveKeyStore("keystores/"+ certificateDTO.getAlias()+".p12",password.toCharArray());


        certificateRepository.save(certificate);
        
    }



    public boolean checkIfValid(String alias){
        System.out.println("Validnost " +alias);

        String pass ="";

        if (alias.equals("root")){
            System.out.println("JESTE ROOOT");
            pass = secret;
        }else {
            System.out.println("NIJEEE");
            pass = password;
        }

        System.out.println("pass"+pass);

       /* Revoke revoke=new Revoke();
        revoke.setAlias(alias);
        revoke.setLeaf(false);
        ArrayList<String> lista= (ArrayList<String>) revokeService.getPovuceneAliasi();
        if(lista.contains(revoke.getAlias())) {
            System.out.println("Revoked");
            return false;
        }*/


        System.out.println("Ucitavanje cert");

        java.security.cert.Certificate cert = readFromKS(alias,pass);

        X509Certificate x509Certificate = (X509Certificate) cert;


        try {
            x509Certificate.checkValidity();
        } catch (CertificateExpiredException e) {
            e.printStackTrace();
            System.out.println("EXPIRED");
            return false;
        } catch (CertificateNotYetValidException e) {
            e.printStackTrace();
            System.out.println("Not YET VALID");
            return false;
        }

        IssuerData data = getIssuerFromCertificate(alias,pass);

        System.out.println(data.getX500name().toString());

        if (!alias.equals("root")){



        Certificate certificate = certificateRepository.findByAlias(alias);

        String aliasIs = certificate.getSignedByAlias();


        if (aliasIs.equals("root")){
            pass=secret;
        }

            java.security.cert.Certificate certIss = readFromKS(aliasIs,pass);

            try {
                cert.verify(certIss.getPublicKey());
            } catch (CertificateException e) {
                e.printStackTrace();
                return false;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();

            } catch (InvalidKeyException e) {
                e.printStackTrace();
                return false;
            } catch (NoSuchProviderException e) {
                e.printStackTrace();

            } catch (SignatureException e) {
                e.printStackTrace();
                return false;
            }

            return checkIfValid(aliasIs);
         }


        return true;
    }

    public java.security.cert.Certificate readFromKS(String alias, String pass){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = "keystores/"+ alias +".p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();

       return  keyStoreReader.readCertificate(name,pass,alias);
    }

    public List<Certificate> allCertificatesDB(){
        return certificateRepository.findAll();
    }



}
