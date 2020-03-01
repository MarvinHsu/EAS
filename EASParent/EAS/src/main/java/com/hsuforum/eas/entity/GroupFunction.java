package com.hsuforum.eas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

/**
 * The persistent class for the tbcl_groups_functions database table.
 * 
 */
@Entity
@Table(name = "TB_GROUPS_FUNCTIONS")
@NamedQuery(name = "GroupFunction.findAll", query = "SELECT g FROM GroupFunction g")
public class GroupFunction extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private Function function;
	private FunctionItem functionItem;
	private Group group;

	public GroupFunction() {
	}

	@Id
	@Column(name = "ID", nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// bi-directional many-to-one association to Function
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ID", nullable = false)
	public Function getFunction() {
		return this.function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}

	// bi-directional many-to-one association to FunctionItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_FUNCTIONS_ITEMS_ID", nullable = false)
	public FunctionItem getFunctionItem() {
		return this.functionItem;
	}

	public void setFunctionItem(FunctionItem functionItem) {
		this.functionItem = functionItem;
	}

	// bi-directional many-to-one association to Group
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TB_GROUPS_ID", nullable = false)
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((functionItem == null) ? 0 : functionItem.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupFunction other = (GroupFunction) obj;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (functionItem == null) {
			if (other.functionItem != null)
				return false;
		} else if (!functionItem.equals(other.functionItem))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		return true;
	}



}