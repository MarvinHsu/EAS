package com.hsuforum.eas.entity.listener;

import com.hsuforum.eas.entity.User;

/**
 * Get user to update create user and update user
 * @author Marvin
 *
 */
public interface SystemUserOperations {

	/**
	 * Set create user
	 *
	 * @param createUser
	 */
	void setCreateUser(User createUser);

	/**
	 * Set update user
	 *
	 * @param updateUser
	 */
	void setUpdateUser(User updateUser);
}
