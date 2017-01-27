package com.hsuforum.eas.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.eas.entity.Function;
import com.hsuforum.eas.entity.Module;

/**
 * Module's Value Object Implement
 * 
 */
public class ModuleVo extends ValueObjectImpl<Module, java.lang.String> {


	private static final long serialVersionUID = 2630701064462134665L;
	private List<Function> functionList = null;
	private List<Function> functionSelectedRowKeys;
	
	/**
	 * Constructor
	 * 
	 */
	public ModuleVo() {
		super(new Module());
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public ModuleVo(Module entity) {
		super(entity);
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public List<Function> getFunctionSelectedRowKeys() {
		if (this.functionSelectedRowKeys == null) {
			this.functionSelectedRowKeys = new ArrayList<Function>();
		}
		return functionSelectedRowKeys;
	}

	public void setFunctionSelectedRowKeys(List<Function> functionSelectedRowKeys) {
		this.functionSelectedRowKeys = functionSelectedRowKeys;
	}

}
