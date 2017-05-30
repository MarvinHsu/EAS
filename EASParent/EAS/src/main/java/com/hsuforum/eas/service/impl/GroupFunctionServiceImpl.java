package com.hsuforum.eas.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.eas.dao.GroupFunctionDao;
import com.hsuforum.eas.entity.GroupFunction;
import com.hsuforum.eas.service.GroupFunctionService;

@Service("groupFunctionService")
public class GroupFunctionServiceImpl
		extends BaseServiceImpl<GroupFunction, com.hsuforum.eas.entity.GroupFunctionPK, GroupFunctionDao>
		implements GroupFunctionService {

	private static final long serialVersionUID = 3469176962996795949L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(GroupFunctionServiceImpl.class);

	@Autowired
	private GroupFunctionDao dao;

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public GroupFunctionDao getDao() {
		return this.dao;
	}

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(final GroupFunctionDao baseDao) {
		this.dao = baseDao;
	}

	
	/**
	 * @see com.hsuforum.eas.service.GroupFunctionService#findAllFetchRelation()
	 */
	public List<GroupFunction> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

}