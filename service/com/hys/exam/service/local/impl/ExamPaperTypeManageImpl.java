package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.common.util.Utils;
import com.hys.exam.dao.local.ExamPaperTypeDAO;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.service.local.ExamPaperTypeManage;
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
public class ExamPaperTypeManageImpl extends BaseMangerImpl implements
		ExamPaperTypeManage {

	
	private ExamPaperTypeDAO localExamPaperTypeDAO;
	
	public ExamPaperTypeDAO getLocalExamPaperTypeDAO() {
		return localExamPaperTypeDAO;
	}

	public void setLocalExamPaperTypeDAO(ExamPaperTypeDAO localExamPaperTypeDAO) {
		this.localExamPaperTypeDAO = localExamPaperTypeDAO;
	}

	@Override
	public ExamPaperType addExamPaperType(ExamPaperType ptype) {
		return localExamPaperTypeDAO.addExamPaperType(ptype);
	}

	@Override
	public void deleteExamPaperType(ExamPaperType ptype) {
		localExamPaperTypeDAO.deleteExamPaperType(ptype);
	}

	@Override //删除试卷分类
	public void deleteExamPaperType(List<Long> delArray) {
		if(!Utils.isListEmpty(delArray)){
			ExamPaperType ptype = null ;
			for (Long primaryid : delArray) {
				ptype = new ExamPaperType() ;
				ptype.setId(primaryid) ;
				localExamPaperTypeDAO.deleteExamPaperType(ptype);
			}
		}
	}
	
	@Override
	public ExamPaperType getExamPaperTypeById(Long id) {
		return localExamPaperTypeDAO.getExamPaperTypeById(id);
	}


	@Override
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(Integer[] idArr) {
		return localExamPaperTypeDAO.getExamPaperTypeRootListBySysId(idArr);
	}

	@Override
	public ExamPaperType updateExamPaperType(ExamPaperType ptype) {
		return localExamPaperTypeDAO.updateExamPaperType(ptype);
	}

	@Override
	public ExamReturnPaperType getExamPaperTypeListByParentId(
			ExamPaperTypeQuery query) {
		return localExamPaperTypeDAO.getExamPaperTypeListByParentId(query);
	}

	@Override
	public void updateMovePaperType(ExamPaperType ptype) {
		localExamPaperTypeDAO.updateMovePaperType(ptype);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(Integer[] idArr) {
		return localExamPaperTypeDAO.getExamPaperTypeRootListById(idArr);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListByChildId(Long id) {
		return localExamPaperTypeDAO.getExamPaperTypeRootListByChildId(id);
	}

}
