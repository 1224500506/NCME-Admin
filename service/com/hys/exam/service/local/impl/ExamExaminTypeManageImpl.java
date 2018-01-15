package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamExaminTypeDAO;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.service.local.ExamExaminTypeManage;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-10
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminTypeManageImpl extends BaseMangerImpl implements
		ExamExaminTypeManage {

	
	private ExamExaminTypeDAO localExamExaminTypeDAO;

	public ExamExaminTypeDAO getLocalExamExaminTypeDAO() {
		return localExamExaminTypeDAO;
	}

	public void setLocalExamExaminTypeDAO(ExamExaminTypeDAO localExamExaminTypeDAO) {
		this.localExamExaminTypeDAO = localExamExaminTypeDAO;
	}

	@Override
	public ExamExaminType addExamExaminType(ExamExaminType etype) {
		return localExamExaminTypeDAO.addExamExaminType(etype);
	}

	@Override
	public void deleteExamExaminTypeById(ExamExaminType etype) {
		localExamExaminTypeDAO.deleteExamExaminTypeById(etype);
	}

	@Override
	public ExamExaminType getExamExaminTypeById(Long id) {
		return localExamExaminTypeDAO.getExamExaminTypeById(id);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(Integer[] id) {
		return localExamExaminTypeDAO.getExamExaminTypeRootBySysId(id);
	}

	@Override
	public ExamExaminType updateExamExaminType(ExamExaminType etype) {
		return localExamExaminTypeDAO.updateExamExaminType(etype);
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(
			ExamExaminTypeQuery query) {
		return localExamExaminTypeDAO.getExamExaminTypeListByParentId(query);
	}

	@Override
	public void updateMoveExaminType(ExamExaminType etype) {
		localExamExaminTypeDAO.updateMoveExaminType(etype);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(Integer[] id) {
		return localExamExaminTypeDAO.getExamExaminTypeRootById(id);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootListByChildId(Long id) {
		return localExamExaminTypeDAO.getExamExaminTypeRootListByChildId(id);
	}

	@Override
	public boolean deleteExamExaminTypeList(List<ExamExaminType> list)
			throws FrameworkRuntimeException {
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); ++i) {
				ExamExaminType e = list.get(i);
				localExamExaminTypeDAO.deleteExamExaminType(e);
			}
		}

		return true;
	}
}
