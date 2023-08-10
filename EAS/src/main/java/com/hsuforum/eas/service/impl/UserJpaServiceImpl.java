package com.hsuforum.eas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.eas.dao.primary.UserJpaRepository;
import com.hsuforum.eas.entity.primary.User;
import com.hsuforum.eas.service.UserJpaService;

import lombok.extern.slf4j.Slf4j;

@Service("userJpaService")
@Slf4j
public class UserJpaServiceImpl extends BaseJpaServiceImpl<User, String, UserJpaRepository> implements UserJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private UserJpaRepository repo;

	@Override
	public UserJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(UserJpaRepository repo) {
		this.repo = repo;

	}

}
