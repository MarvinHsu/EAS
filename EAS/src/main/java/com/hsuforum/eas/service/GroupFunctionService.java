package com.hsuforum.eas.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.eas.entity.GroupFunction;

public interface GroupFunctionService extends BaseService<GroupFunction, com.hsuforum.eas.entity.GroupFunctionPK> {

	
	List<GroupFunction> findAllFetchRelation();

}
