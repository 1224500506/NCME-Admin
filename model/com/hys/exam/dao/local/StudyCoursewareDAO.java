package com.hys.exam.dao.local;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.StudyCourseware;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-2-25
 * 
 * 描述：
 * 
 * 说明:
 */
public interface StudyCoursewareDAO {
	/**
	 * 查询课件信息
	 * 
	 * @param page
	 * @param courseware
	 */
	public void getStudyCoursewareList(Page<StudyCourseware> page,
			StudyCourseware courseware);

	/**
	 * 查询课程下的课件信息
	 * @param page
	 * @param courseware
	 */
	public void getCourseWareByCourse(Page<StudyCourseware> page, StudyCourseware curware);
	
	/**
	 * 新增课件信息
	 * 
	 * @param s
	 * @return
	 */
	public int addStudyCourseware(StudyCourseware s);
	
	//修改课件path
	public int updateStudyCoursewareById(StudyCourseware s);

	/**
	 * 更新课件信息
	 * 
	 * @param s
	 * @return
	 */
	public int updateStudyCourseware(StudyCourseware s);

	/**
	 * 查询课件信息
	 * 
	 * @param s
	 * @return
	 */
	public StudyCourseware getStudyCoursewareById(Long id);	

	/**
	 * 删除课件信息
	 * 
	 * @param s
	 * @return
	 */
	public int deleteStudyCourseware(StudyCourseware s);
	
	/**
	 * 根据课程组件ID 查询课件信息
	 * @param elementId
	 * @return
	 */
	public StudyCourseware getStudyCoursewareByEleId(Long elementId) ;

	/**
	 * @param industryList
	 * @return
	 */
	public void addStudyCoursewareIndustryList(StudyCourseware s);

	/**
	 * @param industryList
	 * @return
	 */
	public void addStudyCoursewareApplicableList(StudyCourseware s);

	/**
	 * @param industryList
	 * @return
	 */
	public void addStudyCoursewareKnowList(StudyCourseware s);

}
