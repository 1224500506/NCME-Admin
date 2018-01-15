package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamQuestionTypeDAO;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.service.local.ExamQuestionTypeManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-13
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionTypeManageImpl extends BaseMangerImpl implements
		ExamQuestionTypeManage {

	
	private ExamQuestionTypeDAO localExamQuestionTypeDAO;
	
	public ExamQuestionTypeDAO getLocalExamQuestionTypeDAO() {
		return localExamQuestionTypeDAO;
	}

	public void setLocalExamQuestionTypeDAO(
			ExamQuestionTypeDAO localExamQuestionTypeDAO) {
		this.localExamQuestionTypeDAO = localExamQuestionTypeDAO;
	}

	public List<ExamQuestionType> getQuestionTypeRootBySysId(Integer[] idArr) {
		return localExamQuestionTypeDAO.getQuestionTypeRootBySysId(idArr);
	}
	
	public List<ExamQuestionType> getQuestionTypeRootById(Integer[] idArr) {
		return localExamQuestionTypeDAO.getQuestionTypeRootById(idArr);
	}

	public List<ExamQuestionType> getSubSysTypeByTypeId(Long[] idArr) {
		return localExamQuestionTypeDAO.getSubSysTypeByTypeId(idArr);
	}



	public ExamQuestionType addExamQuestType(ExamQuestionType qtype) {
		return localExamQuestionTypeDAO.addExamQuestType(qtype);
	}



	public void deleteExamQuestTypeById(ExamQuestionType qtype) {
		localExamQuestionTypeDAO.deleteExamQuestTypeById(qtype);
	}



	public ExamQuestionType getExamQuestionTypeById(Long id) {
		return localExamQuestionTypeDAO.getExamQuestionTypeById(id);
	}


	public ExamQuestionType updateExamQuestTypeById(ExamQuestionType qtype) {
		return localExamQuestionTypeDAO.updateExamQuestTypeById(qtype);
	}



	public ExamReturnQuestionType getExamQuestionTypeListByParentId(
			ExamQuestionTypeQuery query) {
		return localExamQuestionTypeDAO.getExamQuestionTypeListByParentId(query);
	}



	public void updateMoveQuestionType(ExamQuestionType qtype) {
		localExamQuestionTypeDAO.updateMoveQuestionType(qtype);
	}

	@Override
	public List<ExamQuestionType> getExamQuestionTypeRootListByChildId(Long id) {
		return localExamQuestionTypeDAO.getExamQuestionTypeRootListByChildId(id);
	}


}
