package com.hsuforum.eas.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.eas.entity.Function;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/DbContext.xml", "/DaoContext.xml", "/ServiceContext.xml"})
@Transactional("transactionManager")
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
