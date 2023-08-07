package com.hsuforum.eas.dao.primary;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.primary.Module;

public interface ModuleDao extends BaseDao<Module, String> {

	
	List<Module> findAllFetchRelation();

}
