package com.hsuforum.eas.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.FunctionItem;

public interface FunctionItemDao extends BaseDao<FunctionItem, java.lang.String> {

	
	List<FunctionItem> findAllFetchRelation();

}
