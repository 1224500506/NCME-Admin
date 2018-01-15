package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExpertGroup;
import com.hys.exam.model.ExpertGroupTerm;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

/**
 * 专家委员会管理
 * @author Han
 *
 */
public interface ExpertGroupManage extends BaseService {

	/**
	 * 查询委员会或学组
	 * @param group
	 * @return
	 */
	List<ExpertGroup> getExpertGroupList(ExpertGroup group);
	
	/**
	 * 取得委员会或学组
	 * @param group
	 * @return
	 */
	ExpertGroup getExpertGroup(ExpertGroup group);
	
	/**
	 * 取得委员会或学组
	 * @param id
	 * @return
	 */
	ExpertGroup getExpertGroup(Long id);
	
	/**
	 * 添加委员会或学组
	 * @param group
	 * @return
	 */
	boolean addExpertGroup(ExpertGroup group);
	
	/**
	 * 修改委员会或学组
	 * @param group
	 * @return
	 */
	boolean updateExpertGroup(ExpertGroup group);
	
	/**
	 * 删除委员会或学组
	 * @param group
	 * @return
	 */
	boolean deleteExpertGroup(ExpertGroup group);
	
	/**
	 * 删除委员会或学组
	 * @param id
	 * @return
	 */
	boolean deleteExpertGroup(Long id);
	
	/**
	 * 查询委员会的届期
	 * @param group
	 * @return
	 */
	List<ExpertGroupTerm> getTermList(ExpertGroup group);
	
	/**
	 * 查询届期
	 * @param term
	 * @return
	 */
	ExpertGroupTerm getExpertGroupTerm(ExpertGroupTerm term);
	
	/**
	 * 添加届期
	 * @param term
	 * @return
	 */
	boolean addExpertGroupTerm(ExpertGroupTerm term);
	
	/**
	 * 修改届期
	 * @param term
	 * @return
	 */
	boolean updateExpertGroupTerm(ExpertGroupTerm term);
	
	/**
	 * 删除届期
	 * @param term
	 * @return
	 */
	boolean deleteExpertGroupTerm(ExpertGroupTerm term);
	
	List<ExpertGroup> getExpertGroupfromName(ExpertGroup group);

	///------------以下是---页面管理的方法-------------------------------------
	void getExpertGroupPageList(PageList pl, ExpertGroup group, boolean isBind);

	 List<ExpertInfo> getGroupExpertInfo(Long groupId, int... args);

	List<ExpertInfo> getGroupExpertInfo(Long id);
	//绑定详细的内容
	void getExpertGroupPageListEdit(PageList pl, ExpertGroup group);

	boolean updateExpertGroupLockstate(ExpertGroup group);
	//排序
	boolean resortOrderNum(String orderstr);
}
