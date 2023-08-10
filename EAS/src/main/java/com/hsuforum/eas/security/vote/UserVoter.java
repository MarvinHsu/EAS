package com.hsuforum.eas.security.vote;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.hsuforum.eas.entity.primary.FunctionItem;
import com.hsuforum.eas.entity.primary.Group;
import com.hsuforum.eas.entity.primary.GroupFunction;
import com.hsuforum.eas.entity.primary.User;

import lombok.extern.slf4j.Slf4j;

/**
 * User voter
 * @author Marvin
 *
 */
@Slf4j
public class UserVoter extends RoleVoter {

	
	private final static String URI_ENDFIX = "jsf";

	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> config) {
		int result = ACCESS_ABSTAIN;

		Object principal = authentication.getPrincipal();
		if (!(principal instanceof User)) {
			return result;
		}
		User user = (User) principal;
		Set<Group> groups = user.getGroups();

		if (!(object instanceof FilterInvocation)) {
			return result;
		}
		FilterInvocation fi = (FilterInvocation) object;

		Iterator iter = config.iterator();
		while (iter.hasNext()) {
			ConfigAttribute attribute = (ConfigAttribute) iter.next();

			if (super.supports(attribute)) {
				result = ACCESS_DENIED;
				for (Group group : groups) {
					for (GroupFunction groupFunction : group.getGroupFunctions()) {
						FunctionItem functionItem = groupFunction.getFunctionItem();
						if (functionItem != null) {

							if (fi.getRequestUrl().substring(0, fi.getRequestUrl().indexOf(URI_ENDFIX) + 3)
									.equals(functionItem.getUrl())) {
								return ACCESS_GRANTED;
							}
						}

					}
				}
			}
		}
		return result;
	}

}
