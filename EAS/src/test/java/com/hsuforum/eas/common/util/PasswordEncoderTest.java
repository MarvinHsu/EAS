package com.hsuforum.eas.common.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.eas.DefaultSetting;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PasswordEncoderTest {
	@Autowired
	DefaultSetting defaultSetting;
	@Test
	//@Ignore
	public void testPasswordEncoder(){
		

	    try {
	    	PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder(defaultSetting.getSecret(), 0, 15, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);       
	    	String passwordEncoderString=passwordEncoder.encode("abcd1234");
	    
	    	System.out.println("passwordEncoderString="+passwordEncoderString);
	    } catch(Exception ex) {
	    	System.out.println("exception="+ex.getMessage());
	    	ex.printStackTrace();
	    }
	}
}
