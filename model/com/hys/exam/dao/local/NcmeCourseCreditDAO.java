package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.NcmeCourseCredit;
import com.hys.exam.model.StudyCourseSetting;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public interface NcmeCourseCreditDAO {
	/**
	 * 查询授权信息 分页
	 * 
	 * @param page
	 * @param courseware
	 */
	public void getNcmeCourseCreditList(Page<NcmeCourseCredit> page, NcmeCourseCredit ncmeCourseCredit);
	
	//根据课程分类得到其授权的课程列表
	public List<NcmeCourseCredit> getNcmeCourseCreditListByCourseType(
			Page<NcmeCourseCredit> page, NcmeCourseCredit n, Long cardTypeId);

	/**
	 * 新增
	 * 
	 * @param n
	 * @return
	 */
	public int addNcmeCourseCredit(NcmeCourseCredit n);
	
	/**
	 * 
	 * 修改授权
	 * @param n
	 */
	public void updateNcmeCourseCredit(NcmeCourseCredit n);
	
	//修改授权时间
	public int updateNcmeCourseCreditDate(NcmeCourseCredit credit);
	
	/**
	 * 
	 * 得到授权 object
	 * @param year
	 * @param courseId
	 * @param siteId
	 * @return
	 */
	public NcmeCourseCredit getNcmeCourseCredit(String year, Long courseId, Long siteId);

	/**
	 * 删除
	 * 
	 * @param n
	 * @return
	 */
	public int deleteNcmeCourseCredit(NcmeCourseCredit n);

	/**
	 * 根据主键查询
	 * 
	 * @param creditYear
	 * @param courseId
	 * @param organId
	 * @return
	 */
	public NcmeCourseCredit getNcmeCourseCreditById(String creditYear, String courseId, Long siteId, Integer courseType);
	
	//得到课件播放时间
	public StudyCourseSetting getStudyCourseSetting();
	
	//保存课件播放时间
	public int saveStudyCourseSetting(Long id, Double time);
}
