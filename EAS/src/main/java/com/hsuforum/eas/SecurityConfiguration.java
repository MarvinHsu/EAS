package com.hsuforum.eas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
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
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
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

	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
        		.authorizeRequests();

        registry.antMatchers("/images/**").permitAll();
        registry.antMatchers("/resources/**").permitAll();
        registry.antMatchers("/img/**").permitAll();
        registry.antMatchers("/*.html").permitAll();
        registry.antMatchers("/*.xml").permitAll();    
   
        registry
        	.and()        
        	.csrf()
        	.disable()
        	.authorizeRequests()
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
        
    }


}
