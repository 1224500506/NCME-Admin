package com.hys.exam.service.remote.impl;

import java.util.List;
import java.util.Map;

import com.hys.exam.dao.remote.ExamPropValDAO;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.service.remote.ExamPropValManage;

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
public class ExamPropValManageImpl implements ExamPropValManage {
	
	private ExamPropValDAO remoteExamPropValDAO;
	

	public ExamPropValDAO getRemoteExamPropValDAO() {
		return remoteExamPropValDAO;
	}

	public void setRemoteExamPropValDAO(ExamPropValDAO remoteExamPropValDAO) {
		this.remoteExamPropValDAO = remoteExamPropValDAO;
	}

	@Override
	public List<ExamPropValTemp> getBasePropVal(String key, Integer sysId,
			Integer type) throws Exception {
		return remoteExamPropValDAO.getBasePropVal(key, sysId, type);
	}

	@Override
	public Map<String, List<ExamPropVal>> getSysPropValBySysId(String key)
			throws Exception {
		return remoteExamPropValDAO.getSysPropValBySysId(key);
	}

}
