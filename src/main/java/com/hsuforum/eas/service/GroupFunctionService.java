package com.hsuforum.eas.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.eas.entity.primary.GroupFunction;

public interface GroupFunctionService extends BaseService<GroupFunction, String> {

	
	List<GroupFunction> findAllFetchRelation();

}
