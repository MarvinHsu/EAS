package com.hsuforum.eas.security.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.owasp.esapi.errors.EncryptionException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

import com.hsuforum.common.web.util.EncryptUtils;

/**
 * 
 * DB authentication provider
 * @author Marvin
 *
 */
public class DBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private UserDetailsService userDetailsService;

	private boolean includeDetailsObject = true;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials"));
		}
		
		boolean valid = false;
		try {
			String decryptPassword=EncryptUtils.decrypt(userDetails.getPassword());
			if(decryptPassword.equals(authentication.getCredentials().toString())){
				valid=true;
			}
		} catch (EncryptionException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new BadCredentialsException(e.getMessage());
		}
		
				
		if (!valid) {
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials"));
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		if (logger.isDebugEnabled()) {
			logger.debug("Retrieve user details for username: " + username);
		}

		UserDetails loadedUser = null;
		if (username != null && !username.equals("")) {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);

		}
		if (loadedUser == null) {
			throw new AuthenticationServiceException(
					messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }));
		}
		return loadedUser;
	}

	@Override
	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	public boolean isIncludeDetailsObject() {
		return includeDetailsObject;
	}

	public void setIncludeDetailsObject(boolean includeDetailsObject) {
		this.includeDetailsObject = includeDetailsObject;
	}

}
