package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamQuestionLabelDAO;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.service.local.ExamQuestionLabelManage;
import com.hys.framework.service.impl.BaseMangerImpl;

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
public class ExamQuestionLabelManageImpl extends BaseMangerImpl implements
		ExamQuestionLabelManage {

	private ExamQuestionLabelDAO localExamQuestionLabelDAO;
	
	
	
	public ExamQuestionLabelDAO getLocalExamQuestionLabelDAO() {
		return localExamQuestionLabelDAO;
	}



	public void setLocalExamQuestionLabelDAO(
			ExamQuestionLabelDAO localExamQuestionLabelDAO) {
		this.localExamQuestionLabelDAO = localExamQuestionLabelDAO;
	}



	@Override
	public List<ExamQuestionLabel> getQuestionLabel(int type) {
		return localExamQuestionLabelDAO.getQuestionLabel(type);
	}

}
