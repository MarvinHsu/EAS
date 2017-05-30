package com.hsuforum.eas.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.eas.dao.ModuleDao;
import com.hsuforum.eas.entity.Module;
import com.hsuforum.eas.service.ModuleService;

@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl<Module, java.lang.String, ModuleDao> implements ModuleService {

	private static final long serialVersionUID = -6208458659946672638L;

	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(ModuleServiceImpl.class);

	@Autowired
	private ModuleDao dao;

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public ModuleDao getDao() {
		return this.dao;
	}

	
	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(final ModuleDao baseDao) {
		this.dao = baseDao;
	}

	
	/**
	 * @see com.hsuforum.eas.service.ModuleService#findAllFetchRelation()
	 */
	public List<Module> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

}