package com.hsuforum.eas.dao;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.Module;

public interface ModuleDao extends BaseDao<Module, java.lang.String> {

	
	List<Module> findAllFetchRelation();

}
