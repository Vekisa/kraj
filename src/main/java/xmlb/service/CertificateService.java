package xmlb.service;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500NameBuilder;

import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.x509.X509V2CRLGenerator;
import org.bouncycastle.x509.extension.X509ExtensionUtil;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.*;
import java.security.cert.*;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.KeyStoreReader;
import xmlb.controller.CertificateController;
import xmlb.dto.CertificateDTO;
import xmlb.model.*;
import xmlb.model.Certificate;
import xmlb.repository.CertificateRepository;
import xmlb.repository.CompanyRepository;
import java.net.URL;
import java.net.URLConnection;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Extension;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CertificateService {

    @Value("ng servfe")
    private String secret;

    @Value("${password.for.keystore}")
    private String password;

    @Value("${certificate.serial.number}")
    private int serialNumber;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    private String rootPath = "keystores/root.p12";

    private String pathToKeystores = "keystores/";


    public List<CertificateDTO> search(String alias, Boolean leafs, Boolean root){
        if(alias == null || leafs == null || root == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters cannot be null");

        List<CertificateDTO> list = new ArrayList<>();
        List<Certificate> certificates = certificateRepository.findAll();
        for(Certificate certificate : certificates)
            if(certificate.getAlias().contains(alias) && certificate.getAlias().equals("root") && root){
                list.add(new CertificateDTO(certificate));
            }else if(certificate.getAlias().contains(alias) && isCertificateOk(certificate) && certificate.getLeaf() && leafs) {
                list.add(new CertificateDTO(certificate));
            }else if(certificate.getAlias().contains(alias) && isCertificateOk(certificate) && !certificate.getLeaf()){
                list.add(new CertificateDTO(certificate));
            }
        return list;
    }


    public List<CertificateDTO> all(){
        //UBACITI PROVERU ZA TRENUTNOOG KORISNIKA
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> certificateWithoutLeafs = new ArrayList<>();

        for(Certificate certificate : certificates){
            if(isCertificateOk(certificate))
                certificateWithoutLeafs.add(certificate);
        }

        return CreateDTOList.certificates(certificateWithoutLeafs);
    }

    public List<CertificateDTO> allWithoutRoot(){
        //UBACITI PROVERU ZA TRENUTNOOG KORISNIKA
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> certificateWithoutLeafs = new ArrayList<>();

        for(Certificate certificate : certificates){
            if(isCertificateOk(certificate) && !certificate.getAlias().equals("root"))
                certificateWithoutLeafs.add(certificate);
        }

        return CreateDTOList.certificates(certificateWithoutLeafs);
    }

    public List<CertificateDTO> allCertificatesWithoutLeafs(){
        //UBACITI PROVERU ZA TRENUTNOOG KORISNIKA
        List<Certificate> certificates = certificateRepository.findAll();
        List<Certificate> certificateWithoutLeafs = new ArrayList<>();

        for(Certificate certificate : certificates){
            System.out.println("PROVERAMA: " + certificate.getAlias());
            if(!certificate.getLeaf() && isCertificateOk(certificate)) {
                System.out.println("UBACIO: " + certificate.getAlias());
                certificateWithoutLeafs.add(certificate);
            }
        }

        return CreateDTOList.certificates(certificateWithoutLeafs);
    }

    public String createNewSelfSignedCertificate(CertificateDTO certificateDTO) {
        try {
            System.out.println("Create SS Service!");

            KeyPair keyPairSubject = generateKeyPair();

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate =  certificateDTO.getStartDate();
            Date endDate = certificateDTO.getEndDate();

            //Serijski broj sertifikata
            ++serialNumber;
            //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, certificateDTO.getCommonName());
            builder.addRDN(BCStyle.O, certificateDTO.getOrganization());
            builder.addRDN(BCStyle.OU, certificateDTO.getOrganizationUnit());
            builder.addRDN(BCStyle.C, certificateDTO.getCountry());
            builder.addRDN(BCStyle.L, certificateDTO.getLocality());
            builder.addRDN(BCStyle.ST, certificateDTO.getState());

            SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), String.valueOf(serialNumber), startDate, endDate);
            IssuerData issuerData = new IssuerData(keyPairSubject.getPrivate(),builder.build());

            CertificateGenerator certificateGenerator = new CertificateGenerator();
            X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuerData);

            KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

            keyStoreWriter.loadKeyStore(null,password.toCharArray());

            keyStoreWriter.write("root",keyPairSubject.getPrivate(),password.toCharArray(),cert);

            keyStoreWriter.saveKeyStore(pathToKeystores +"root.p12",password.toCharArray());

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

    public IssuerData getIssuerFromCertificate(String organization, String alias, String password){
        String name = pathToKeystores + organization +".p12";
        KeyStoreReader keyStoreReader = new KeyStoreReader();

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name,alias,password.toCharArray(),password.toCharArray());

        return issuerData;
    }

    public IssuerData getIssuerFromRootCertificate(String pass){
        KeyStoreReader keyStoreReader = new KeyStoreReader();
        java.security.cert.Certificate cert = keyStoreReader.readCertificate(rootPath,pass,"root");
        IssuerData issuerData = keyStoreReader.readIssuerFromStore(rootPath,"root",pass.toCharArray(),pass.toCharArray());

        return issuerData;
    }

    public void createNewIssuedCertificate(CertificateDTO certificateDTO) {

        //VALIDACIJA
        validateCertificateFromFront(certificateDTO);

        IssuerData issuer;
        if(certificateDTO.getParent().toUpperCase().equals("1")){
            issuer = this.getIssuerFromRootCertificate(certificateDTO.getPassword());

        }else{
            Certificate certificateParent = findBySerialNumber(certificateDTO.getParent());
            if(certificateParent == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No certificate with this serial number");

            issuer = this.getIssuerFromCertificate(certificateParent.getCompany().getName(), certificateParent.getAlias(), certificateDTO.getPassword());
        }

        KeyPair keyPairSubject = generateKeyPair();

        //Datumi od kad do kad vazi sertifikat
        Date startDate =  certificateDTO.getStartDate();
        Date endDate = certificateDTO.getEndDate();

        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, certificateDTO.getCommonName());
        builder.addRDN(BCStyle.O, certificateDTO.getOrganization());
        builder.addRDN(BCStyle.OU, certificateDTO.getOrganizationUnit());
        builder.addRDN(BCStyle.C, certificateDTO.getCountry());
        builder.addRDN(BCStyle.L, certificateDTO.getLocality());
        builder.addRDN(BCStyle.ST, certificateDTO.getState());


        String serialNumber = BigInteger.valueOf(System.currentTimeMillis()).toString();

        SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), serialNumber, startDate, endDate);

        Certificate certificateParent = findBySerialNumber(certificateDTO.getParent());
        if(certificateParent == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Parent certificate does not exist");

        Certificate certificate = new Certificate(certificateDTO.getAlias(),
                serialNumber,
                certificateDTO.getStartDate(),
                certificateDTO.getEndDate(),
                certificateParent.getAlias(),
                false,
                certificateDTO.getLeaf(),
                certificateDTO.getCountry(),
                certificateDTO.getState(),
                certificateDTO.getLocality(),
                certificateDTO.getOrganizationUnit(),
                certificateDTO.getCommonName());

        CertificateGenerator certificateGenerator = new CertificateGenerator();
       // V3TBSCertificateGenerator certificateGenerator1= new V3TBSCertificateGenerator();

        X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuer);
        //TBSCertificate cert1 = certificateGenerator1.generateTBSCertificate();
        KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

        /*byte[] extVal = cert.getExtensionValue(Extension.authorityInfoAccess.getId());

        AccessDescription ad=new AccessDescription(AccessDescription.id_ad_caIssuers, new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(linkTo(methodOn(CertificateController.class).getCertificate(certificateDTO.getParent())).toUri().toString())));
        AccessDescription ocsp= new AccessDescription(AccessDescription.id_ad_ocsp, new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(linkTo(methodOn(CertificateController.class).allRevoke()).toUri().toString())));
        ASN1EncodableVector aia_ASN=new ASN1EncodableVector();
        aia_ASN.add(ad);
        aia_ASN.add(ocsp);
        AuthorityInformationAccess aia =  AuthorityInformationAccess.getInstance(new DERSequence(aia_ASN));
        //AuthorityInformationAccess aia = AuthorityInformationAccess.getInstance(X509ExtensionUtil.fromExtensionValue(extVal));
        //X509v2CRLBuilder crlgen = new X509v2CRLBuilder(issuer.getX500name(), new Date());
        //crlgen.addExtension(Extension.authorityInfoAccess, false, aia);

*/

        Company company = companyRepository.findCompanyByName(certificateDTO.getOrganization());
        if (company == null){
            keyStoreWriter.loadKeyStore(null,password.toCharArray());
            keyStoreWriter.write(certificateDTO.getAlias(),keyPairSubject.getPrivate(),password.toCharArray(),cert);
            company = new Company();
            company.setName(certificateDTO.getOrganization());
            company.setFilePath(pathToKeystores + certificateDTO.getOrganization()+".p12");
            company.getCertificates().add(certificate);
            certificate.setCompany(company);
            companyRepository.save(company);
        }else{
            if(companyService.hasCertificateWithAlias(company.getId(),certificate.getAlias()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Alias in that company keystore already exist");

            keyStoreWriter.loadKeyStore(pathToKeystores+ certificateDTO.getOrganization()+".p12",password.toCharArray());
            keyStoreWriter.write(certificateDTO.getAlias(),keyPairSubject.getPrivate(),password.toCharArray(),cert);
            company.getCertificates().add(certificate);
            certificate.setCompany(company);
            companyRepository.save(company);
        }

        keyStoreWriter.saveKeyStore(pathToKeystores + certificateDTO.getOrganization()+".p12",password.toCharArray());
        certificateRepository.save(certificate);

    }

    /*public boolean checkIfValid(String alias){
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

        Revoke revoke=new Revoke();
        revoke.setAlias(alias);
        revoke.setLeaf(false);
        ArrayList<String> lista= (ArrayList<String>) revokeService.getPovuceneAliasi();
        if(lista.contains(revoke.getAlias())) {
            System.out.println("Revoked");
            return false;
        }


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
    }*/

    public java.security.cert.Certificate readFromKS(String alias, String pass){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String name = "keystores/"+ alias +".p12";

        KeyStoreReader keyStoreReader = new KeyStoreReader();

       return  keyStoreReader.readCertificate(name,pass,alias);
    }

    public void revokeCertificate(String serialNumber){
        if(serialNumber == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial number is null");

        List<Certificate> certificates = certificateRepository.findAll();
        for(Certificate certificate : certificates){
            if(certificate.getSerialNumber().equals(serialNumber))
                if(certificate.getRevoked())
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate already revoked");
                else{
                    certificate.setRevoked(true);
                    certificateRepository.save(certificate);
                    return;
                }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Certificate does not exist");
    }

    private Boolean isCertificateOk(Certificate certificate){
        if(!certificate.getRevoked() &&
                (certificate.getEndDate().after(new Date()) || certificate.getEndDate().equals(new Date())))
            return true;

        return false;
    }

    public Certificate findBySerialNumber(String serialNumber){
        List<Certificate> certificates = certificateRepository.findAll();
        for(Certificate certificate : certificates)
            if(certificate.getSerialNumber().equals(serialNumber) && isCertificateOk(certificate))
                return certificate;

        return null;
    }

    private void validateCertificateFromFront(CertificateDTO certificateDTO){
        if(certificateDTO.getStartDate().before(new Date()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad start date");
        if(certificateDTO.getEndDate().before(new Date()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad end date");
        if(certificateDTO.getEndDate().before(certificateDTO.getStartDate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad end and start dates");


        if(certificateDTO.getLeaf() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Leaf is null");

        String serialNumber = certificateDTO.getParent();
        if(serialNumber == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent is null");

        Certificate certificateParent = findBySerialNumber(serialNumber);
        if(certificateParent == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent certificate does not exist");

        if(certificateParent.getEndDate().before(certificateDTO.getEndDate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent end date is before end date");

        if(certificateParent.getStartDate().after(certificateDTO.getStartDate()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent start date is after start date");

        if(certificateParent.getCompany().getName().equals(certificateDTO.getOrganization()) && !certificateParent.getCompany().getName().equals("rootComp"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent certificate does not exist");
    }

   /* public X509CRL getCRL() throws CertificateException, IOException {

        List<Certificate> lista = certificateRepository.findAll();
        List<Certificate> listaR=null;

        //String certificatePath = "C:\\Users\\user1\\Desktop\\test.cer";

        CertificateFactory cf = CertificateFactory.getInstance("X509");

        X509Certificate certificate = null;
        X509CRLEntry revokedCertificate = null;
        X509CRL crl = null;
        //certificate = (X509Certificate) cf.generateCertificate(new FileInputStream(new File(certificatePath)));

        for (Certificate ce : lista){
            if(ce.getRevoked()){
                listaR.add(ce);

            }

        }


        URL url = new URL("http://<someUrl from certificate>.crl");
        URLConnection connection = url.openConnection();
        //crl.getRevokedCertificates().add(eCerts);
        try(DataInputStream inStream = new DataInputStream(connection.getInputStream())){

            crl = (X509CRL)cf.generateCRL(inStream);
        } catch (CRLException e) {
            e.printStackTrace();
        }

       /* revokedCertificate = crl.getRevokedCertificate(certificate.getSerialNumber());

        if(revokedCertificate !=null){
            System.out.println("Revoked");
        }
        else{
            System.out.println("Valid");
        }

        return crl;
    }*/


}

