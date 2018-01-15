package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.dao.remote.ExamPaperTypeDAO;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.service.remote.ExamPaperTypeManage;

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
public class ExamPaperTypeManageImpl implements ExamPaperTypeManage {

	private ExamPaperTypeDAO remoteExamPaperTypeDAO;
	
	public ExamPaperTypeDAO getRemoteExamPaperTypeDAO() {
		return remoteExamPaperTypeDAO;
	}

	public void setRemoteExamPaperTypeDAO(ExamPaperTypeDAO remoteExamPaperTypeDAO) {
		this.remoteExamPaperTypeDAO = remoteExamPaperTypeDAO;
	}

	@Override
	public ExamPaperType addExamPaperType(String key, ExamPaperType ptype)
			throws Exception {
		return remoteExamPaperTypeDAO.addExamPaperType(key, ptype);
	}

	@Override
	public void deleteExamPaperType(String key, ExamPaperType ptype)
			throws Exception {
		remoteExamPaperTypeDAO.deleteExamPaperType(key, ptype);
	}

	@Override
	public ExamPaperType getExamPaperTypeById(String key, Long id)
			throws Exception {
		return remoteExamPaperTypeDAO.getExamPaperTypeById(key, id);
	}

	@Override
	public ExamReturnPaperType getExamPaperTypeListByParentId(String key,
			ExamPaperTypeQuery query) throws Exception {
		return remoteExamPaperTypeDAO.getExamPaperTypeListByParentId(key, query);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(String key,
			Integer[] idArr) throws Exception {
		return remoteExamPaperTypeDAO.getExamPaperTypeRootListById(key, idArr);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(String key,
			Integer[] idArr) throws Exception {
		return remoteExamPaperTypeDAO.getExamPaperTypeRootListBySysId(key, idArr);
	}

	@Override
	public ExamPaperType updateExamPaperType(String key, ExamPaperType ptype)
			throws Exception {
		return remoteExamPaperTypeDAO.updateExamPaperType(key, ptype);
	}

	@Override
	public void updateMovePaperType(String key, ExamPaperType ptype)
			throws Exception {
		remoteExamPaperTypeDAO.updateMovePaperType(key, ptype);
	}

}
