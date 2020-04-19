package com.hsuforum.eas.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.Function;

public interface FunctionDao extends BaseDao<Function, java.lang.String> {

	
	List<Function> findAllFetchRelation();

}
