package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.SystemSite;
import com.hys.exam.model.SystemSiteCourseTypeExam;
import com.hys.exam.model.TrainContentSite;
import com.hys.exam.model.system.SystemAdminOrganVO;
import com.hys.exam.model.system.SystemSiteVO;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息 dao
 * 
 * 说明:
 */
public interface SystemSiteDAO {

	public List<SystemSite> getListByItem(SystemSite item);

	public void querySystemSiteList(Page<SystemSite> page, SystemSite item);

	public Integer save(SystemSite item);

	public SystemSite getItemById(Long id);

	public int update(SystemSite item);

	public int deleteList(long[] selIdArr, String idColName);

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
	 * 删除站点课程分类考试
	 * 
	 * @param siteId
	 * @param courseTypeId
	 * @return
	 */
	public int deleteSystemSiteCourseTypeExam(Long siteId, Long courseTypeId);

	/**
	 * 新增站点课程分类考试
	 * 
	 * @param siteId
	 * @param courseTypeId
	 * @return
	 */
	public int addSystemSiteCourseTypeExam(SystemSiteCourseTypeExam s);

	/**
	 * 新增站点信息
	 * 
	 * @param t
	 * @return
	 */
	public int addTrainContentSite(TrainContentSite t);
	
	public List<SystemSiteVO> getSystemSiteList();
	
	//得到绑定/未绑定站点
	public List<SystemSiteVO> getSystemSiteListByTypeId(Long typeId, boolean flag);
	
	public List<SystemAdminOrganVO> getSystemAdminOrganList(Long siteId);
	
	//得到已绑定/未绑定的地区
	public List<SystemAdminOrganVO> getSystemAdminOrganList(Long siteId, boolean flag);
	
	public int saveSystemCardTypeSite(Long typeId, Long siteId); 
	
	public int deleteSystemCardTypeSite(Long typeId, Long siteId);
	
	public int saveSystemSiteOrgan(Long siteId, Long organId);
	
	public int deleteSystemSiteOrgan(Long siteId, Long organId);
	
	//得到学习卡已绑定的地区
	public List<SystemAdminOrganVO> getSystemAdminOrganListByCardType(Long typeId, boolean flag);
	public int savePaycardOrgan(Long typeId, Long organId, Integer orgType);
	public int deletePaycardOrgan(Long typeId, Long organId);

	/**
	 * 新增站点的时候，查询改站点是否存在
	 * @param site
	 * @return
	 */
	public List<SystemSite> getListByItem(String site, Long id);
}