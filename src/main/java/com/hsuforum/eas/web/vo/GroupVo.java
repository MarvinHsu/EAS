package com.hsuforum.eas.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.eas.entity.primary.Group;
import com.hsuforum.eas.entity.primary.User;

/**
 * Group's Value Object Implement
 * 
 */
public class GroupVo extends ValueObjectImpl<Group, java.lang.String> {


	private static final long serialVersionUID = 1026303834702279591L;
	private List<User> userList = null;
	private List<User> userSelectedRowKeys;	 
	private List<FunctionVo> functionVoList;
	/**
	 * Constructor
	 * 
	 */
	public GroupVo() {
		super(new Group());
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public GroupVo(Group entity) {
		super(entity);
	}

	/**
	 * Get user list
	 * 
	 * @return List<User>
	 */
	public List<User> getUserList() {
		return this.userList;
	}

	/**
	 * Set user list
	 * 
	 * @param userList
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * Get checked User collection
	 * 
	 * @return List<User>
	 */
	public List<User> getUserSelectedRowKeys() {
		if (this.userSelectedRowKeys == null) {
			this.userSelectedRowKeys = new ArrayList<User>();
		}
		return this.userSelectedRowKeys;
	}

	/**
	 * Set checked User collection
	 * 
	 * @param UserSelectedRowKeys
	 */
	public void setUserSelectedRowKeys(List<User> userSelectedRowKeys) {
		this.userSelectedRowKeys = userSelectedRowKeys;
	}

	public List<FunctionVo> getFunctionVoList() {
		if (this.functionVoList == null) {
			this.functionVoList = new ArrayList<FunctionVo>();
		}
		return functionVoList;
	}

	public void setFunctionVoList(List<FunctionVo> functionVoList) {
		this.functionVoList = functionVoList;
	}

 

	

	
	


}
