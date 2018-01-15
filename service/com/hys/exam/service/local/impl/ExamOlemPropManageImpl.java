package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamOlemPropDAO;
import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.service.local.ExamOlemPropManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemPropManageImpl extends BaseMangerImpl implements
		ExamOlemPropManage {

	private ExamOlemPropDAO localExamOlemPropDAO;

	public ExamOlemPropDAO getLocalExamOlemPropDAO() {
		return localExamOlemPropDAO;
	}

	public void setLocalExamOlemPropDAO(ExamOlemPropDAO localExamOlemPropDAO) {
		this.localExamOlemPropDAO = localExamOlemPropDAO;
	}

	@Override
	public void addExamOlemProp(ExamOlemProp prop) {
		localExamOlemPropDAO.addExamOlemProp(prop);
	}

	public boolean deleteExamOlemPropById(ExamOlemProp prop) {
		return localExamOlemPropDAO.deleteExamOlemPropById(prop);
	}

	@Override
	public ExamOlemProp getExamOlemPropById(ExamOlemProp prop) {
		return localExamOlemPropDAO.getExamOlemPropById(prop);
	}

	@Override
	public ExamReturnOlemProp getExamOlemPropList(ExamOlemPropQuery query) {
		return localExamOlemPropDAO.getExamOlemPropList(query);
	}

	public void updateExamOlemProp(ExamOlemProp prop) {
		localExamOlemPropDAO.updateExamOlemProp(prop);
	}

	@Override
	public void addExamOlemPropRefBaseProp(List<ExamOlemPropRefBaseProp> prop) {
		localExamOlemPropDAO.addExamOlemPropRefBaseProp(prop);
	}

	@Override
	public void deleteExamOlemPropRefBaseProp(ExamOlemPropRefBaseProp prop) {
		localExamOlemPropDAO.deleteExamOlemPropRefBaseProp(prop);
	}

	@Override
	public List<ExamOlemPropRefBaseProp> getExamOlemPropRefBasePropList(Long id) {
		return localExamOlemPropDAO.getExamOlemPropRefBasePropList(id);
	}

	@Override
	public int getExamOlemPropType(Long id) {
		return localExamOlemPropDAO.getExamOlemPropType(id);
	}

	@Override
	public void addImportExamOlemProp(List<ExamOlemProp> olemPropList,
			List<ExamOlemPropRefBaseProp> olemBasePropList) {
		localExamOlemPropDAO.addBatchExamOlemProp(olemPropList);
		localExamOlemPropDAO.addExamOlemPropRefBaseProp(olemBasePropList);
	}

	@Override
	public Long getId() {
		return localExamOlemPropDAO.getId();
	}

}
