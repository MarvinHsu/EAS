package com.hsuforum.eas.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.eas.entity.primary.FunctionItem;

public interface FunctionItemService extends BaseService<FunctionItem, java.lang.String> {

	
	List<FunctionItem> findAllFetchRelation();

}
