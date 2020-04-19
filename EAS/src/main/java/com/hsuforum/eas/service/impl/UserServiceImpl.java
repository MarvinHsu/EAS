package com.hsuforum.eas.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseServiceImpl;
import com.hsuforum.eas.dao.UserDao;
import com.hsuforum.eas.entity.User;
import com.hsuforum.eas.service.UserService;

/**
 * User Service Implement
 * 
 * @author Marvin
 *
 */

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, String, UserDao> implements UserService {

	private static final long serialVersionUID = -276502196280318978L;
	@SuppressWarnings("unused")
	private final static Log LOG = LogFactory.getLog(UserServiceImpl.class);
	@Autowired
	private UserDao dao;

	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#getDao()
	 */
	public UserDao getDao() {
		return dao;
	}

	/**
	 * @see com.hsuforum.common.service.impl.BaseServiceImpl#setDao(com.hsuforum.common.dao.BaseDao)
	 */
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	
	/**
	 * @see com.hsuforum.eas.service.UserService#findAllFetchRelation()
	 */
	public List<User> findAllFetchRelation() {

		return this.getDao().findAllFetchRelation();
	}

	/**
	 * @see com.hsuforum.eas.service.UserService#doLogin(java.lang.String)
	 */
	@Override
	public User doLogin(String account) {

		return this.getDao().doLogin(account);
	}

	/**
	 * @see com.hsuforum.eas.service.UserService#findByCriteriaMapFetchRelation(java.util.Map)
	 */
	@Override
	public List<User> findByCriteriaMapFetchRelation(Map<String, ? extends Object> criteriaMap) {

		return this.getDao().findByCriteriaMapFetchRelation(criteriaMap);
	}

	/**
	 * @see com.hsuforum.eas.service.UserService#findByAccountOtherEmail(java.lang.String,
	 *      java.lang.String)
	 */
	public User findByAccountOtherEmail(final String account, final String email) {

		return this.getDao().findByAccountOtherEmail(account, email);

	}

	/**
	 * @see com.hsuforum.eas.service.UserService#findByAccount(java.lang.String)
	 */
	public User findByAccount(final String account) {

		User user = this.getDao().findByAccount(account);
		return user;

	}

	/**
	 * @see com.hsuforum.eas.service.UserService#findForSentActivate(java.lang.String)
	 */
	public User findForSentActivate(final String email) {

		return this.getDao().findForSentActivate(email);

	}
}
