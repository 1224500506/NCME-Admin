package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.dao.local.StudyGuideManageDAO;
import com.hys.exam.model.ExamICD;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.service.local.StudyGuideManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.impl.BaseMangerImpl;

public class StudyGuideManageImpl extends BaseMangerImpl implements
		StudyGuideManage {

	private StudyGuideManageDAO localStudyGuideManageDAO;

	public StudyGuideManageDAO getLocalStudyGuideManageDAO() {
		return localStudyGuideManageDAO;
	}

	public void setLocalStudyGuideManageDAO(
			StudyGuideManageDAO localStudyGuideManageDAO) {
		this.localStudyGuideManageDAO = localStudyGuideManageDAO;
	}
	
	@Override
	public List<StudyGuide> getStudyGuideList(StudyGuide guide) {
		return localStudyGuideManageDAO.getStudyGuideList(guide);
	}
	@Override
	public List<StudyGuide> UpdateData(StudyGuide guide) {
		return localStudyGuideManageDAO.UpdataData(guide);
	}

	@Override
	public boolean addStudyGuide(StudyGuide guide) {
		return localStudyGuideManageDAO.addStudyGuide(guide);
	}
	@Override
	public boolean addImageID(StudyGuide guide) {
		return localStudyGuideManageDAO.addImageID(guide);
	}

	@Override
	public boolean updateStudyGuide(StudyGuide guide) {
		return localStudyGuideManageDAO.updateStudyGuide(guide);
	}

	@Override
	public boolean deleteStudyGuide(StudyGuide guide) {
		return localStudyGuideManageDAO.deleteStudyGuide(guide);
	}

	@Override
	public boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr) {
		return localStudyGuideManageDAO.updateStudyGuideICDs(guideId, icdPropId, ctr);
	}

	@Override
	public List<StudyGuide> getUdateData(StudyGuide queryGuide) {
		return localStudyGuideManageDAO.UpdataData(queryGuide) ;
		 
	}

	@Override
	public List<StudyGuide> getStudyGuideListById(StudyGuide queryGuide) {
		
		return localStudyGuideManageDAO.getStudyGuideListById(queryGuide);
	}

	@Override
	public List<ExamICD> getICDList(ExamICD icd) {
		// TODO Auto-generated method stub
		return localStudyGuideManageDAO.getIcdList(icd);
	}

	@Override
	public List<ExamICD> getIcdListByKey(ExamICD icd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamICD> getEditICDList(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudyGuide> getNextStudyGuide(long id, int level) {
		return localStudyGuideManageDAO.getNextStudyGuide(id, level);
	}

	@Override
	public void getStudyGuidePageList(PageList pl, StudyGuide queryGuide) {
		localStudyGuideManageDAO.getStudyGuidePageList(pl,queryGuide);
		
	}

}
