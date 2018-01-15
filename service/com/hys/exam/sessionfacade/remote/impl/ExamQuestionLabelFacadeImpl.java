package com.hys.exam.sessionfacade.remote.impl;

import java.util.List;

import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.service.remote.ExamQuestionLabelManage;
import com.hys.exam.sessionfacade.remote.ExamQuestionLabelFacade;
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
public class ExamQuestionLabelFacadeImpl extends BaseSessionFacadeImpl
		implements ExamQuestionLabelFacade {
	
	private ExamQuestionLabelManage remoteExamQuestionLabelManage;
	
	public ExamQuestionLabelManage getRemoteExamQuestionLabelManage() {
		return remoteExamQuestionLabelManage;
	}



	public void setRemoteExamQuestionLabelManage(
			ExamQuestionLabelManage remoteExamQuestionLabelManage) {
		this.remoteExamQuestionLabelManage = remoteExamQuestionLabelManage;
	}

	@Override
	public List<ExamQuestionLabel> getQuestionLabel(String key, int type)
			throws FrameworkRuntimeException {
		try{
			return remoteExamQuestionLabelManage.getQuestionLabel(key, type);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}			
		
	}

}
