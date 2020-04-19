package com.hsuforum.eas.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsuforum.eas.entity.Function;

@RunWith(SpringRunner.class)
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
