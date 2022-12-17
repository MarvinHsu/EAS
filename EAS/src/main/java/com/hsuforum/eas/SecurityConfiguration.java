package com.hsuforum.eas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

@Configuration
public class SecurityConfiguration {

	@Autowired
	ConcurrentSessionFilter concurrentSessionFilter;
	@Autowired
	SecurityContextPersistenceFilter securityContextPersistenceFilter;
	@Autowired
	LogoutFilter logoutFilter;
	@Autowired
	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
	@Autowired
	SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter;
	@Autowired
	RememberMeAuthenticationFilter rememberMeFilter;
	@Autowired
	AnonymousAuthenticationFilter anonymousProcessingFilter;
	@Autowired
	ExceptionTranslationFilter exceptionTranslationFilter;
	@Autowired
	FilterSecurityInterceptor filterSecurityInterceptor;



	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


		httpSecurity
				.authorizeHttpRequests()
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/resources/**").permitAll()
				.requestMatchers("/img/**").permitAll()
				.requestMatchers("/*.html").permitAll()
				.requestMatchers("/*.xml").permitAll()
				.requestMatchers("/*.txt").permitAll()
				.requestMatchers("/login.jsf").permitAll()
				.requestMatchers("/jakarta.faces.resource/**").permitAll()
				.requestMatchers("/index.jspx").permitAll()
				.requestMatchers("/default.jsf").permitAll()
				.requestMatchers("/favicon.ico").permitAll()
				.requestMatchers("/error").permitAll()
				.requestMatchers("/exception/exception.jsf","/").permitAll()
				.and()
				.csrf()
				.disable()
				.authorizeHttpRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginPage("/login.jsf")
				.failureUrl("/login.jsf")
				.loginProcessingUrl("/j_spring_security_check.jsf")
				.and()
				.addFilterBefore(concurrentSessionFilter, ConcurrentSessionFilter.class)
				.addFilterBefore(securityContextPersistenceFilter, SecurityContextPersistenceFilter.class)
				.addFilterBefore(logoutFilter, LogoutFilter.class)
				.addFilterBefore(usernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(securityContextHolderAwareRequestFilter, SecurityContextHolderAwareRequestFilter.class)
				.addFilterBefore(rememberMeFilter,RememberMeAuthenticationFilter.class)
				.addFilterBefore(anonymousProcessingFilter, AnonymousAuthenticationFilter.class)
				.addFilterBefore(exceptionTranslationFilter, ExceptionTranslationFilter.class)
				.addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
		;

		return httpSecurity.build();

	}


}
