package com.hys.exam.sessionfacade.remote.impl;

import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.service.remote.ExamPropValManage;
import com.hys.exam.sessionfacade.remote.ExamPropValFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 13, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropValFacadeImpl extends BaseSessionFacadeImpl implements
		ExamPropValFacade {

	
	private ExamPropValManage remoteExamPropValManage;
	
	public ExamPropValManage getRemoteExamPropValManage() {
		return remoteExamPropValManage;
	}

	public void setRemoteExamPropValManage(ExamPropValManage remoteExamPropValManage) {
		this.remoteExamPropValManage = remoteExamPropValManage;
	}

	@Override
	public List<ExamPropValTemp> getBasePropVal(String key, Integer sysId,
			Integer type) throws FrameworkRuntimeException {
		try {
			return remoteExamPropValManage.getBasePropVal(key, sysId, type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public Map<String, List<ExamPropVal>> getSysPropValBySysId(String key)
			throws FrameworkRuntimeException {
		try {
			return remoteExamPropValManage.getSysPropValBySysId(key);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

}
