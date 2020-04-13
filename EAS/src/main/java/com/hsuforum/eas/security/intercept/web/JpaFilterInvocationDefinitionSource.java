package com.hsuforum.eas.security.intercept.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.hsuforum.eas.entity.FunctionItem;
import com.hsuforum.eas.entity.Group;
import com.hsuforum.eas.entity.GroupFunction;
import com.hsuforum.eas.service.GroupFunctionService;

/**
 * Filter invocation definition source
 * @author Marvin
 *
 */
public class JpaFilterInvocationDefinitionSource implements FilterInvocationSecurityMetadataSource {

	protected final Log logger = LogFactory.getLog(getClass());

	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	public JpaFilterInvocationDefinitionSource(GroupFunctionService groupFunctionService) {
		super();
		requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		List<GroupFunction> groupFunctions = groupFunctionService.findAllFetchRelation();

		for (GroupFunction groupFunction : groupFunctions) {
			List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
			Group group = groupFunction.getGroup();

			configAttributes.add(new SecurityConfig(group.getAuthority()));

			Collection<ConfigAttribute> definition = new ArrayList<ConfigAttribute>(configAttributes);

			FunctionItem funtionItem = groupFunction.getFunctionItem();

			RequestMatcher requestMatcher = new AntPathRequestMatcher(funtionItem.getUrl());
			requestMap.put(requestMatcher, definition);

			if (logger.isDebugEnabled()) {
				Iterator iter = definition.iterator();

				StringBuffer sb = new StringBuffer();
				while (iter.hasNext()) {
					ConfigAttribute attr = (ConfigAttribute) iter.next();
					sb.append(attr.getAttribute());
					sb.append(", ");
				}
				logger.debug("url = " + funtionItem.getUrl() + ", roles = " + sb.toString());
			}

		}

		// add definition source /login.jsf=IS_AUTHENTICATED_ANONYMOUSLY
		ArrayList<ConfigAttribute> ava = new ArrayList<ConfigAttribute>();
		ava.add(new SecurityConfig(AuthenticatedVoter.IS_AUTHENTICATED_ANONYMOUSLY));
		requestMap.put(new AntPathRequestMatcher("/login.jsf"), ava);
		// add definition source /**=IS_AUTHENTICATED_FULLY
		ArrayList<ConfigAttribute> aaf = new ArrayList<ConfigAttribute>();
		ava.add(new SecurityConfig(AuthenticatedVoter.IS_AUTHENTICATED_FULLY));
		requestMap.put(new AntPathRequestMatcher("/**/*.jsf"), aaf);

	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public Collection<ConfigAttribute> getConfigAttributeDefinitions() {
		return this.getAllConfigAttributes();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}

		return allAttributes;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
