package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.service.local.ExamQuestionLabelManage;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionLabelFacadeImpl extends BaseSessionFacadeImpl
		implements ExamQuestionLabelFacade {

	private ExamQuestionLabelManage localExamQuestionLabelManage;
	
	public ExamQuestionLabelManage getLocalExamQuestionLabelManage() {
		return localExamQuestionLabelManage;
	}

	public void setLocalExamQuestionLabelManage(
			ExamQuestionLabelManage localExamQuestionLabelManage) {
		this.localExamQuestionLabelManage = localExamQuestionLabelManage;
	}



	public List<ExamQuestionLabel> getQuestionLabel(int type)
			throws FrameworkRuntimeException {
		try{
			return localExamQuestionLabelManage.getQuestionLabel(type);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}		
		
	}

}
