package com.hsuforum.eas.service;

import java.util.List;

import com.hsuforum.common.service.BaseService;
import com.hsuforum.eas.entity.Module;

public interface ModuleService extends BaseService<Module, java.lang.String> {

	
	List<Module> findAllFetchRelation();

}
