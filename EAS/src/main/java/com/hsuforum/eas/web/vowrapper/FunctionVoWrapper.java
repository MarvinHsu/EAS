package com.hsuforum.eas.web.vowrapper;

import com.hsuforum.common.web.vo.impl.VoWrapperImpl;
import com.hsuforum.eas.entity.primary.Function;
import com.hsuforum.eas.web.vo.FunctionVo;

/**
 * The wrapper transfer Function to FunctionVo
 * @author Marvin
 */
public class FunctionVoWrapper extends VoWrapperImpl<Function, java.lang.String> {

	private static final long serialVersionUID = 1L;

	/**
	 * @see com.hsuforum.common.web.vo.impl.VoWrapperImpl#wrap(com.hsuforum.common.entity.BaseEntity)
	 */
	@Override
	public FunctionVo wrap(Function entity) {
		return new FunctionVo(entity);
	}
}