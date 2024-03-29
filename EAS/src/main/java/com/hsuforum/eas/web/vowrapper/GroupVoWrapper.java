package com.hsuforum.eas.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.eas.entity.primary.Group;
import com.hsuforum.eas.web.vo.GroupVo;

/**
 * The wrapper transfer Group to GroupVo
 * @author Marvin
 */
public class GroupVoWrapper extends VoWrapperImpl<Group, java.lang.String> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public GroupVo wrap(Group bo) {
		return new GroupVo(bo);
	}
}