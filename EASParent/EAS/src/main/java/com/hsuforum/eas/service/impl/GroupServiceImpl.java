package com.hsuforum.eas.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.eas.dao.GroupDao;
import com.hsuforum.eas.entity.Group;
import com.hsuforum.eas.service.GroupService;

/**
 * Group Service Implement
 * 
 * @author Administrator
 *
 */
@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl<Group, String, GroupDao> implements GroupService {

	private static final long serialVersionUID = -8882435809668503894L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(GroupServiceImpl.class);

	@Autowired
	private GroupDao dao;

	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public GroupDao getDao() {
		return dao;
	}

	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(GroupDao dao) {
		this.dao = dao;
	}

	
	/**
	 * @see com.hsuforum.eas.service.GroupService#findAllFetchRelation()
	 */
	public List<Group> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

	/**
	 * @see com.hsuforum.eas.service.GroupService#findByPKFetchFuntions(java.lang.Long)
	 */
	public Group findByPKFetchFunctions(String pk) {

		return this.getDao().findByPKFetchFunctions(pk);

	}

	/**
	 * @see com.hsuforum.eas.service.GroupService#findByPKFetchUsers(java.lang.String)
	 */
	public Group findByPKFetchUsers(String pk) {
		return this.getDao().findByPKFetchUsers(pk);
	}
}
