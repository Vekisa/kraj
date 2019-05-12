package xmlb.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import xmlb.model.User.User;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Optional;

public class KeyStoreWriter {
	//KeyStore je Java klasa za citanje specijalizovanih datoteka koje se koriste za cuvanje kljuceva
	//Tri tipa entiteta koji se obicno nalaze u ovakvim datotekama su:
	// - Sertifikati koji ukljucuju javni kljuc
	// - Privatni kljucevi
	// - Tajni kljucevi, koji se koriste u simetricnima siframa
	private KeyStore keyStore;
	protected final Log LOGGER = LogFactory.getLog(getClass());

	public KeyStoreWriter() {
		try {
			keyStore = KeyStore.getInstance("PKCS12"); 
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void loadKeyStore(String fileName, char[] password) {
		try {
			if(fileName != null) {
				keyStore.load(new FileInputStream(fileName), password);
				LOGGER.info("KeyStore loaded " + fileName  );
			} else {
				//Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
				keyStore.load(null, password);
			}
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("KeyStore" + fileName + " not loaded "  + e.getMessage());
			e.printStackTrace();
		} catch (CertificateException e) {
			LOGGER.error("KeyStore" + fileName + " not loaded "  + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LOGGER.error("KeyStore" + fileName + " not loaded "  + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("KeyStore" + fileName + " not loaded "  + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void saveKeyStore(String fileName, char[] password) {
		try {
			keyStore.store(new FileOutputStream(fileName), password);
			LOGGER.info("KeyStore saved " + fileName);
		} catch (KeyStoreException e) {
			LOGGER.error("KeyStore" + fileName + " not saved " + e.getMessage());
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("KeyStore" + fileName + " not saved "  + e.getMessage());
			e.printStackTrace();
		} catch (CertificateException e) {
			LOGGER.error("KeyStore" + fileName + " not saved "  + e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			LOGGER.error("KeyStore" + fileName + " not saved "  + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("KeyStore" + fileName + " not saved "  + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
		try {
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
			LOGGER.info("Certificate " +  alias + " saved at keyStore" );
		} catch (KeyStoreException e) {
			LOGGER.error("Certificate " +  alias + " not saved at keyStore: " + e.getMessage() );
			e.printStackTrace();
		}
	}
}