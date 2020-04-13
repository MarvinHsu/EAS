package com.hsuforum.eas.web.vo;

import java.util.ArrayList;
import java.util.List;

import com.hsuforum.common.web.vo.impl.ValueObjectImpl;
import com.hsuforum.eas.entity.Function;
import com.hsuforum.eas.entity.FunctionItem;

/**
 * Function's Value Object Implement
 * 
 */
public class FunctionVo extends ValueObjectImpl<Function, java.lang.String> {

	private static final long serialVersionUID = -1542258114277218238L;
	private String selectModuleId;
	private List<FunctionItem> functionItemList = null;
	private List<FunctionItem> functionItemSelectedRowKeys;
	private String[] functionItemChecked;
	
	/**
	 * Constructor
	 * 
	 */
	public FunctionVo() {
		super(new Function());
	}

	/**
	 * Constructor
	 * 
	 * @param entity
	 */
	public FunctionVo(Function entity) {
		super(entity);
	}


	/**
	 * Get selected Module's id
	 * @return FunctionId
	 */
	public String getSelectModuleId() {
		if (this.selectModuleId == null && this.getEntity().getModule() != null) {
			this.selectModuleId = this.getEntity().getModule().getId().toString();
		}
		return this.selectModuleId;

	}

	/**
	 * Set selected Module's id
	 * 
	 * @param selectFunctionId
	 */
	public void setSelectModuleId(String selectModuleId) {

		this.selectModuleId = selectModuleId;
	}

	public List<FunctionItem> getFunctionItemList() {
		return functionItemList;
	}

	public void setFunctionItemList(List<FunctionItem> functionItemList) {
		this.functionItemList = functionItemList;
	}

	public List<FunctionItem> getFunctionItemSelectedRowKeys() {
		if (this.functionItemSelectedRowKeys == null) {
			this.functionItemSelectedRowKeys = new ArrayList<FunctionItem>();
		}
		return functionItemSelectedRowKeys;
	}

	public void setFunctionItemSelectedRowKeys(List<FunctionItem> functionItemSelectedRowKeys) {
		this.functionItemSelectedRowKeys = functionItemSelectedRowKeys;
	}

	public String[] getFunctionItemChecked() {
		return functionItemChecked;
	}

	public void setFunctionItemChecked(String[] functionItemChecked) {
		this.functionItemChecked = functionItemChecked;
	}

}
