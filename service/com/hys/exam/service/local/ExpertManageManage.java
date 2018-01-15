package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExpertInfo;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.utils.PageList;
import com.hys.framework.service.BaseService;

/**
 * 专家管理
 * @author Han
 *
 */
public interface ExpertManageManage extends BaseService {

	/**
	 * 查询专家列表
	 * @param expert
	 * @return
	 */
	List<ExpertInfo> getExpertList(ExpertInfo expert);
	
	/**
	 * 取得专家
	 * @param expert
	 * @return
	 */
	ExpertInfo getExpertInfo(ExpertInfo expert);
	
	
	public ExpertInfo getExpertInfoByUsername(String username);
	
	public List<ExpertInfo> getExpertInfoNameByCvSetId(Long id);
	
	/**
	 * 取得专家
	 * @param id
	 * @return
	 */
	ExpertInfo getExpertInfo(Long id);
	
	/**
	 * 添加专家
	 * @param expert
	 * @return
	 * @throws Exception 
	 */
	boolean addExpertInfo(ExpertInfo expert) throws Exception;
	
	/**
	 * 修改专家
	 * @param expert
	 * @return
	 */
	boolean updateExpertInfo(ExpertInfo expert) throws Exception;
	
	/**
	 * 删除专家
	 * @param expert
	 * @return
	 */
	boolean deleteExpertInfo(ExpertInfo expert);
	
	/**
	 * 删除专家
	 * @param id
	 * @return
	 */
	boolean deleteExpertInfo(Long id);
	
	/**
	 *根据项目id获取项目负责人 或授课老师
	 *type=1为项目负责人
	 *type=2为授课老师
	 */
	public List<ExpertInfo> getExpertListByCvSetId(Long cvSetId,Integer type);
	/**
	 * 获取专家    admin页面管理
	 * @param pl
	 * @param expertInfo
	 */
	void getExpertPageList(PageList pl, ExpertInfo expertInfo);

	String lockExpertInfo(Long id, Integer checkState);
	/**
	 * 查看名师
	 * @param pl
	 * @param expertInfo
	 */
	void getExpertPageListView(PageList pl, ExpertInfo expertInfo);
}
