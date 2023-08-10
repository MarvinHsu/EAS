package com.hsuforum.eas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.eas.dao.primary.GroupJpaRepository;
import com.hsuforum.eas.entity.primary.Group;
import com.hsuforum.eas.service.GroupJpaService;

import lombok.extern.slf4j.Slf4j;

@Service("groupJpaService")
@Slf4j
public class GroupJpaServiceImpl extends BaseJpaServiceImpl<Group, String, GroupJpaRepository>
		implements GroupJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private GroupJpaRepository repo;

	@Override
	public GroupJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(GroupJpaRepository repo) {
		this.repo = repo;

	}

}
