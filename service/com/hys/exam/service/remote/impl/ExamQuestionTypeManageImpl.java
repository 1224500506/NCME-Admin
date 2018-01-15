package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.dao.remote.ExamQuestionTypeDAO;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.service.remote.ExamQuestionTypeManage;

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
public class ExamQuestionTypeManageImpl implements ExamQuestionTypeManage {

	private ExamQuestionTypeDAO remoteExamQuestionTypeDAO;
	
	public ExamQuestionTypeDAO getRemoteExamQuestionTypeDAO() {
		return remoteExamQuestionTypeDAO;
	}

	public void setRemoteExamQuestionTypeDAO(
			ExamQuestionTypeDAO remoteExamQuestionTypeDAO) {
		this.remoteExamQuestionTypeDAO = remoteExamQuestionTypeDAO;
	}

	@Override
	public ExamQuestionType addExamQuestType(String key, ExamQuestionType qtype)
			throws Exception {
		return remoteExamQuestionTypeDAO.addExamQuestType(key, qtype);
	}

	@Override
	public void deleteExamQuestTypeById(String key, ExamQuestionType qtype)
			throws Exception {
		remoteExamQuestionTypeDAO.deleteExamQuestTypeById(key, qtype);
	}

	@Override
	public ExamQuestionType getExamQuestionTypeById(String key, Long id)
			throws Exception {
		return remoteExamQuestionTypeDAO.getExamQuestionTypeById(key, id);
	}

	@Override
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(String key,
			ExamQuestionTypeQuery query) throws Exception {
		return remoteExamQuestionTypeDAO.getExamQuestionTypeListByParentId(key, query);
	}

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(String key,
			Integer[] idArr) throws Exception {
		return remoteExamQuestionTypeDAO.getQuestionTypeRootById(key, idArr);
	}

	@Override
	public List<ExamQuestionType> getQuestionTypeRootBySysId(String key,
			Integer[] idArr) throws Exception {
		return remoteExamQuestionTypeDAO.getQuestionTypeRootBySysId(key, idArr);
	}

	@Override
	public List<ExamQuestionType> getSubSysTypeByTypeId(String key, Long[] idArr)
			throws Exception {
		return remoteExamQuestionTypeDAO.getSubSysTypeByTypeId(key, idArr);
	}

	@Override
	public ExamQuestionType updateExamQuestTypeById(String key,
			ExamQuestionType qtype) throws Exception {
		return remoteExamQuestionTypeDAO.updateExamQuestTypeById(key, qtype);
	}

	@Override
	public void updateMoveQuestionType(String key, ExamQuestionType qtype)
			throws Exception {
		remoteExamQuestionTypeDAO.updateMoveQuestionType(key, qtype);
	}

}
