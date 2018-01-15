package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.ExamPropListByDirectDAO;
import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamMajorOrder;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.service.local.ExamPropListByDirectManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class ExamPropListByDirectManageImpl extends BaseMangerImpl implements
	ExamPropListByDirectManage {

	private ExamPropListByDirectDAO localExamPropListByDirectDAO;
	
	public ExamPropListByDirectDAO getLocalExamPropListByDirectDAO() {
		return localExamPropListByDirectDAO;
	}

	public void setLocalExamPropListByDirectDAO(
			ExamPropListByDirectDAO localExamPropListByDirectDAO) {
		this.localExamPropListByDirectDAO = localExamPropListByDirectDAO;
	}

	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery) {
		return localExamPropListByDirectDAO.getNextLevelProp(propQuery);
	}

	@Override
	public List<ExamProp> getPropListByType(ExamProp prop) {
		return localExamPropListByDirectDAO.getPropListByType(prop);
	}


	@Override
	public List<ExamPropType> getExamPropTypeList() {
		return localExamPropListByDirectDAO.getExamPropTypeList();
	}

	@Override
	public List<ExamProp> getNextLevelProp(Long propValId) {
		return localExamPropListByDirectDAO.getNextLevelProp(propValId);
	}
	
	@Override
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop) {
		return localExamPropListByDirectDAO.getSourceTypeList(prop);
	}

	@Override
	public List<ExamSource> getSourceValList(ExamSource prop) {
		return localExamPropListByDirectDAO.getSourceValList(prop);
	}


	@Override
	public List<ExamHospital> getHospitalList(ExamHospital host) {
		return localExamPropListByDirectDAO.getHospitalList(host);
	}

	@Override
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major) {
		return localExamPropListByDirectDAO.getMajorOrderList(major);
	}

	@Override
	public Long getParentPropId(Long id) {
		return localExamPropListByDirectDAO.getParentPropId(id);
	}

}
