package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamHospital;
import com.hys.exam.model.ExamMajorOrder;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropType;
import com.hys.exam.model.ExamSource;
import com.hys.exam.model.ExamSourceType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.framework.service.BaseService;

public interface ExamPropListByDirectManage extends BaseService {

	public List<ExamProp> getPropListByType(ExamProp prop);
	
	public ExamReturnProp getNextLevelProp(ExamPropQuery propQuery);

	public List<ExamProp> getNextLevelProp(Long  propValId);

	public List<ExamPropType> getExamPropTypeList();
	
	public List<ExamSourceType> getSourceTypeList(ExamSourceType prop);
	
	public List<ExamSource> getSourceValList(ExamSource prop);
	
	public List<ExamHospital> getHospitalList(ExamHospital host);
	
	public List<ExamMajorOrder> getMajorOrderList(ExamMajorOrder major);
		
	public Long getParentPropId(Long id);

}
