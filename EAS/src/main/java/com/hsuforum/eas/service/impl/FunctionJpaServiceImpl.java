package com.hsuforum.eas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.eas.dao.primary.FunctionJpaRepository;
import com.hsuforum.eas.entity.primary.Function;
import com.hsuforum.eas.service.FunctionJpaService;

import lombok.extern.slf4j.Slf4j;

@Service("functionJpaService")
@Slf4j
public class FunctionJpaServiceImpl extends BaseJpaServiceImpl<Function, String, FunctionJpaRepository>
		implements FunctionJpaService {
	private static final long serialVersionUID = 8166682726719828895L;
	
	@Autowired
	private FunctionJpaRepository repo;
	
	@Override
	public FunctionJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(FunctionJpaRepository repo) {
		this.repo=repo;
		
	}

}
