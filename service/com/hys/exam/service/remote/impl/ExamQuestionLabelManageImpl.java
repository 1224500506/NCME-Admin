package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.dao.remote.ExamQuestionLabelDAO;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.service.remote.ExamQuestionLabelManage;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 10, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionLabelManageImpl implements ExamQuestionLabelManage {

	private ExamQuestionLabelDAO remoteExamQuestionLabelDAO;
	
	
	public ExamQuestionLabelDAO getRemoteExamQuestionLabelDAO() {
		return remoteExamQuestionLabelDAO;
	}



	public void setRemoteExamQuestionLabelDAO(
			ExamQuestionLabelDAO remoteExamQuestionLabelDAO) {
		this.remoteExamQuestionLabelDAO = remoteExamQuestionLabelDAO;
	}



	@Override
	public List<ExamQuestionLabel> getQuestionLabel(String key, int type)
			throws Exception {
		return remoteExamQuestionLabelDAO.getQuestionLabel(key, type);
	}

}
