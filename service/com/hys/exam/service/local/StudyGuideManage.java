package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamICD;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

public interface StudyGuideManage extends BaseService {
	
	List<StudyGuide> getStudyGuideList(StudyGuide guide);
	
	boolean addStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr);
	
	boolean deleteStudyGuide(StudyGuide guide);

	List<ExamICD> getICDList(ExamICD icd);

	List<ExamICD> getIcdListByKey(ExamICD icd);

	List<ExamICD> getEditICDList(Long id);
	
	boolean addImageID(StudyGuide guide);

	List<StudyGuide> getUdateData(StudyGuide queryGuide);

	List<StudyGuide> UpdateData(StudyGuide guide);

	List<StudyGuide> getStudyGuideListById(StudyGuide queryGuide);
	
	List<StudyGuide> getNextStudyGuide(long id, int type);

	void getStudyGuidePageList(PageList pl, StudyGuide queryGuide);

}
