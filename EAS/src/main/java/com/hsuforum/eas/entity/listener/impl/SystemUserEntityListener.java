package com.hsuforum.eas.entity.listener.impl;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hsuforum.eas.entity.User;
import com.hsuforum.eas.entity.listener.SystemUserOperations;

/**
 * Set create and update user listenter
 * @author Marvin
 *
 */
public class SystemUserEntityListener {

	/**
	 * In create set create user
	 * @param entity
	 */
	@PrePersist
	protected void toSetCreateUser(Object entity) {
		if (entity instanceof SystemUserOperations) {
			SecurityContext sc = SecurityContextHolder.getContext();
			User user = (User) sc.getAuthentication().getPrincipal();
			((SystemUserOperations) entity).setCreateUser(user);
		}
	}

	/**
	 * In update set update user
	 * @param entity
	 */
	@PreUpdate
	protected void toSetUpdateUser(Object entity) {
		if (entity instanceof SystemUserOperations) {
			SecurityContext sc = SecurityContextHolder.getContext();
			User user = (User) sc.getAuthentication().getPrincipal();
			((SystemUserOperations) entity).setUpdateUser(user);
		}
	}
}
