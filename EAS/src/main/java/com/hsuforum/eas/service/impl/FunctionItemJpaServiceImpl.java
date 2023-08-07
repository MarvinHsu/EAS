package com.hsuforum.eas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.eas.dao.primary.FunctionItemJpaRepository;
import com.hsuforum.eas.entity.primary.FunctionItem;
import com.hsuforum.eas.service.FunctionItemJpaService;

import lombok.extern.slf4j.Slf4j;

@Service("functionItemJpaService")
@Slf4j
public class FunctionItemJpaServiceImpl extends BaseJpaServiceImpl<FunctionItem, String, FunctionItemJpaRepository>
		implements FunctionItemJpaService {
	

	private static final long serialVersionUID = 7390409841076847717L;
	@Autowired
	private FunctionItemJpaRepository repo;

	@Override
	public FunctionItemJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(FunctionItemJpaRepository repo) {
		this.repo = repo;

	}

}
