package com.hys.exam.sessionfacade.local;

import java.util.List;

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
public interface StudyCoursewareFacade {
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
	public int saveStudyCourseware(StudyCourseware s);

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
	public void deleteStudyCourseware(List<StudyCourseware> list);
	
	/**
	 * 根据课程组件ID 查询课件信息
	 * @param elementId
	 * @return
	 */
	public StudyCourseware getStudyCoursewareByEleId(Long elementId) ;
}
