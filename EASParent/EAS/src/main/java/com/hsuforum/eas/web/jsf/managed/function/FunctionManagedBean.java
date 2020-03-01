package com.hsuforum.eas.web.jsf.managed.function;

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
import javax.faces.model.SelectItem;

import com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeJpaDataTableManagedBean;
import com.hsuforum.common.web.vo.ValueObject;
import com.hsuforum.eas.entity.Function;
import com.hsuforum.eas.entity.FunctionItem;
import com.hsuforum.eas.entity.Module;
import com.hsuforum.eas.service.FunctionItemService;
import com.hsuforum.eas.service.FunctionJpaService;
import com.hsuforum.eas.service.FunctionService;
import com.hsuforum.eas.service.ModuleService;
import com.hsuforum.eas.web.util.SelectHelper;
import com.hsuforum.eas.web.vo.FunctionVo;
import com.hsuforum.eas.web.vowrapper.FunctionVoWrapper;

@ManagedBean
@SessionScoped
public class FunctionManagedBean
		extends TemplatePrimeJpaDataTableManagedBean<Function, String, FunctionService, FunctionJpaService> {

	private static final long serialVersionUID = -78467379177882514L;

	private String mode;

	@ManagedProperty(value = "#{functionService}")
	private FunctionService service;
	@ManagedProperty(value = "#{functionJpaService}")
	private FunctionJpaService jpaService;
	@ManagedProperty(value = "#{functionItemService}")
	private FunctionItemService functionItemService;
	@ManagedProperty(value = "#{moduleService}")
	private ModuleService moduleService;
	private List<SelectItem> moduleList;

	public FunctionManagedBean() {

		super();

	}


	@PostConstruct
	public void init() {

		this.setInitShowListData(true);
		this.initFindCriteriaMap();
		this.setVoWrapper(new FunctionVoWrapper());

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
		Function object = new Function();
		object.setId(UUID.randomUUID().toString());
		this.setUpdatingData(this.wrap(object));
		this.getUpdatingData().setFunctionItemList(this.getFunctionItemList());

		this.setMode("Create");

	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#initUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	protected void initUpdatingData(ValueObject<Function, java.lang.String> updatingData) {

		
		if (this.getUpdatingData().getEntity().getModule() != null) {
			this.getUpdatingData().setSelectModuleId(this.getUpdatingData().getEntity().getModule().getId().toString());

		}


		
		this.getUpdatingData().setFunctionItemList(this.getFunctionItemList());
		Set<FunctionItem> functionItemList = this.getUpdatingData().getEntity().getFunctionItems();
		if (functionItemList != null && functionItemList.size() > 0) {
			for (int i = 0; i < this.getUpdatingData().getFunctionItemList().size(); i++) {

				FunctionItem functionItem = (FunctionItem) this.getUpdatingData().getFunctionItemList().get(i);

				if (functionItemList.contains(functionItem)) {
					
					this.getUpdatingData().getFunctionItemSelectedRowKeys().add(functionItem);
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
		findCriteriaMap.put("name", null);
		findCriteriaMap.put("code", null);
		findCriteriaMap.put("outcome", null);
		this.setFindCriteriaMap(findCriteriaMap);

		Map<String, String> findOperMap = new HashMap<String, String>();
		findOperMap.put("name", "eq");
		findOperMap.put("code", "eq");
		findOperMap.put("outcome", "eq");
		this.setFindOperMap(findOperMap);

		Map<String, String> findSortMap = new HashMap<String, String>();
		findSortMap.put("name", "DESC");
		findSortMap.put("code", "DESC");
		findSortMap.put("outcome", "DESC");
		this.setFindSortMap(findSortMap);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getUpdatingData()
	 */
	@Override
	public FunctionVo getUpdatingData() {
		return (FunctionVo) super.getUpdatingData();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setUpdatingData(com.hsuforum.common.web.vo.ValueObject)
	 */
	@Override
	public void setUpdatingData(ValueObject<Function, java.lang.String> vo) {
		super.setUpdatingData(vo);
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#getService()
	 */
	public FunctionService getService() {

		return this.service;
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setService(com.hsuforum.common.service.BaseService)
	 */
	public void setService(FunctionService service) {
		this.service = service;
	}

	
	
	public FunctionJpaService getJpaService() {
		return jpaService;
	}


	public void setJpaService(FunctionJpaService jpaService) {
		this.jpaService = jpaService;
	}


	public List<SelectItem> getModuleList() {

		if (this.moduleList == null) {
			this.moduleList = new ArrayList<SelectItem>();
			this.moduleList.add(SelectHelper.EMPTY_SELECTITEM);
			for (Module module : getModuleService().findAll()) {
				SelectItem item = new SelectItem();
				item.setValue(module.getId().toString());
				item.setLabel(module.getName());
				this.moduleList.add(item);

			}
		}
		return moduleList;
	}

	public ModuleService getModuleService() {
		return this.moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public FunctionItemService getFunctionItemService() {
		return functionItemService;
	}

	public void setFunctionItemService(FunctionItemService functionItemService) {
		this.functionItemService = functionItemService;
	}

	private void setupModule() {
	
		if ((this.getUpdatingData().getSelectModuleId() != null)
				&& (this.getUpdatingData().getSelectModuleId().compareTo("") != 0)) {
			this.getUpdatingData().getEntity()
					.setModule(getModuleService().findByPK(this.getUpdatingData().getSelectModuleId()));
		} else {
			this.getUpdatingData().getEntity().setModule(null);
		}
	}






	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.BaseManagedBeanImpl#setupUpdatingData()
	 */
	@Override
	protected void setupUpdatingData() {
		this.setupModule();
		this.setupFunctionItem();
	}


	/**
	 * @see com.hsuforum.common.web.jsf.managedbean.impl.TemplatePrimeDataTableManagedBean#findAllData()
	 */
	@Override
	protected List<Function> findAllData() {
		return this.getService().findAllFetchRelation();
	}

	public List<FunctionItem> getFunctionItemList() {

		List<FunctionItem> manyBoList = new ArrayList<FunctionItem>();

		for (FunctionItem manyBo : getFunctionItemService().findAllFetchRelation()) {
			manyBoList.add(manyBo);

		}
		return manyBoList;
	}


	private void setupFunctionItem() {

		if (this.getUpdatingData().getFunctionItemList() != null) {
			List<FunctionItem> functionItemSelectedRowKeys = this.getUpdatingData().getFunctionItemSelectedRowKeys();
			this.getUpdatingData().getEntity().clearFunctionItems();
			
			Iterator<FunctionItem> functionItemSelectedRowKeyIterator = functionItemSelectedRowKeys.iterator();

			while (functionItemSelectedRowKeyIterator.hasNext()) {
				FunctionItem rowKey = functionItemSelectedRowKeyIterator.next();

				this.getUpdatingData().getEntity().addFunctionItem(rowKey);
			}
		}
	}
}
