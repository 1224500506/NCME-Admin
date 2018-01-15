package com.hys.exam.service.local.impl;


import java.util.List;

import com.hys.exam.dao.local.CvLiveDAO;
import com.hys.exam.model.CvLive;
import com.hys.exam.model.CvLiveCourseware;
import com.hys.exam.model.CvLiveRefUnit;
import com.hys.exam.model.CvLiveStudyRef;
import com.hys.exam.model.SystemAccount;
import com.hys.exam.service.local.CvLiveManage;
import com.hys.framework.service.impl.BaseMangerImpl;

public class CvLiveManageImpl extends BaseMangerImpl implements CvLiveManage {

	private CvLiveDAO localCvLiveManageDAO;
	
	public CvLiveDAO getLocalCvLiveManageDAO() {
		return localCvLiveManageDAO;
	}
	public void setLocalCvLiveManageDAO(CvLiveDAO localCvLiveManageDAO) {
		this.localCvLiveManageDAO = localCvLiveManageDAO;
	}
	@Override
	public Long addCvLiveCourseware(CvLiveCourseware record) {
		return localCvLiveManageDAO.addCvLiveCourseware(record);
	}
	@Override
	public List<CvLiveCourseware> getCvLiveCoursewareList(
			CvLiveCourseware record) {
		return localCvLiveManageDAO.getCvLiveCoursewareList(record);
	}
	@Override
	public int updateCvLiveCoutsewareInfo(CvLiveCourseware record) {
		return localCvLiveManageDAO.updateCvLiveCoutsewareInfo(record);
	}
	@Override
	public List<CvLive> queryCvLiveList(Long cvId) {
		return localCvLiveManageDAO.queryCvLiveList(cvId);
	}
	@Override
	public Long addCvLiveRefUnit(CvLiveRefUnit record) {
		return localCvLiveManageDAO.addCvLiveRefUnit(record);
	}
	@Override
	public List<CvLiveRefUnit> getCvLiveRefUnitList(CvLiveRefUnit record) {
		return localCvLiveManageDAO.getCvLiveRefUnitList(record);
	}
	@Override
	public List<SystemAccount> getViewLiveUser(String classNo) {
		return localCvLiveManageDAO.getViewLiveUser(classNo);
	}
	@Override
	public List<CvLiveStudyRef> queryCvLiveStudyRef(CvLiveStudyRef record) {
		return localCvLiveManageDAO.queryCvLiveStudyRef(record);
	}

}
