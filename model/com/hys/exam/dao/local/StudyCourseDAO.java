package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseware;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程信息 dao
 * 
 * 说明:
 */
public interface StudyCourseDAO {
	
	/**
	 * 查询课程分类下的课程信息
	 * @param page
	 * @param course
	 * @return
	 */
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course, String courseTypes) ;
	
	//授权课程列表
	public void getStudyCreditCourseList(Page<StudyCourse> page, StudyCourse course, String CourseTypeIds);

	/**
	 * 添加课程信息 
	 * @param course
	 * @return
	 */
	public int addStudyCourse(StudyCourse course) ;
	
	//批量更新课程状态
	public int updateBatchStatus(Long id, Integer status);
	
	/**
	 * 添加课程与课程分类关联管系
	 * @param curTypeId
	 * @param courseId
	 * @return
	 */
	public int addStudyCourseTypeCourse(Long curTypeId, Long courseId) ;
	
	/**
	 * 修改课程信息
	 * @param course
	 * @return
	 */
	public int updateStudyCourse(StudyCourse course) ;
	
	/**
	 * 修改课程信息2 StringBuilder 拼装修改
	 * @param course
	 * @return
	 */
	public int updateStudyCourse2(StudyCourse course) ;
	
	/**
	 * 根据课程ID 查询指定课程信息
	 * @param courseId
	 * @return
	 */
	public StudyCourse getStudyCourseById(Long courseId) ;
	
	/**
	 * 根据课程ID 查询课程下课件信息
	 * @param courseId
	 * @return
	 */
	public List<StudyCourseElement> getStudyCourseElementByCourse(Long courseId) ;
	
	/**
	 * 根据组件ID 查询课件信息
	 * @param elementId
	 * @return
	 */
	public StudyCourseware getCourseEleWareByElement(Long elementId) ;
}