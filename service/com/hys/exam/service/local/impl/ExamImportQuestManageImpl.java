package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamImportQuestAttDAO;
import com.hys.exam.model.ExamQuestAtt;
import com.hys.exam.model.ExamQuestRelation;
import com.hys.exam.service.local.ExamImportQuestManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamImportQuestManageImpl extends BaseMangerImpl implements
		ExamImportQuestManage {

	private ExamImportQuestAttDAO localExamImportQuestAttDAO;
	
	public ExamImportQuestAttDAO getLocalExamImportQuestAttDAO() {
		return localExamImportQuestAttDAO;
	}

	public void setLocalExamImportQuestAttDAO(
			ExamImportQuestAttDAO localExamImportQuestAttDAO) {
		this.localExamImportQuestAttDAO = localExamImportQuestAttDAO;
	}

	public void addQuestProp(List<ExamQuestAtt> propList) {
		localExamImportQuestAttDAO.addQuestProp(propList);
	}

	public void addSysPropVal(List<ExamQuestAtt> propList,List<ExamQuestRelation> relationList) {
		localExamImportQuestAttDAO.addSysPropVal(propList);
		localExamImportQuestAttDAO.addSyspropValRelation(relationList);
	}

}
