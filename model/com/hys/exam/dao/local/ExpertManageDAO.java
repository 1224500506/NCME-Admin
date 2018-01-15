package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;
import com.hys.exam.utils.PageList;

/**
 * 专家信息管理
 * @author Han
 *
 */
public interface ExpertManageDAO {
	
	List<ExpertInfo> getExpertList(ExpertInfo expert);
	
	ExpertInfo getExpertInfo(Long id);
	
	boolean addExpertInfo(ExpertInfo expert) throws Exception;
	
	boolean updateExpertInfo(ExpertInfo expert) throws Exception;
	
	boolean deleteExpertInfo(Long id);
	
	//chelb add
	public ExpertInfo getExpertInfoByUsername(String username);
	
	public List<ExpertInfo> getExpertInfoNameByCvSetId(Long id);
	/**
	 *根据项目id获取项目负责人 或授课老师
	 *type=1为项目负责人
	 *type=2为授课老师
	 */
	public List<ExpertInfo> getExpertListByCvSetId(Long cvSetId,Integer type);
	/**
	 * 页面管理  --名师
	 * @param pl
	 * @param expertInfo
	 */
	void getExpertPageList(PageList pl, ExpertInfo expert);

	String lockExpertInfo(Long id, Integer lockState);
	/**
	 * 
	 * @param pl
	 * @param expertInfo
	 */
	void getExpertPageListView(PageList pl, ExpertInfo expertInfo);
}
