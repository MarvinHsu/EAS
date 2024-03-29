package com.hsuforum.eas.web.jsf.managed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.eas.DefaultSetting;
import com.hsuforum.eas.entity.primary.Function;
import com.hsuforum.eas.entity.primary.Group;
import com.hsuforum.eas.entity.primary.GroupFunction;
import com.hsuforum.eas.entity.primary.Module;
import com.hsuforum.eas.entity.primary.User;
import com.hsuforum.eas.security.util.AAUtils;
import com.hsuforum.eas.service.ModuleService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * Navigation menu managed bean
 */
@Component
@SessionScope
@Slf4j
public class MenuManagedBean implements Serializable {

	private static final long serialVersionUID = 7319288785728714429L;

	@Autowired
	private DefaultSetting defaultSetting;

	@Autowired
	private ModuleService moduleService;
	private User user;
	private List<Module> modules;

	private Integer activeTab;

	public MenuManagedBean() {
		super();

	}

	@PostConstruct
	public void init() {

		Object obj = AAUtils.getLoggedInUser();
		if (obj instanceof User) {
			this.user = (User) obj;

			this.modules = this.getModuleService().findAllFetchRelation();
			if (this.modules.size() > 0) {
				this.activeTab = 0;
			}
			if (this.user != null && user.getAuthorities() != null && this.modules != null) {
				for (int i = 0; i < this.modules.size(); i++) {
					if (this.modules.get(i).getShowed() == true) {
						//use set to prevent duplication
						Set<Function> functions = new HashSet<Function>();
						for (GrantedAuthority grantedAuthority : user.getAuthorities()) {

							for (GroupFunction groupFunction : ((Group) grantedAuthority).getGroupFunctions()) {

								if (groupFunction.getFunction().getModule() != null && groupFunction.getFunction()
										.getModule().getCode().equals(this.modules.get(i).getCode())) {
									Iterator<Function> iter = this.modules.get(i).getFunctions().iterator();

									while (iter.hasNext()) {
										Function function = iter.next();
										if (groupFunction.getFunction().getCode().equals(function.getCode())
												&& function.getShowed() == true) {
											functions.add(function);
										}
									}

								}

							}
						}
						List<Function> functionsArrayList = new ArrayList<Function>();
						functionsArrayList.addAll(functions);
						Collections.sort(functionsArrayList, new Comparator<Function>() {
							public int compare(Function s1, Function s2) {

								return s1.getSequence().compareTo(s2.getSequence());

							}
						});

						this.modules.get(i).setFunctions(new LinkedHashSet<Function>(functionsArrayList));
					}
					//if module haven't any function. hidden it
					if(this.modules.get(i).getShowed() == true && CollectionUtils.isEmpty(this.modules.get(i).getFunctions())) {
						this.modules.get(i).setShowed(false);
					}

				}
			}
		}
	}

	public void navigationListener(ActionEvent event) throws Exception {

		String obj = (String) event.getComponent().getAttributes().get("functionCode");

		// Remove managed bean of session
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("scopedTarget." + obj + "ManagedBean");

	}

	public boolean isGrant(String functionCode, String itemCode) {

		if (this.getDefaultSetting().getDevMode() == true) {
			return true;
		}
		if (this.getUser() != null && this.getUser().getAuthorities() != null) {
			for (GrantedAuthority grantedAuthority : this.getUser().getAuthorities()) {

				for (GroupFunction groupFunction : ((Group) grantedAuthority).getGroupFunctions()) {
					if (groupFunction.getFunctionItem().getCode().equals(itemCode)
							&& groupFunction.getFunction().getCode().equals(functionCode)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void onTabChange(TabChangeEvent event) {
		this.activeTab = Integer.parseInt(((AccordionPanel) event.getComponent()).getActiveIndex());

	}

	public Integer getActiveTab() {
		return activeTab;
	}

	public void setActiveTab(Integer activeTab) {
		this.activeTab = activeTab;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public DefaultSetting getDefaultSetting() {
		return defaultSetting;
	}

	public void setDefaultSetting(DefaultSetting defaultSetting) {
		this.defaultSetting = defaultSetting;
	}



}
