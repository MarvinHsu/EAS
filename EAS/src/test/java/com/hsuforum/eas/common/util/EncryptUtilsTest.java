package com.hsuforum.eas.common.util;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Base64;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.common.web.util.EncryptUtils;

import lombok.extern.slf4j.Slf4j;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class EncryptUtilsTest {
	@Test
	public void testEncode(){
		String myplaintext = "abcd1234";

	    try {
	        CipherText ciphertext = ESAPI.encryptor().encrypt( new PlainText(myplaintext) );
	        String base64=Base64.encodeBytes(ciphertext.asPortableSerializedByteArray());
	        log.info("ciphertext base64="+base64);
	        
	        
	                    
	        CipherText cipherText2=CipherText.fromPortableSerializedBytes(Base64.decode(base64));
	        
	        PlainText recoveredPlaintext = ESAPI.encryptor().decrypt(cipherText2) ;
	        log.info("recoveredPlaintext="+recoveredPlaintext);
	        assert myplaintext.equals( recoveredPlaintext.toString() );
	    } catch(Exception ex) {
	    	log.info("exception="+ex.getMessage());
	    	ex.printStackTrace();
	    }
	}
	
	@Test
	public void testDecode(){
		
	    try {
	    	String filePath="/Application/EAS/EncodeKey";
	    	File serializedFile = new File(filePath);
            FileInputStream fis = new FileInputStream(serializedFile);
            
            int avail = fis.available();
            byte[] bytes = new byte[avail];
            fis.read(bytes, 0, avail);
            fis.close();

            CipherText restoredCipherText =CipherText.fromPortableSerializedBytes(bytes);

            log.info("restoredCipherText="+restoredCipherText);
	        
	        PlainText recoveredPlaintext = ESAPI.encryptor().decrypt(restoredCipherText) ;
	        log.info("recoveredPlaintext="+recoveredPlaintext);
	        
	    } catch(Exception ex) {
	    	log.info("exception="+ex.getMessage());
	    	ex.printStackTrace();
	    }
	}
	
	@Test
	public void testEncrypt() throws EncryptionException{
		String plainText = "abcd1234";

		String encryptString=EncryptUtils.encrypt(plainText);
		log.info("plainText="+plainText+" encryptString="+encryptString);		
		
	}
	@Test
	public void testDecrypt() throws EncryptionException{
		String base64Text = "ETMsDgAAAX4J7/2MABRBRVMvQ0JDL1BLQ1M1UGFkZGluZwCAABAAEH2OYItSbMBcxX0vA5J5utcAAAAQ6urWCdHdERIo3PkiY3KFBQAUjFs4Z7cXq8tyjRR5OPTrE72MCjQ=";

		String plainText=EncryptUtils.decrypt(base64Text);
		log.info("plainText="+plainText);		
		
	}
}
