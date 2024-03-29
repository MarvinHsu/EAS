package com.hsuforum.eas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.hsuforum.common.service.util.ServiceLocator;
import com.hsuforum.eas.exception.ExceptionHandler;
import com.hsuforum.eas.security.intercept.web.JpaFilterInvocationDefinitionSource;
import com.hsuforum.eas.security.provider.DBAuthenticationProvider;
import com.hsuforum.eas.security.userdetails.JpaUserDetailsService;
import com.hsuforum.eas.security.vote.UserVoter;
import com.hsuforum.eas.service.GroupFunctionService;
import com.hsuforum.eas.service.UserService;
@Configuration
public class ProjectConfiguration {
	@Bean
	@ConfigurationProperties(prefix = "project")
	DefaultSetting defaultSetting() {
		return new DefaultSetting();
	}
	@Bean
	ExceptionHandler exceptionHandler() {
		return new ExceptionHandler();
	}
	@Bean
	ServiceLocator serviceLocator() {
		return new ServiceLocator();
	}
	@Bean
    SessionRegistry SessionRegistry() {
		return new SessionRegistryImpl();
    }
	
	@Bean
    ConcurrentSessionControlAuthenticationStrategy sessionController(SessionRegistry sessionRegistry) {
		ConcurrentSessionControlAuthenticationStrategy sessionController= new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		sessionController.setMaximumSessions(1);
		return sessionController;
    }
	@Bean
    ConcurrentSessionFilter concurrentSessionFilter(SessionRegistry sessionRegistry) {
		ConcurrentSessionFilter concurrentSessionFilter= new ConcurrentSessionFilter(sessionRegistry);
		return concurrentSessionFilter;
    }
	@Bean
    SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        return new SecurityContextPersistenceFilter();
    }
	@Bean
    LogoutFilter logoutFilter(RememberMeServices rememberMeServices) {
		LogoutHandler[] logoutHandlers = new LogoutHandler[2];
		logoutHandlers[0]=(LogoutHandler) rememberMeServices;
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.setInvalidateHttpSession(true);
		securityContextLogoutHandler.setClearAuthentication(true);
		logoutHandlers[1]=securityContextLogoutHandler;
		LogoutFilter LogoutFilter = new LogoutFilter("/default.jsf", logoutHandlers);
		LogoutFilter.setFilterProcessesUrl("/j_spring_security_logout.jsf");
        return LogoutFilter;
    }
	
	@Bean
    UserDetailsService userDetailsService(UserService userService) {
		
		JpaUserDetailsService jpaUserDetailsService = new JpaUserDetailsService();
		jpaUserDetailsService.setUserService(userService);
        return jpaUserDetailsService;
    }
	
	@Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, DefaultSetting defaultSetting, PasswordEncoder passwordEncoder) {
		
		DBAuthenticationProvider dbAuthenticationProvider= new DBAuthenticationProvider();
		dbAuthenticationProvider.setUserDetailsService(userDetailsService);
		dbAuthenticationProvider.setDefaultSetting(defaultSetting);
		dbAuthenticationProvider.setPasswordEncoder(passwordEncoder);
		AnonymousAuthenticationProvider anonymousAuthenticationProvider = new AnonymousAuthenticationProvider("foobar");
		RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider("spring");
		List<AuthenticationProvider> authenticationProviders = new ArrayList<AuthenticationProvider>();
		authenticationProviders.add(dbAuthenticationProvider);
		authenticationProviders.add(anonymousAuthenticationProvider);
		authenticationProviders.add(rememberMeAuthenticationProvider);
		AuthenticationManager authenticationManager =new ProviderManager(authenticationProviders);
        return authenticationManager;
    }
	@Bean
    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(SessionAuthenticationStrategy sessionAuthenticationStrategy, AuthenticationManager authenticationManager, RememberMeServices rememberMeServices, AuthenticationFailureHandler authenticationFailureHandler) {
		UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
		usernamePasswordAuthenticationFilter.setFilterProcessesUrl("/j_spring_security_check.jsf");
		usernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
		usernamePasswordAuthenticationFilter.setRememberMeServices(rememberMeServices);
		usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
		usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		usernamePasswordAuthenticationFilter.setPostOnly(false);
        return usernamePasswordAuthenticationFilter;
    }
	
	@Bean
    SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
		SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
		authenticationFailureHandler.setDefaultFailureUrl("/login.jsf");
        return authenticationFailureHandler;
    }
	
	@Bean
    SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        return new SecurityContextHolderAwareRequestFilter();
    }
	@Bean
    AnonymousAuthenticationFilter anonymousProcessingFilter() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(simpleGrantedAuthority);
		AnonymousAuthenticationFilter anonymousProcessingFilter = new AnonymousAuthenticationFilter("foobar", "anonymousUser", authorities);	
        return anonymousProcessingFilter;
    }
	
	@Bean
    LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
		LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login.jsf");	
        return loginUrlAuthenticationEntryPoint;
    }
	
	@Bean
    ExceptionTranslationFilter exceptionTranslationFilter(LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint) {
		ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(loginUrlAuthenticationEntryPoint);	
        return exceptionTranslationFilter;
    }
	
	@Bean
    AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		decisionVoters.add(new UserVoter());
		decisionVoters.add(new AuthenticatedVoter());
		AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
		
        return affirmativeBased;
    }
	@Bean
    FilterSecurityInterceptor filterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource, AuthenticationManager authenticationManager, AffirmativeBased accessDecisionManager) {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setObserveOncePerRequest(true);
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
		filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
		
        return filterSecurityInterceptor;
    }
	@Bean
    FilterInvocationSecurityMetadataSource securityMetadataSource(GroupFunctionService groupFunctionService) {
		JpaFilterInvocationDefinitionSource jpaFilterInvocationDefinitionSource = new JpaFilterInvocationDefinitionSource(groupFunctionService);
        return jpaFilterInvocationDefinitionSource;
    }
	@Bean
    LoggerListener loggerListener() {
        return new LoggerListener();
    }
	@Bean
    RememberMeAuthenticationFilter rememberMeFilter(RememberMeServices rememberMeServices, AuthenticationManager authenticationManager) {
		RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter(authenticationManager, rememberMeServices);
        return rememberMeFilter;
    }
	@Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
		RememberMeServices rememberMeServices = new TokenBasedRememberMeServices("spring", userDetailsService);
        return rememberMeServices;
    }
	@Bean
	PasswordEncoder passwordEncoder(DefaultSetting defaultSetting) {
		PasswordEncoder passwordEncoder=new Pbkdf2PasswordEncoder(defaultSetting.getSecret(), 0, 15, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
        return passwordEncoder;
    }
}
