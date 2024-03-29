package com.hsuforum.eas.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.eas.entity.primary.User;
import com.hsuforum.eas.web.vo.UserVo;

/**
 * The wrapper transfer User to UserVo
 * @author Marvin
 */
public class UserVoWrapper extends VoWrapperImpl<User, java.lang.String> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public UserVo wrap(User entity) {
		return new UserVo(entity);
	}
}