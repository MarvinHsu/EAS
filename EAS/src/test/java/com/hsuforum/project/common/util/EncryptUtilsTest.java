package com.hsuforum.project.common.util;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Ignore;
import org.junit.Test;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Base64;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;

import com.hsuforum.common.web.util.EncryptUtils;

public class EncryptUtilsTest {
	@Test
	@Ignore
	public void testEncode(){
		String myplaintext = "abcd1234";

	    try {
	        CipherText ciphertext = ESAPI.encryptor().encrypt( new PlainText(myplaintext) );
	        String base64=Base64.encodeBytes(ciphertext.asPortableSerializedByteArray());
	        System.out.println("ciphertext base64="+base64);
	        
	        
	                    
	        CipherText cipherText2=CipherText.fromPortableSerializedBytes(Base64.decode(base64));
	        
	        PlainText recoveredPlaintext = ESAPI.encryptor().decrypt(cipherText2) ;
	        System.out.println("recoveredPlaintext="+recoveredPlaintext);
	        assert myplaintext.equals( recoveredPlaintext.toString() );
	    } catch(Exception ex) {
	    	System.out.println("exception="+ex.getMessage());
	    	ex.printStackTrace();
	    }
	}
	
	@Test
	@Ignore
	public void testDecode(){
		
	    try {
	    	String filePath="D:/appublic/applications/com.hsuforum.eas.security.util/EncodeKey";
	    	File serializedFile = new File(filePath);
            FileInputStream fis = new FileInputStream(serializedFile);
            
            int avail = fis.available();
            byte[] bytes = new byte[avail];
            fis.read(bytes, 0, avail);
            fis.close();

            CipherText restoredCipherText =CipherText.fromPortableSerializedBytes(bytes);

	        System.out.println("restoredCipherText="+restoredCipherText);
	        
	        PlainText recoveredPlaintext = ESAPI.encryptor().decrypt(restoredCipherText) ;
	        System.out.println("recoveredPlaintext="+recoveredPlaintext);
	        
	    } catch(Exception ex) {
	    	System.out.println("exception="+ex.getMessage());
	    	ex.printStackTrace();
	    }
	}
	
	@Test
	@Ignore
	public void testEncrypt() throws EncryptionException{
		String plainText = "abcd1234";

		String encryptString=EncryptUtils.encrypt(plainText);
		System.out.println("plainText="+plainText+" encryptString="+encryptString);		
		
	}
	@Test
	@Ignore
	public void testDecrypt() throws EncryptionException{
		String base64Text = "ETMsDgAAAVkLQs75ABRBRVMvQ0JDL1BLQ1M1UGFkZGluZwCAABAAEEZrCjApRLhwepaUbOp9QkIAAAAQZoNcyLu0A5iNjTsoPUULEgAUO0OQJuaofF+rLuqVS/5SwLCMKGo=";

		String plainText=EncryptUtils.decrypt(base64Text);
		System.out.println("plainText="+plainText);		
		
	}
}
