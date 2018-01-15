package com.hys.exam.sessionfacade.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseTypeExam;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 facade
 * 
 * 说明:
 */
public interface SystemSiteFacade {

	public List<SystemSite> getListByItem(SystemSite item);

	public void querySystemSiteList(Page<SystemSite> page,
			SystemSite item);

	public Integer save(SystemSite item);

	public SystemSite getItemById(Long id);

	/**
	 * 
	 * Description: 更新注册站点,同时更新站点课程分类关系表
	 * @param item courseTypeIds
	 * @return
	 */
	public int update(SystemSite item);

	public int deleteList(long[] selIdArr,String idColName);

	public int delete(long id, String string);

	/**
	 * 根据站点id和分类查询考试信息
	 * 
	 * @param siteId
	 * @param courseTypeId
	 * @return
	 */
	public List<ExamExamination> getExamExaminationList(Long siteId,
			Long courseTypeId);

	/**
	 * 新增站点试卷分类
	 * @param list
	 * @return
	 */
	public void addSystemSiteCourseTypeExamList(List<SystemSiteCourseTypeExam> list);
	
	/**
	 * 新增站点和更新站点的时候，查询站点是否存在
	 * @param site
	 * @return
	 */
	public List<SystemSite> getListByItem(String site, Long id);
	
}