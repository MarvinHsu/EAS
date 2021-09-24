package com.hsuforum.eas.web.jsf.managed;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.hsuforum.eas.entity.Function;
import com.hsuforum.eas.entity.Group;
import com.hsuforum.eas.entity.GroupFunction;
import com.hsuforum.eas.entity.Module;
import com.hsuforum.eas.entity.User;
import com.hsuforum.eas.security.util.AAUtils;
import com.hsuforum.eas.service.ModuleService;
import com.hsuforum.eas.web.config.DefaultConfigManagedBean;

/**
 * Navigation menu managed bean
 */
@Component
@SessionScope
public class MenuManagedBean implements Serializable {

	private static final long serialVersionUID = 7319288785728714429L;

	@Autowired
	private DefaultConfigManagedBean defaultConfigManagedBean;

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
			if (this.user != null && user.getAuthorities() != null) {
				for (GrantedAuthority grantedAuthority : user.getAuthorities()) {

					for (GroupFunction groupFunction : ((Group) grantedAuthority).getGroupFunctions()) {
						for (int i = 0; i < this.modules.size(); i++) {
							if (groupFunction.getFunction().getModule() != null && groupFunction.getFunction()
									.getModule().getCode().equals(this.modules.get(i).getCode())) {
								this.modules.get(i).setShowed(true);
								Iterator<Function> iter = this.modules.get(i).getFunctions().iterator();
								Set<Function> functions = new LinkedHashSet<Function>();
								while (iter.hasNext()) {
									Function function = iter.next();
									if (groupFunction.getFunction().getCode().equals(function.getCode())) {
										function.setShowed(true);
									}
									functions.add(function);
								}
								this.modules.get(i).setFunctions(functions);

							}

						}
					}
				}
			}
		}
	}

	public void navigationListener(ActionEvent event) throws Exception {

		String obj = (String) event.getComponent().getAttributes().get("functionCode");

		// Remove managed bean of session
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("scopedTarget."+obj+"ManagedBean");

	}

	public boolean isGrant(String functionCode, String itemCode) {
	
		if (this.getDefaultConfigManagedBean().getDevMode() == true) {
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

	public DefaultConfigManagedBean getDefaultConfigManagedBean() {
		return defaultConfigManagedBean;
	}

	public void setDefaultConfigManagedBean(DefaultConfigManagedBean defaultConfigManagedBean) {
		this.defaultConfigManagedBean = defaultConfigManagedBean;
	}

}
