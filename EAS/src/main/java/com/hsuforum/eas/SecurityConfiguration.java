package com.hsuforum.eas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.hsuforum.eas.security.intercept.web.JpaFilterInvocationDefinitionSource;
import com.hsuforum.eas.security.provider.DBAuthenticationProvider;
import com.hsuforum.eas.security.userdetails.JpaUserDetailsService;
import com.hsuforum.eas.security.vote.UserVoter;
import com.hsuforum.eas.service.GroupFunctionService;
import com.hsuforum.eas.service.UserService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

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
