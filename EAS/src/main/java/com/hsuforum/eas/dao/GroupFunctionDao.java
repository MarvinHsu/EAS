package com.hsuforum.eas.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.GroupFunction;

public interface GroupFunctionDao extends BaseDao<GroupFunction, String> {

	
	List<GroupFunction> findAllFetchRelation();

}
