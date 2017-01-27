package com.hsuforum.eas.security.ui.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
/**
 * 
 * @author Marvin
 *
 */
public class InsecureProcessingFilter extends AbstractAuthenticationProcessingFilter {

	protected InsecureProcessingFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);

	}

	public Authentication attemptAuthentication(HttpServletRequest request) throws AuthenticationException {
		String username = request.getParameter("username");
		username = username.trim();
		return null;
	}

	@Override
	public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest arg0,
			HttpServletResponse arg1)
			throws org.springframework.security.core.AuthenticationException, IOException, ServletException {

		return null;
	}

}
