package com.hsuforum.eas.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.hsuforum.common.web.jsf.utils.JSFMessageUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception handler in web layer action  
 * @author Marvin
 *
 */
@Aspect
@Slf4j
public class ExceptionHandler {

	protected final Log logger = LogFactory.getLog(this.getClass());


	@Pointcut("execution(* com.hsuforum.eas.web.jsf.managed..*ManagedBean.do*Action(..))")
	public void doActionInWebLayer() {
	}


	@Around("doActionInWebLayer()")
	public Object handleUnprocessedException(ProceedingJoinPoint pjp) throws Throwable {
		try {
			return pjp.proceed();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			JSFMessageUtils.showErrorMessage(e.getMessage());
			return null;
		} 
	}



}
