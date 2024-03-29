package com.hsuforum.eas.dao.primary;

import java.util.List;

import com.hsuforum.common.dao.BaseDao;
import com.hsuforum.eas.entity.primary.Group;

/**
 *
 * Group Data Access Object Interface
 *
 */
public interface GroupDao extends BaseDao<Group, String> {

	
	List<Group> findAllFetchRelation();

	
	Group findByPKFetchFunctions(String pk);

	
	Group findByPKFetchUsers(String pk);
}
