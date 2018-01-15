package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.dao.remote.ExamExaminTypeDAO;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.service.remote.ExamExaminTypeManage;

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
public class ExamExaminTypeManageImpl implements ExamExaminTypeManage {

	private ExamExaminTypeDAO remoteExamExaminTypeDAO;
	
	public ExamExaminTypeDAO getRemoteExamExaminTypeDAO() {
		return remoteExamExaminTypeDAO;
	}

	public void setRemoteExamExaminTypeDAO(ExamExaminTypeDAO remoteExamExaminTypeDAO) {
		this.remoteExamExaminTypeDAO = remoteExamExaminTypeDAO;
	}

	@Override
	public ExamExaminType addExamExaminType(String key, ExamExaminType etype)
			throws Exception {
		return remoteExamExaminTypeDAO.addExamExaminType(key, etype);
	}

	@Override
	public void deleteExamExaminTypeById(String key, ExamExaminType etype)
			throws Exception {
		remoteExamExaminTypeDAO.deleteExamExaminTypeById(key, etype);
	}

	@Override
	public ExamExaminType getExamExaminTypeById(String key, Long id)
			throws Exception {
		return remoteExamExaminTypeDAO.getExamExaminTypeById(key, id);
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(String key,
			ExamExaminTypeQuery query) throws Exception {
		return remoteExamExaminTypeDAO.getExamExaminTypeListByParentId(key, query);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(String key,
			Integer[] id) throws Exception {
		return remoteExamExaminTypeDAO.getExamExaminTypeRootById(key, id);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(String key,
			Integer[] id) throws Exception {
		return remoteExamExaminTypeDAO.getExamExaminTypeRootBySysId(key, id);
	}

	@Override
	public ExamExaminType updateExamExaminType(String key, ExamExaminType etype)
			throws Exception {
		return remoteExamExaminTypeDAO.updateExamExaminType(key, etype);
	}

	@Override
	public void updateMoveExaminType(String key, ExamExaminType etype)
			throws Exception {
		remoteExamExaminTypeDAO.updateMoveExaminType(key, etype);
	}

}
