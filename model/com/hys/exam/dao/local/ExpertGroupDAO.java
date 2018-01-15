package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.utils.PageList;

/**
 * 专家委员会管理
 * @author Han
 *
 */
public interface ExpertGroupDAO {
	
	List<ExpertGroup> getExpertGroupList(ExpertGroup group);
	
	ExpertGroup getExpertGroup(ExpertGroup group);
	
	ExpertGroup getExpertGroup(Long id);
	
	List<ExpertGroup> getExpertGroupfromName(ExpertGroup group);
	
	boolean addExpertGroup(ExpertGroup group);
	
	boolean updateExpertGroup(ExpertGroup group);
	
	boolean deleteExpertGroup(ExpertGroup group);
	
	boolean deleteExpertGroup(Long id);
	
	List<ExpertGroupTerm> getTermList(ExpertGroup group);
	
	ExpertGroupTerm getExpertGroupTerm(ExpertGroupTerm term);
	
	boolean addExpertGroupTerm(ExpertGroupTerm term);
	
	boolean updateExpertGroupTerm(ExpertGroupTerm term);
	
	boolean deleteExpertGroupTerm(ExpertGroupTerm term);
	//-----------------页面管理，新加的方法---------------------------------
	//查询绑定的内容
	void getExpertGroupPageList(PageList pl, ExpertGroup group, boolean isBind);
	//绑定详细的内容
	void getExpertGroupPageListEdit(PageList pl, ExpertGroup group);
	
	List<ExpertInfo> getGroupExpertInfo(Long groupId, int[] args);

	List<ExpertInfo> getGroupExpertInfo(Long id);

	boolean updateExpertGroupLockstate(ExpertGroup group);

	boolean resortOrderNum(String orderstr);
}
