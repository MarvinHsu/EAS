package com.hsuforum.eas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.eas.dao.primary.GroupFunctionJpaRepository;
import com.hsuforum.eas.entity.primary.GroupFunction;
import com.hsuforum.eas.service.GroupFunctionJpaService;

import lombok.extern.slf4j.Slf4j;

@Service("groupFunctionJpaService")
@Slf4j
public class GroupFunctionJpaServiceImpl
		extends BaseJpaServiceImpl<GroupFunction, String, GroupFunctionJpaRepository>
		implements GroupFunctionJpaService {
	private static final long serialVersionUID = 8166682726719828895L;

	@Autowired
	private GroupFunctionJpaRepository repo;

	@Override
	public GroupFunctionJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(GroupFunctionJpaRepository repo) {
		this.repo = repo;

	}

}
