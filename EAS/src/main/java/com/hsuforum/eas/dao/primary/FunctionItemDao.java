package com.hsuforum.eas.dao.primary;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.primary.FunctionItem;

public interface FunctionItemDao extends BaseDao<FunctionItem, java.lang.String> {

	
	List<FunctionItem> findAllFetchRelation();

}
