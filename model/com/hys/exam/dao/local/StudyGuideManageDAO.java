package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamICD;
import com.hys.exam.model.StudyGuide;
import com.hys.exam.utils.PageList;

public interface StudyGuideManageDAO {
	
	List<StudyGuide> getStudyGuideList(StudyGuide guide);
	
	boolean addStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuide(StudyGuide guide);
	
	boolean updateStudyGuideICDs(Long guideId, Long icdPropId, int ctr);
	
	boolean deleteStudyGuide(StudyGuide guide);

	List<ExamICD> getIcdList(ExamICD icd);

	List<ExamICD> getIcdListByKey(ExamICD icd);

	List<ExamICD> getEditICDList(Long id);
	
	boolean addImageID(StudyGuide guide);

	List<StudyGuide> UpdataData(StudyGuide guide);

	List<StudyGuide> getStudyGuideListById(StudyGuide queryGuide);

	List<StudyGuide> getNextStudyGuide(long id, int type);

	void getStudyGuidePageList(PageList pl, StudyGuide queryGuide);
}
