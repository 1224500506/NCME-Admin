package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.dao.remote.ExamExaminationDAO;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.service.remote.ExamExaminationManage;

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
public class ExamExaminationManageImpl implements ExamExaminationManage {

	private ExamExaminationDAO remoteExamExaminationDAO;
	
	public ExamExaminationDAO getRemoteExamExaminationDAO() {
		return remoteExamExaminationDAO;
	}

	public void setRemoteExamExaminationDAO(
			ExamExaminationDAO remoteExamExaminationDAO) {
		this.remoteExamExaminationDAO = remoteExamExaminationDAO;
	}

	@Override
	public Long addExamination(String key, ExamExamination exam)
			throws Exception {
		return remoteExamExaminationDAO.addExamination(key, exam);
	}

	@Override
	public void deleteExamination(String key, List<Long> id) throws Exception {
		remoteExamExaminationDAO.deleteExamination(key, id);
	}

	@Override
	public ExamExamination getExamExaminationById(String key, Long id)
			throws Exception {
		return remoteExamExaminationDAO.getExamExaminationById(key, id);
	}

	@Override
	public List<ExamExamination> getExaminationList(String key,
			ExamExaminationQuery examExaminationQuery) throws Exception {
		return remoteExamExaminationDAO.getExaminationList(key, examExaminationQuery);
	}

	@Override
	public List<ExamExamination> getExaminationListByExamTypeId(String key,
			Long examTypeId) throws Exception {
		return remoteExamExaminationDAO.getExaminationListByExamTypeId(key, examTypeId);
	}

	@Override
	public List<ExamExamination> getExaminationListByIds(String key,
			ExamExaminationQuery query) throws Exception {
		return remoteExamExaminationDAO.getExaminationListByIds(key, query);
	}

	@Override
	public int getExaminationListByIdsSize(String key,
			ExamExaminationQuery query) throws Exception {
		return remoteExamExaminationDAO.getExaminationListByIdsSize(key, query);
	}

	@Override
	public int getExaminationListSize(String key,
			ExamExaminationQuery examExaminationQuery) throws Exception {
		return remoteExamExaminationDAO.getExaminationListSize(key, examExaminationQuery);
	}

	@Override
	public void updateExaminationTypeByExamId(String key, Long typeId, Long id)
			throws Exception {
		remoteExamExaminationDAO.updateExaminationTypeByExamId(key, typeId, id);
	}

	@Override
	public void updateExaminationById(String key, ExamExamination exam)
			throws Exception {
		remoteExamExaminationDAO.updateExaminationById(key, exam);
	}

}
