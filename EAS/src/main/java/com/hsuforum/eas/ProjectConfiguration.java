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

@Configuration
@ImportResource(value = { "classpath*:ScheduleContext.xml",
		"classpath*:WebContext.xml", "classpath*:ServiceContext.xml", "classpath*:DaoContext.xml",
		"classpath*:DBContext.xml" })
public class ProjectConfiguration {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}

	@Bean
	public TransactionInterceptor txAdvice(PlatformTransactionManager transactionManager) {
		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		
		RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
		readOnlyTx.setReadOnly(true);
		readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		
		RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
		requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		Map<String, TransactionAttribute> txMap = new HashMap<>();
		txMap.put("create*", requiredTx);
		txMap.put("update*", requiredTx);
		txMap.put("delete*", requiredTx);
		txMap.put("*", readOnlyTx);
		source.setNameMap(txMap);
		TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
		return txAdvice;
	}
	@Bean
    public Advisor dbOperation1(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Facade.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
	@Bean
    public Advisor dbOperation2(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.hsuforum..*Service.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
	@Bean
    public SessionRegistry SessionRegistry() {
		return new SessionRegistryImpl();
    }
	
	@Bean
    public ConcurrentSessionControlAuthenticationStrategy sessionController(SessionRegistry sessionRegistry) {
		ConcurrentSessionControlAuthenticationStrategy sessionController= new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		sessionController.setMaximumSessions(1);
		return sessionController;
    }
	@Bean
    public ConcurrentSessionFilter concurrentSessionFilter(SessionRegistry sessionRegistry) {
		ConcurrentSessionFilter concurrentSessionFilter= new ConcurrentSessionFilter(sessionRegistry);
		return concurrentSessionFilter;
    }
	@Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        return new SecurityContextPersistenceFilter();
    }
	@Bean
    public LogoutFilter logoutFilter(RememberMeServices rememberMeServices) {
		LogoutHandler[] logoutHandlers = new LogoutHandler[2];
		logoutHandlers[0]=(LogoutHandler) rememberMeServices;
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.setInvalidateHttpSession(true);
		securityContextLogoutHandler.setClearAuthentication(true);
		logoutHandlers[1]=securityContextLogoutHandler;
		LogoutFilter LogoutFilter = new LogoutFilter("/index.jspx", logoutHandlers);
		LogoutFilter.setFilterProcessesUrl("/j_spring_security_logout.jsf");
        return LogoutFilter;
    }
	
	@Bean
    public UserDetailsService userDetailsService(UserService userService) {
		
		JpaUserDetailsService jpaUserDetailsService = new JpaUserDetailsService();
		jpaUserDetailsService.setUserService(userService);
        return jpaUserDetailsService;
    }
	
	@Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
		
		DBAuthenticationProvider dbAuthenticationProvider= new DBAuthenticationProvider();
		dbAuthenticationProvider.setUserDetailsService(userDetailsService);
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
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(SessionAuthenticationStrategy sessionAuthenticationStrategy, AuthenticationManager authenticationManager, RememberMeServices rememberMeServices, AuthenticationFailureHandler authenticationFailureHandler) {
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
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
		SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
		authenticationFailureHandler.setDefaultFailureUrl("/login.jsf");
        return authenticationFailureHandler;
    }
	
	@Bean
    public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
        return new SecurityContextHolderAwareRequestFilter();
    }
	@Bean
    public AnonymousAuthenticationFilter anonymousProcessingFilter() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(simpleGrantedAuthority);
		AnonymousAuthenticationFilter anonymousProcessingFilter = new AnonymousAuthenticationFilter("foobar", "anonymousUser", authorities);	
        return anonymousProcessingFilter;
    }
	
	@Bean
    public LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint() {
		LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login.jsf");	
        return loginUrlAuthenticationEntryPoint;
    }
	
	@Bean
    public ExceptionTranslationFilter exceptionTranslationFilter(LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint) {
		ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(loginUrlAuthenticationEntryPoint);	
        return exceptionTranslationFilter;
    }
	
	@Bean
    public AffirmativeBased affirmativeBased() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		decisionVoters.add(new UserVoter());
		decisionVoters.add(new AuthenticatedVoter());
		AffirmativeBased affirmativeBased = new AffirmativeBased(decisionVoters);
		
        return affirmativeBased;
    }
	@Bean
    public FilterSecurityInterceptor filterSecurityInterceptor(FilterInvocationSecurityMetadataSource securityMetadataSource, AuthenticationManager authenticationManager, AffirmativeBased accessDecisionManager) {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setObserveOncePerRequest(true);
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
		filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource);
		
        return filterSecurityInterceptor;
    }
	@Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource(GroupFunctionService groupFunctionService) {
		JpaFilterInvocationDefinitionSource jpaFilterInvocationDefinitionSource = new JpaFilterInvocationDefinitionSource(groupFunctionService);
        return jpaFilterInvocationDefinitionSource;
    }
	@Bean
    public LoggerListener loggerListener() {
        return new LoggerListener();
    }
	@Bean
    public RememberMeAuthenticationFilter rememberMeFilter(RememberMeServices rememberMeServices, AuthenticationManager authenticationManager) {
		RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter(authenticationManager, rememberMeServices);
        return rememberMeFilter;
    }
	@Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
		RememberMeServices rememberMeServices = new TokenBasedRememberMeServices("spring", userDetailsService);
        return rememberMeServices;
    }

}
