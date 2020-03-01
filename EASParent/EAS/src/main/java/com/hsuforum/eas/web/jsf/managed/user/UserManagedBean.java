package com.hsuforum.eas.web.jsf.managed.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.owasp.esapi.errors.EncryptionException;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean;
import com.hsuforum.common.web.jsf.utils.JSFMessageUtils;
import com.hsuforum.common.web.util.EncryptUtils;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.eas.common.ErrorCode;
import com.hsuforum.eas.entity.Group;
import com.hsuforum.eas.entity.User;
import com.hsuforum.eas.service.GroupService;
import com.hsuforum.eas.service.UserJpaService;
import com.hsuforum.eas.service.UserService;
import com.hsuforum.eas.web.vo.UserVo;
import com.hsuforum.eas.web.vowrapper.UserVoWrapper;

@ManagedBean
@SessionScoped
public class UserManagedBean extends TemplatePrimeJpaDataTableManagedBean<User, java.lang.String, UserService, UserJpaService> {

	private static final long serialVersionUID = 4704000081629878950L;

	private String mode;

	@ManagedProperty(value = "#{userService}")
	private UserService service;

	@ManagedProperty(value = "#{userJpaService}")
	private UserJpaService jpaService;

	@ManagedProperty(value = "#{groupService}")
	private GroupService groupService;


	public UserManagedBean() {

		super();

	}

	@PostConstruct
	public void init() {
	
		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new UserVoWrapper());

	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initCreatingData()
	 */
	@Override
	protected void initCreatingData() {
		User object = new User();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		
		this.getUpdatingData().setGroupList(this.getGroupList());
		this.setMode("Create");
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<User, java.lang.String> updatingData) {

		try {
			String decryptPassword=EncryptUtils.decrypt(this.getUpdatingData().getEntity().getPassword());
			this.getUpdatingData().getEntity().setPassword(decryptPassword);
		} catch (EncryptionException e) {
			
			e.printStackTrace();
			JSFMessageUtils.showErrorMessage(ErrorCode.P10004.toString());
		}
		
		this.getUpdatingData().setGroupList(this.getGroupList());
		Set<Group> groupList = this.getUpdatingData().getEntity().getGroups();
		if (groupList != null && groupList.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getGroupList().size(); i++) {

				Group group = (Group) this.getUpdatingData().getGroupList().get(i);
				
				if (groupList.contains(group)) {
					
					this.getUpdatingData().getGroupSelectedRowKeys().add(group);
				}
			}
		}

		this.setMode("Update");
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {
	
		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		findCriteriaMap.put("email", null);
		findCriteriaMap.put("name", null);
		findCriteriaMap.put("account", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("email", "eq");
		findOperMap.put("name", "eq");
		findOperMap.put("account", "eq");
		this.setFindOperMap(findOperMap);

		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("email", "DESC");
		findSortMap.put("name", "DESC");
		findSortMap.put("account", "DESC");
		this.setFindSortMap(findSortMap);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public UserVo getUpdatingData() {
		return (UserVo) super.getUpdatingData();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<User, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getService()
	 */
	public UserService getService() {

		return this.service;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(UserService service) {
		this.service = service;
	}

	public UserJpaService getJpaService() {
		return jpaService;
	}

	public void setJpaService(UserJpaService jpaService) {
		this.jpaService = jpaService;
	}

	public List<Group> getGroupList() {

		List<Group> manyBoList = new ArrayList<Group>();

		for (Group manyBo : getGroupService().findAllFetchRelation()) {
			manyBoList.add(manyBo);

		}
		return manyBoList;
	}

	public GroupService getGroupService() {
		return this.groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}


	private void setupGroup() {

		if (this.getUpdatingData().getGroupList() != null) {
			List<Group> groupSelectedRowKeys = this.getUpdatingData().getGroupSelectedRowKeys();
			this.getUpdatingData().getEntity().clearGroups();
			
			Iterator<Group> groupSelectedRowKeyIterator = groupSelectedRowKeys.iterator();

			while (groupSelectedRowKeyIterator.hasNext()) {
				Group rowKey = groupSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addGroup(rowKey);
			}
		}
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		try {
			String encryptPassword=EncryptUtils.encrypt(this.getUpdatingData().getEntity().getPassword());
			this.getUpdatingData().getEntity().setPassword(encryptPassword);
		} catch (EncryptionException e) {
			
			e.printStackTrace();
			JSFMessageUtils.showErrorMessage(ErrorCode.P10005.toString());
		}
		this.setupGroup();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<User> findAllData() {
		return this.getService().findAllFetchRelation();
	}
	


}
