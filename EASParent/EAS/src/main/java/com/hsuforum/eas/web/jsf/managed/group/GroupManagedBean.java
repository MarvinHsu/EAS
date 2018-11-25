package com.hsuforum.eas.web.jsf.managed.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.eas.entity.Function;
import com.hsuforum.eas.entity.FunctionItem;
import com.hsuforum.eas.entity.Group;
import com.hsuforum.eas.entity.GroupFunction;
import com.hsuforum.eas.entity.GroupFunctionPK;
import com.hsuforum.eas.entity.User;
import com.hsuforum.eas.service.FunctionService;
import com.hsuforum.eas.service.GroupService;
import com.hsuforum.eas.service.UserService;
import com.hsuforum.eas.web.vo.FunctionVo;
import com.hsuforum.eas.web.vo.GroupVo;
import com.hsuforum.eas.web.vowrapper.FunctionVoWrapper;
import com.hsuforum.eas.web.vowrapper.GroupVoWrapper;

@ManagedBean
@SessionScoped
public class GroupManagedBean extends TemplatePrimeDataTableManagedBean<Group, java.lang.String, GroupService> {

	private static final long serialVersionUID = 1096387523639795946L;

	
	private String mode;

	@ManagedProperty(value = "#{groupService}")
	private GroupService service;

	@ManagedProperty(value = "#{functionService}")
	private FunctionService functionService;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;


	public GroupManagedBean() {

		super();

	}


	@PostConstruct
	public void init() {

		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new GroupVoWrapper());

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
		Group object = new Group();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		this.getUpdatingData().setUserList(this.getUserList());
		this.getUpdatingData().getEntity().setGroupFunctions(new HashSet<GroupFunction>());
		this.getUpdatingData().setFunctionVoList(new ArrayList<FunctionVo>());
		
		for (Function function : this.getFunctionList()) {
			FunctionVoWrapper functionVoWrapper = new FunctionVoWrapper();
			FunctionVo functionVo = functionVoWrapper.wrap(function);
			this.getUpdatingData().getFunctionVoList().add(functionVo);
		}
		
		this.setMode("Create");
		
		
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<Group, java.lang.String> updatingData) {

		
		this.getUpdatingData().setUserList(this.getUserList());
		Set<User> userList = this.getUpdatingData().getEntity().getUsers();
		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getUserList().size(); i++) {

				User user = (User) this.getUpdatingData().getUserList().get(i);
				
				if (userList.contains(user)) {
					
					this.getUpdatingData().getUserSelectedRowKeys().add(user);
				}
			}
		}
		for (Function function : this.getFunctionList()) {
			FunctionVoWrapper functionVoWrapper = new FunctionVoWrapper();
			FunctionVo functionVo = functionVoWrapper.wrap(function);
			for (GroupFunction groupFunction : this.getUpdatingData().getEntity().getGroupFunctions()) {

				if (groupFunction.getFunction().equals(function)) {

					for (FunctionItem functionItem : function.getFunctionItems()) {
						FunctionItem functionItem2 = groupFunction.getFunctionItem();

						if (functionItem.getId().equals(functionItem2.getId())) {
							functionVo.getFunctionItemSelectedRowKeys().add(functionItem2);
						}

					}
					

				}
			}
			functionVo.setFunctionItemChecked(new String[functionVo.getFunctionItemSelectedRowKeys().size()]);
			for(int i=0;i<functionVo.getFunctionItemChecked().length;i++){
				functionVo.getFunctionItemChecked()[i]=functionVo.getFunctionItemSelectedRowKeys().get(i).getId();
			}
			
			this.getUpdatingData().getFunctionVoList().add(functionVo);
		}

		this.setMode("Update");

	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {

		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		findCriteriaMap.put("name", null);
		findCriteriaMap.put("code", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		this.setFindOperMap(findOperMap);


		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("name", "DESC");
		findSortMap.put("code", "DESC");
		this.setFindSortMap(findSortMap);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public GroupVo getUpdatingData() {
		return (GroupVo) super.getUpdatingData();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<Group, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getService()
	 */
	public GroupService getService() {

		return this.service;
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(GroupService service) {
		this.service = service;
	}


	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public List<Function> getFunctionList() {

		List<Function> functionList = new ArrayList<Function>();
		for (Function function : getFunctionService().findAllFetchRelation()) {

			functionList.add(function);

		}

		return functionList;
	}

	public List<User> getUserList() {

		List<User> manyBoList = new ArrayList<User>();

		for (User manyBo : getUserService().findAllFetchRelation()) {
			manyBoList.add(manyBo);

		}
		return manyBoList;
	}


	public UserService getUserService() {
		return this.userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	private void setupUser() {

		if (this.getUpdatingData().getUserList() != null) {
			List<User> userSelectedRowKeys = this.getUpdatingData().getUserSelectedRowKeys();
			this.getUpdatingData().getEntity().clearUsers();
		
			Iterator<User> userSelectedRowKeyIterator = userSelectedRowKeys.iterator();

			while (userSelectedRowKeyIterator.hasNext()) {
				User rowKey = userSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addUser(rowKey);
			}
		}
	}


	private void setupGroupFunction() {
		this.getUpdatingData().getEntity().clearGroupFunctions();
		
		for(FunctionVo functionVo:this.getUpdatingData().getFunctionVoList()){
			for(String functionItemId : functionVo.getFunctionItemChecked()){
				GroupFunctionPK groupFunctionPK = new GroupFunctionPK();
				groupFunctionPK.setFunctionId(functionVo.getEntity().getId());
				groupFunctionPK.setFunctionItemId(functionItemId);
				groupFunctionPK.setGroupId(this.getUpdatingData().getEntity().getId());
				
				GroupFunction groupFunction = new GroupFunction();
				groupFunction.setId(groupFunctionPK);
				groupFunction.setGroup(this.getUpdatingData().getEntity());
				groupFunction.setFunction(functionVo.getEntity());
				for(FunctionItem functionItem : functionVo.getEntity().getFunctionItems()){
					if(functionItemId.equals(functionItem.getId())){
						groupFunction.setFunctionItem(functionItem);
					}
				}
				
				this.getUpdatingData().getEntity().addGroupFunction(groupFunction);
			}
			
		}
		
		if (this.getUpdatingData().getUserList() != null) {
			List<User> userSelectedRowKeys = this.getUpdatingData().getUserSelectedRowKeys();
			this.getUpdatingData().getEntity().clearUsers();
	
			Iterator<User> userSelectedRowKeyIterator = userSelectedRowKeys.iterator();

			while (userSelectedRowKeyIterator.hasNext()) {
				User rowKey = userSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addUser(rowKey);
			}
		}
	}
	
	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {

		this.setupUser();
		this.setupGroupFunction();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Group> findAllData() {
		return this.getService().findAllFetchRelation();
	}



}
