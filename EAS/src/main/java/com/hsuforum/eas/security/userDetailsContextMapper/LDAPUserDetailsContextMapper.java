package com.hsuforum.eas.security.userDetailsContextMapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import com.hsuforum.eas.entity.primary.User;
import com.hsuforum.eas.service.UserService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author Marvin
 *
 */
@Slf4j
public class LDAPUserDetailsContextMapper implements UserDetailsContextMapper {
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private UserService userService;

	public LDAPUserDetailsContextMapper() {
		super();
	}

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authority) {
		if (log.isDebugEnabled()) {
			log.debug("Load user by username: " + username);
		}

		Map<String, String> crit = new HashMap<String, String>();
		if (username != null) {
			username = username.toUpperCase();
		}
		crit.put("account", username);
		List<User> users = this.userService.findByCriteriaMapFetchRelation(crit);

		if (users == null || users.size() == 0) {
			throw new UsernameNotFoundException(
					this.messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }));
		}

		UserDetails user = users.get(0);

		if (user.getAuthorities().size() == 0) {
			throw new UsernameNotFoundException(
					this.messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] { username }));
		}

		return user;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}

}
