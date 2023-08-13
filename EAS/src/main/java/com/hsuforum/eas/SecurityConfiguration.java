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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

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
	SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		
		http.authorizeHttpRequests((requests) -> {
			try {
				requests
					.requestMatchers(mvcMatcherBuilder.pattern("/images/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/resources/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/img/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/*.html")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/*.xml")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/*.txt")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/login.jsf")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/jakarta.faces.resource/**")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/index.jspx")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/default.jsf")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/favicon.ico")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/login.jsf")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/exception/exception.jsf")).permitAll()
					.requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
					.and().csrf()
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
				    .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class);
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		
		return http.build();
	}


}
