package com.hsuforum.eas.security.userdetails;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hsuforum.eas.entity.primary.User;
import com.hsuforum.eas.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Marvin
 *
 */
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

	

	private UserService userService;

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		if (log.isDebugEnabled()) {
			log.debug("Load user by username: " + username);
		}

		if (username != null && !username.equals("")) {
			username = username.toUpperCase();
		} else {
			return null;
		}

		User user = this.userService.doLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException(
					this.messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }));
		}

		UserDetails userDetail = user;

		if (userDetail.getAuthorities().size() == 0) {
			throw new UsernameNotFoundException(
					this.messages.getMessage("JdbcDaoImpl.noAuthority", new Object[] { username }));
		}

		return user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
