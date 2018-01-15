package com.hys.exam.sessionfacade.remote.impl;

import java.util.List;

import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.service.remote.ExamExaminationManage;
import com.hys.exam.sessionfacade.remote.ExamExaminationFacade;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 13, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminationFacadeImpl extends BaseSessionFacadeImpl implements
		ExamExaminationFacade {

	private ExamExaminationManage remoteExamExaminationManage;

	public ExamExaminationManage getRemoteExamExaminationManage() {
		return remoteExamExaminationManage;
	}

	public void setRemoteExamExaminationManage(
			ExamExaminationManage remoteExamExaminationManage) {
		this.remoteExamExaminationManage = remoteExamExaminationManage;
	}

	public Long addExamination(String key,ExamExamination exam)
			throws FrameworkRuntimeException {
		try {
			return remoteExamExaminationManage.addExamination(key,exam);
		} catch (Exception e) {
			logger.error("新增考试出现异常!", e);
			throw new FrameworkRuntimeException("新增考试出现异常!", e);
		}
	}

	@Override
	public void deleteExamination(String key,List<Long> id)
			throws FrameworkRuntimeException {
		try {
			remoteExamExaminationManage.deleteExamination(key,id);
		} catch (Exception e) {
			logger.error("删除考试出现异常!", e);
			throw new FrameworkRuntimeException("删除考试出现异常!", e);
		}
	}

	@Override
	public ExamExamination getExamExaminationById(String key,Long id)
			throws FrameworkRuntimeException {
		try {
			return remoteExamExaminationManage.getExamExaminationById(key,id);
		} catch (Exception e) {
			logger.error("获取考试出现异常!", e);
			throw new FrameworkRuntimeException("获取考试出现异常!", e);
		}
	}

	@Override
	public List<ExamExamination> getExaminationList(String key,
			ExamExaminationQuery examExaminationQuery)
			throws FrameworkRuntimeException {
		try {
			return remoteExamExaminationManage
					.getExaminationList(key,examExaminationQuery);
		} catch (Exception e) {
			logger.error("获取考试列表出现异常!", e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!", e);
		}
	}

	@Override
	public int getExaminationListSize(String key,ExamExaminationQuery examExaminationQuery)
			throws FrameworkRuntimeException {
		try {
			return remoteExamExaminationManage
					.getExaminationListSize(key,examExaminationQuery);
		} catch (Exception e) {
			logger.error("获取考试列表出现异常!", e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!", e);
		}
	}

	@Override
	public void updateExaminationById(String key,ExamExamination exam)
			throws FrameworkRuntimeException {
		try {
			remoteExamExaminationManage.updateExaminationById(key,exam);
		} catch (Exception e) {
			logger.error("修改考试出现异常!", e);
			throw new FrameworkRuntimeException("修改考试出现异常!", e);
		}
	}

	@Override
	public List<ExamExamination> getExaminationListByIds(String key,
			ExamExaminationQuery query) throws FrameworkRuntimeException {
		try {
			return remoteExamExaminationManage.getExaminationListByIds(key,query);
		} catch (Exception e) {
			logger.error("获取考试列表出现异常!", e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!", e);
		}
	}

	@Override
	public int getExaminationListByIdsSize(String key,ExamExaminationQuery query)
			throws FrameworkRuntimeException {
		try {
			return remoteExamExaminationManage.getExaminationListByIdsSize(key,query);
		} catch (Exception e) {
			logger.error("获取考试列表出现异常!", e);
			throw new FrameworkRuntimeException("获取考试列表出现异常!", e);
		}
	}

	@Override
	public void updateExaminationTypeByExamId(String key,Long typeId, Long id)
			throws FrameworkRuntimeException {
		try {
			remoteExamExaminationManage.updateExaminationTypeByExamId(key,typeId, id);
		} catch (Exception e) {
			logger.error("修改考试分类出现异常!", e);
			throw new FrameworkRuntimeException("修改考试分类出现异常!", e);
		}
	}
}
