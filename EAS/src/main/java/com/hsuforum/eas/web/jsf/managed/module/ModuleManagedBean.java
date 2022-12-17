package com.hsuforum.eas.web.jsf.managed.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.eas.entity.Function;
import com.hsuforum.eas.entity.Module;
import com.hsuforum.eas.service.FunctionService;
import com.hsuforum.eas.service.ModuleJpaService;
import com.hsuforum.eas.service.ModuleService;
import com.hsuforum.eas.web.vo.ModuleVo;
import com.hsuforum.eas.web.vowrapper.ModuleVoWrapper;

@Component
@SessionScope
public class ModuleManagedBean extends TemplatePrimeDataTableManagedBean<Module, String, ModuleService, ModuleJpaService> {

	private static final long serialVersionUID = -6435432912309239472L;

	private String mode;

	@Autowired
	private ModuleService service;
	@Autowired
	private ModuleJpaService jpaService;
	@Autowired
	private FunctionService functionService;


	public ModuleManagedBean() {

		super();

	}


	@PostConstruct
	public void init() {
	
		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new ModuleVoWrapper());

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
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initCreatingData()
	 */
	@Override
	protected void initCreatingData() {
		Module object = new Module();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		
		this.getUpdatingData().setFunctionList(this.getFunctionList());
		this.setMode("Create");

	}

	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<Module, java.lang.String> updatingData) {
		
		this.getUpdatingData().setFunctionList(this.getFunctionList());
		Set<Function> functionList = this.getUpdatingData().getEntity().getFunctions();
		if (functionList != null && functionList.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getFunctionList().size(); i++) {

				Function function = (Function) this.getUpdatingData().getFunctionList().get(i);
				
				if (functionList.contains(function)) {
					
					this.getUpdatingData().getFunctionSelectedRowKeys().add(function);
				}
			}
		}
		this.setMode("Update");

	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#initFindCriteriaMap()
	 */
	@Override
	protected void initFindCriteriaMap() {

		Map<String, Object> findCriteriaMap = new HashMap<String, Object>();
		findCriteriaMap.put("code", null);
		findCriteriaMap.put("name", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("code", "eq");
		findOperMap.put("name", "eq");

		this.setFindOperMap(findOperMap);


		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("code", "DESC");
		findSortMap.put("name", "DESC");
		this.setFindSortMap(findSortMap);
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public ModuleVo getUpdatingData() {
		return (ModuleVo) super.getUpdatingData();
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<Module, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#getService()
	 */
	public ModuleService getService() {

		return this.service;
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(ModuleService service) {
		this.service = service;
	}

	
	public ModuleJpaService getJpaService() {
		return jpaService;
	}


	public void setJpaService(ModuleJpaService jpaService) {
		this.jpaService = jpaService;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseJpaManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupFunction();
	}

	public FunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	
	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Module> findAllData() {
		return this.getService().findAllFetchRelation();
	}


	public List<Function> getFunctionList() {

		List<Function> functionList = new ArrayList<Function>();

		for (Function function : getFunctionService().findAllFetchRelation()) {
			functionList.add(function);
		}
		return functionList;
	}
	

	private void setupFunction() {

		if (this.getUpdatingData().getFunctionList() != null) {
			List<Function> functionSelectedRowKeys = this.getUpdatingData().getFunctionSelectedRowKeys();
			this.getUpdatingData().getEntity().clearFunctions();
		
			Iterator<Function> functionSelectedRowKeyIterator = functionSelectedRowKeys.iterator();

			while (functionSelectedRowKeyIterator.hasNext()) {
				Function rowKey = functionSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addFunction(rowKey);
			}
		}
	}
}
