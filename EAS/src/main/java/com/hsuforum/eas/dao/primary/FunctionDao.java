package com.hsuforum.eas.dao.primary;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.primary.Function;

public interface FunctionDao extends BaseDao<Function, java.lang.String> {

	
	List<Function> findAllFetchRelation();

}
