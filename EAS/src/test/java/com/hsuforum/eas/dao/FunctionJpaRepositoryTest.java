package com.hsuforum.eas.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.eas.entity.Function;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FunctionJpaRepositoryTest {
	
	@Autowired
	private FunctionJpaRepository repository;
	@Test
	public void testFindAll(){
		List<Function> functions=repository.findAll();
		for(Function function :functions) {
			System.out.println("Function.id="+function.getId());
		}
	}
}
