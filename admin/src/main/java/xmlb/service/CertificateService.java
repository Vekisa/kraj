package xmlb.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500NameBuilder;

import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.io.*;
import java.security.cert.*;

import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import xmlb.KeyStoreReader;
import xmlb.controller.CertificateController;
import xmlb.dto.CertificateDTO;
import xmlb.model.*;
import xmlb.model.Certificate;
import xmlb.model.User.User;
import xmlb.repository.CertificateRepository;
import xmlb.repository.CompanyRepository;

import java.net.URL;
import java.net.URLConnection;

import xmlb.repository.UserRepository;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Extension;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CertificateService {

    private Logging logging = new Logging(getClass());

    @Value("Xmlsecuritypass")
    private String secret;

    @Value("${password.for.keystore}")
    private String password;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserRepository userRepository;

    private String rootPath = "keystores/root.p12";

    private String pathToKeystores = "keystores/";


    public List<CertificateDTO> search(String alias, Boolean leafs, Boolean root) {
        if (alias == null || leafs == null || root == null) {
            logging.printError("IN FUNC: Parameters cannot be null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parameters cannot be null");
        }

        List<CertificateDTO> list = new ArrayList<>();
        List<Certificate> certificates = certificateRepository.findAll();
        for (Certificate certificate : certificates)
            if (certificate.getAlias().contains(alias) && certificate.getAlias().equals("root") && root) {
                list.add(new CertificateDTO(certificate));
            } else if (certificate.getAlias().contains(alias) && isCertificateOk(certificate) && certificate.getLeaf() && leafs) {
                list.add(new CertificateDTO(certificate));
            } else if (certificate.getAlias().contains(alias) && isCertificateOk(certificate) && !certificate.getLeaf()) {
                list.add(new CertificateDTO(certificate));
            }

        //logging.printInfo("IN FUNC: Success");
        return list;
    }


    public List<CertificateDTO> all() {
        List<Certificate> certificates = filterCertificates(certificateRepository.findAll());
        List<Certificate> certificateWithoutLeafs = new ArrayList<>();

        for (Certificate certificate : certificates) {
            if (isCertificateOk(certificate))
                certificateWithoutLeafs.add(certificate);
        }

        //logging.printInfo("IN FUNC: Success");
        return CreateDTOList.certificates(certificateWithoutLeafs);
    }

    public List<CertificateDTO> allWithoutRoot() {
        List<Certificate> certificates = filterCertificates(certificateRepository.findAll());
        List<Certificate> certificateWithoutLeafs = new ArrayList<>();

        for (Certificate certificate : certificates) {
            if (isCertificateOk(certificate) && !certificate.getAlias().equals("root"))
                certificateWithoutLeafs.add(certificate);
        }

        //logging.printInfo("IN FUNC: Success");
        return CreateDTOList.certificates(certificateWithoutLeafs);
    }

    public List<CertificateDTO> allCertificatesWithoutLeafs() {
        List<Certificate> certificates = filterCertificates(certificateRepository.findAll());
        List<Certificate> certificateWithoutLeafs = new ArrayList<>();

        for (Certificate certificate : certificates) {
            System.out.println("IF VALID: " + checkIfValid(certificate.getSerialNumber()));
            System.out.println("IF LEAF: " +certificate.getLeaf() );
            if (!certificate.getLeaf() && checkIfValid(certificate.getSerialNumber())) {
                System.out.println("UBACIO: " + certificate.getAlias());
                certificateWithoutLeafs.add(certificate);
            }
        }

        //logging.printInfo("IN FUNC: Success");
        return CreateDTOList.certificates(certificateWithoutLeafs);
    }

    public String createNewSelfSignedCertificate(CertificateDTO certificateDTO) {
        try {
            System.out.println("Create SS Service!");

            KeyPair keyPairSubject = generateKeyPair();

            //Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = certificateDTO.getStartDate();
            Date endDate = certificateDTO.getEndDate();

            //Serijski broj sertifikata

            //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, certificateDTO.getCommonName());
            builder.addRDN(BCStyle.O, certificateDTO.getOrganization());
            builder.addRDN(BCStyle.OU, certificateDTO.getOrganizationUnit());
            builder.addRDN(BCStyle.C, certificateDTO.getCountry());
            builder.addRDN(BCStyle.L, certificateDTO.getLocality());
            builder.addRDN(BCStyle.ST, certificateDTO.getState());

            SubjectData subjectData = new SubjectData(keyPairSubject.getPublic(), builder.build(), String.valueOf("IZMENI"), startDate, endDate);
            IssuerData issuerData = new IssuerData(keyPairSubject.getPrivate(), builder.build());

            CertificateGenerator certificateGenerator = new CertificateGenerator();
            X509Certificate cert = certificateGenerator.generateCertificate(subjectData, issuerData);

            KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

            keyStoreWriter.loadKeyStore(null, password.toCharArray());

            keyStoreWriter.write("root", keyPairSubject.getPrivate(), password.toCharArray(), cert);

            keyStoreWriter.saveKeyStore(pathToKeystores + "root.p12", password.toCharArray());

            logging.printInfo("IN FUNC: Success");
            return "Successful";

        } catch (Exception e) {
            logging.printError("IN FUNC: " + e.getMessage());
            return e.getMessage();
        }
    }

    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            logging.printInfo("IN FUNC: Success");
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            logging.printError("IN FUNC: " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            logging.printError("IN FUNC: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public IssuerData getIssuerFromCertificate(String organization, String alias, String password) {
        String name = pathToKeystores + organization + ".p12";
        KeyStoreReader keyStoreReader = new KeyStoreReader();

        IssuerData issuerData = keyStoreReader.readIssuerFromStore(name, alias, password.toCharArray(), password.toCharArray());

        logging.printInfo("IN FUNC: Success");
        return issuerData;
    }

    public IssuerData getIssuerFromRootCertificate(String pass) {
        KeyStoreReader keyStoreReader = new KeyStoreReader();
        java.security.cert.Certificate cert = keyStoreReader.readCertificate(rootPath, pass, "root");
        IssuerData issuerData = keyStoreReader.readIssuerFromStore(rootPath, "root", pass.toCharArray(), pass.toCharArray());

        logging.printInfo("IN FUNC: Success");
        return issuerData;
    }

    public void createNewIssuedCertificate(CertificateDTO certificateDTO) throws CertIOException, OperatorCreationException, CertificateException {

        System.out.println("DATUM "+certificateDTO.getStartDate());

        //VALIDACIJA
        validateCertificateFromFront(certificateDTO);

        IssuerData issuer;
        if (certificateDTO.getParent().toUpperCase().equals("1")) {
            issuer = this.getIssuerFromRootCertificate(certificateDTO.getPassword());

        } else {
            Certificate certificateParent = findBySerialNumber(certificateDTO.getParent());
            if (certificateParent == null) {
                logging.printError("IN FUNC: No certificate with this serial number");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No certificate with this serial number");
            }

            issuer = this.getIssuerFromCertificate(certificateParent.getCompany().getName(), certificateParent.getAlias(), certificateDTO.getPassword());
        }

        KeyPair keyPairSubject = generateKeyPair();

        //Datumi od kad do kad vazi sertifikat
        Date startDate = certificateDTO.getStartDate();
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
        if (certificateParent == null) {
            logging.printError("IN FUNC: Parent certificate does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Parent certificate does not exist");
        }

        Certificate certificate = new Certificate(certificateDTO.getAlias(),
                serialNumber,
                certificateDTO.getStartDate(),
                certificateDTO.getEndDate(),
                certificateParent.getAlias(),
                certificateParent.getSignedBySerialNumber(),
                false,
                certificateDTO.getLeaf(),
                certificateDTO.getCountry(),
                certificateDTO.getState(),
                certificateDTO.getLocality(),
                certificateDTO.getOrganizationUnit(),
                certificateDTO.getCommonName());

        //CertificateGenerator certificateGenerator = new CertificateGenerator();
        // V3TBSCertificateGenerator certificateGenerator1= new V3TBSCertificateGenerator();
        X509v3CertificateBuilder certificateGenerator = new JcaX509v3CertificateBuilder(issuer.getX500name(),
                new BigInteger(serialNumber), new java.sql.Date(certificateDTO.getStartDate().getTime()), new java.sql.Date(certificateDTO.getEndDate().getTime()), builder.build(), keyPairSubject.getPublic());


        //TBSCertificate cert1 = certificateGenerator1.generateTBSCertificate();
        KeyStoreWriter keyStoreWriter = new KeyStoreWriter();

        //byte[] extVal = cert.getExtensionValue(Extension.authorityInfoAccess.getId());

        AccessDescription ad = new AccessDescription(AccessDescription.id_ad_caIssuers, new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(linkTo(methodOn(CertificateController.class).getCertificate(certificateDTO.getParent())).toUri().toString())));
        AccessDescription ocsp = new AccessDescription(AccessDescription.id_ad_ocsp, new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(linkTo(methodOn(CertificateController.class).allRevoke()).toUri().toString())));
        ASN1EncodableVector aia_ASN = new ASN1EncodableVector();
        aia_ASN.add(ad);
        aia_ASN.add(ocsp);
        //AuthorityInformationAccess aia =  AuthorityInformationAccess.getInstance(new DERSequence(aia_ASN));
        //AuthorityInformationAccess aia = AuthorityInformationAccess.getInstance(X509ExtensionUtil.fromExtensionValue(extVal));
        //X509v2CRLBuilder crlgen = new X509v2CRLBuilder(issuer.getX500name(), new Date());
        certificateGenerator.addExtension(Extension.authorityInfoAccess, false, new DERSequence(aia_ASN));

        JcaContentSignerBuilder singerBuilder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");

        singerBuilder = singerBuilder.setProvider("BC");

        ContentSigner contentSigner = singerBuilder.build(issuer.getPrivateKey());

        X509CertificateHolder cHol = certificateGenerator.build(contentSigner);
        JcaX509CertificateConverter pom = new JcaX509CertificateConverter();
        X509Certificate cert = pom.getCertificate(cHol);

        // X509Certificate cert = certificateGenerator.generateCertificate(subjectData,issuer);
        Company company = companyRepository.findCompanyByName(certificateDTO.getOrganization());
        if (company == null) {
            keyStoreWriter.loadKeyStore(null, password.toCharArray());
            keyStoreWriter.write(certificateDTO.getAlias(), keyPairSubject.getPrivate(), password.toCharArray(), cert);
            company = new Company();
            company.setName(certificateDTO.getOrganization());
            company.setFilePath(pathToKeystores + certificateDTO.getOrganization() + ".p12");
            company.getCertificates().add(certificate);
            certificate.setCompany(company);
            companyRepository.save(company);
        } else {
            if (companyService.hasCertificateWithAlias(company.getId(), certificate.getAlias())) {
                logging.printError("IN FUNC: Alias in that company keystore already exist");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alias in that company keystore already exist");
            }

            keyStoreWriter.loadKeyStore(pathToKeystores + certificateDTO.getOrganization() + ".p12", password.toCharArray());
            keyStoreWriter.write(certificateDTO.getAlias(), keyPairSubject.getPrivate(), password.toCharArray(), cert);
            company.getCertificates().add(certificate);
            certificate.setCompany(company);
            companyRepository.save(company);
        }

        keyStoreWriter.saveKeyStore(pathToKeystores + certificateDTO.getOrganization() + ".p12", password.toCharArray());
        certificateRepository.save(certificate);

        logging.printInfo("Certificate: Created");

    }

    public boolean checkIfValid(String serialNumber) {

        System.out.println("CHECK IF VALID");

        String pass = "";

        if (serialNumber.equals("1")) {
            pass = secret;
        } else {
            pass = password;
        }

        if (checkRevokeStatus(serialNumber)) {
            logging.printInfo("IN FUNC: FALSE REVOKED");
            return false;
        }

        java.security.cert.Certificate cert = readFromKS(serialNumber, pass);

        X509Certificate x509Certificate = (X509Certificate) cert;

        try {
            x509Certificate.checkValidity();
        } catch (CertificateExpiredException e) {
            e.printStackTrace();
            logging.printInfo("IN FUNC: FALSE(Expired)");
            return false;
        } catch (CertificateNotYetValidException e) {
            e.printStackTrace();
            logging.printInfo("IN FUNC: FALSE(Not yet valid)");
            return false;
        }

        Certificate certificate = getCertificateFromDB(serialNumber);

        String alias = certificate.getAlias();
        String organisationUnit = certificate.getCompany().getName();


        IssuerData data = getIssuerFromCertificate(organisationUnit, alias, pass);

        System.out.println(data.getX500name().toString());

        if (!serialNumber.equals("1")) {


            String serialNumberIssuer = certificate.getSignedBySerialNumber();


            if (serialNumberIssuer.equals("1")) {
                pass = secret;
            }

            java.security.cert.Certificate certIss = readFromKS(serialNumberIssuer, pass);

            try {
                cert.verify(certIss.getPublicKey());
            } catch (CertificateException e) {
                e.printStackTrace();
                logging.printInfo("IN FUNC: FALSE " + e.getMessage());
                return false;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                logging.printInfo("IN FUNC: FALSE " + e.getMessage());
            } catch (InvalidKeyException e) {
                e.printStackTrace();
                logging.printInfo("IN FUNC: FALSE " + e.getMessage());
                return false;
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
                logging.printInfo("IN FUNC: FALSE " + e.getMessage());

            } catch (SignatureException e) {
                e.printStackTrace();
                logging.printInfo("IN FUNC: FALSE " + e.getMessage());
                return false;
            }

            return checkIfValid(serialNumberIssuer);
        }
        logging.printInfo("IN FUNC: Success");
        return true;
    }


    public java.security.cert.Certificate readFromKS(String serialNumber, String pass) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Certificate certificate = getCertificateFromDB(serialNumber);
        certificate.getCompany().getFilePath();

        String name = certificate.getCompany().getFilePath();


        System.out.println(name);



        KeyStoreReader keyStoreReader = new KeyStoreReader();

        logging.printInfo("IN FUNC: Read Success");
        return keyStoreReader.readCertificate(name, pass, certificate.getAlias());
    }

    public Certificate getCertificateFromDB(String serialNumber) {

        Certificate optionalCertificate = certificateRepository.findBySerialNumber(serialNumber);

        if (optionalCertificate == null) {
            logging.printError("IN FUNC: Certificate does not exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificate does not exist");
        }

        logging.printInfo("IN FUNC: Success");
        return optionalCertificate;
    }


    public void revokeCertificate(String serialNumber) {
        if (serialNumber == null) {
            logging.printError("IN FUNC: Serial number is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial number is null");
        }

        Certificate certificate = getCertificateFromDB(serialNumber);

        if (certificate.getRevoked()) {
            logging.printError("IN FUNC: Certificate already revoked");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Certificate already revoked");
        } else {
            certificate.setRevoked(true);
            certificateRepository.save(certificate);
            logging.printInfo("IN FUNC: Success");
            return;
        }
    }

    public boolean checkRevokeStatus(String serialNumber) {

        Certificate certificate = getCertificateFromDB(serialNumber);

        if (certificate.getRevoked()) {
            logging.printInfo("IN FUNC: TRUE");
            return true;
        } else {
            logging.printInfo("IN FUNC: FALSE");
            return false;
        }

    }

    private Boolean isCertificateOk(Certificate certificate) {
        if (!certificate.getRevoked() &&
                (certificate.getEndDate().after(new Date()) || certificate.getEndDate().equals(new Date()))) {
            logging.printInfo("IN FUNC: TRUE");
            return true;
        }
        logging.printInfo("IN FUNC: FALSE");
        return false;
    }

    public Certificate findBySerialNumber(String serialNumber) {
        List<Certificate> certificates = certificateRepository.findAll();
        for (Certificate certificate : certificates)
            if (certificate.getSerialNumber().equals(serialNumber) && isCertificateOk(certificate)) {
                logging.printInfo("IN FUNC: Success");
                return certificate;
            }
        logging.printInfo("IN FUNC: NULL");
        return null;
    }

    private void validateCertificateFromFront(CertificateDTO certificateDTO) {

        if (!Pattern.matches(Regex.stringLong, certificateDTO.getCountry())) {
            logging.printError("IN FUNC: Field country is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field country is not correct");
        }
        if (!Pattern.matches(Regex.stringLong, certificateDTO.getState())) {
            logging.printError("IN FUNC: Field state is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field state is not correct");
        }
        if (!Pattern.matches(Regex.stringLong, certificateDTO.getLocality())) {
            logging.printError("IN FUNC: Field locality is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field locality is not correct");
        }
        if (!Pattern.matches(Regex.stringLong, certificateDTO.getOrganization())) {
            logging.printError("IN FUNC: Field organization is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field organization is not correct");
        }
        if (!Pattern.matches(Regex.stringLong, certificateDTO.getOrganizationUnit())) {
            logging.printError("IN FUNC: Field organizational unit is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field organizational unit is not correct");
        }
        if (!Pattern.matches(Regex.stringShort, certificateDTO.getCommonName())) {
            logging.printError("IN FUNC: Field common name unit is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field common name unit is not correct");
        }
        if (!Pattern.matches(Regex.stringShort, certificateDTO.getOrganizationUnit())) {
            logging.printError("IN FUNC: Field alias unit is not correct");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Field alias unit is not correct");
        }

        if (certificateDTO.getStartDate() == null || certificateDTO.getEndDate() == null) {
            logging.printError("IN FUNC: Dates cannot be null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dates cannot be null");
        }
        if (certificateDTO.getStartDate().before(new Date())) {
            logging.printError("IN FUNC: Bad start date");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad start date");
        }
        if (certificateDTO.getEndDate().before(new Date())) {
            logging.printError("IN FUNC: Bad end date");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad end date");
        }
        if (certificateDTO.getEndDate().before(certificateDTO.getStartDate())) {
            logging.printError("IN FUNC: Bad end and start dates");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad end and start dates");
        }


        if (certificateDTO.getLeaf() == null) {
            logging.printError("IN FUNC: Leaf is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Leaf is null");
        }

        String serialNumber = certificateDTO.getParent();
        if (serialNumber == null) {
            logging.printError("IN FUNC: Parent is null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent is null");
        }

        Certificate certificateParent = findBySerialNumber(serialNumber);

        if (certificateParent == null) {
            logging.printError("IN FUNC: Parent certificate does not exist");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent certificate does not exist");
        }

        if (!certificateParent.getSerialNumber().equals("1") && !certificateParent.getCompany().getName().equals(certificateDTO.getOrganization())) {
            logging.printError("IN FUNC: Different organizations");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Different organizations");
        }

        if (certificateParent.getEndDate().before(certificateDTO.getEndDate())) {
            logging.printError("IN FUNC: Parent end date is before end date");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent end date is before end date");
        }


        if (certificateParent.getStartDate().after(certificateDTO.getStartDate())) {
            logging.printError("IN FUNC: Parent start date is after start date");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent start date is after start date");
        }

        if (certificateParent.getEndDate().before(certificateDTO.getEndDate())) {
            logging.printError("IN FUNC: Parent end date is before end date");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parent end date is before end date");
        }

        logging.printInfo("IN FUNC: Success");
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

    private List<Certificate> filterCertificates(List<Certificate> certificates) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        List<Certificate> filteredCertificates = new ArrayList<>();
        if (user.get().getId() == 1)
            return certificates;

        for (Certificate certificate : certificates)
            if (certificate.getCompany().getId() == user.get().getCompany().getId())
                filteredCertificates.add(certificate);


        return filteredCertificates;
    }

}

