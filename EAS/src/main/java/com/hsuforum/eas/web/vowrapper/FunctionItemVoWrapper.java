package com.hsuforum.eas.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.eas.entity.primary.FunctionItem;
import com.hsuforum.eas.web.vo.FunctionItemVo;

/**
 * The wrapper transfer FunctionItem to FunctionItemVo
 * @author Marvin
 */
public class FunctionItemVoWrapper extends VoWrapperImpl<FunctionItem, java.lang.String> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public FunctionItemVo wrap(FunctionItem entity) {
		return new FunctionItemVo(entity);
	}
}