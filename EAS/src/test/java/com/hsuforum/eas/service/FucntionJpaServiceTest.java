package com.hsuforum.eas.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.eas.entity.primary.Function;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class FucntionJpaServiceTest {
	@Autowired
	private FunctionJpaService service;
	@Test
	public void testFindAll(){
		List<Function> functions=service.findAll();
		for(Function function :functions) {
			log.info("Function.id="+function.getId());
		}
	}
}
