package com.hsuforum.eas.dao.primary;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.primary.GroupFunction;

public interface GroupFunctionDao extends BaseDao<GroupFunction, String> {

	
	List<GroupFunction> findAllFetchRelation();

}
