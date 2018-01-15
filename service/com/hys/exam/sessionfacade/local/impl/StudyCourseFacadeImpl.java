package com.hys.exam.sessionfacade.local.impl;

import java.util.List;

import com.hys.exam.common.util.Page;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.service.local.StudyCourseManage;
import com.hys.exam.sessionfacade.local.StudyCourseFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程信息 facadeImpl
 * 
 * 说明:
 */
public class StudyCourseFacadeImpl extends BaseSessionFacadeImpl implements StudyCourseFacade {
	
	private StudyCourseManage studyCourseManage ;
	
	@Override //查询课程分类下的课程信息
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course, String courseTypes) 
			throws FrameworkRuntimeException{
		try{
			studyCourseManage.getStudyCourseList(page, course, courseTypes);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	//授权课程列表
	@Override
	public void getStudyCreditCourseList(Page<StudyCourse> page, StudyCourse course, String CourseTypeIds){
		studyCourseManage.getStudyCreditCourseList(page, course, CourseTypeIds);
	}
	
	@Override //添加课程信息
	public int addStudyCourse(StudyCourse course) throws FrameworkRuntimeException {
		try{
			return studyCourseManage.addStudyCourse(course);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	//批量更新课程状态
	@Override
	public int updateBatchStatus(String ids, Integer status){
		return this.studyCourseManage.updateBatchStatus(ids, status);
	}
	
	@Override //修改课程信息
	public int updateStudyCourse(StudyCourse course) throws FrameworkRuntimeException {
		try{
			return studyCourseManage.updateStudyCourse(course);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //修改课程信息2 StringBuilder 拼装修改
	public int updateStudyCourse2(StudyCourse course) throws FrameworkRuntimeException {
		try{
			return studyCourseManage.updateStudyCourse2(course);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //根据课程ID 查询指定课程信息
	public StudyCourse getStudyCourseById(Long courseId) throws FrameworkRuntimeException {
		try{
			return studyCourseManage.getStudyCourseById(courseId);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //批量删除课程信息
	public int deleteStudyCourse(List<StudyCourse> curList)
			throws FrameworkRuntimeException {
		try{
			return studyCourseManage.deleteStudyCourse(curList);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //删除指定课程信息
	public int deleteStudyCourse(Long curId) throws FrameworkRuntimeException {
		try{
			return studyCourseManage.deleteStudyCourse(curId);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //根据课程ID 查询课程下课件信息
	public List<StudyCourseElement> getStudyCourseElementByCourse(Long courseId)
			throws FrameworkRuntimeException {
		try{
			return studyCourseManage.getStudyCourseElementByCourse(courseId);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override //根据组件ID 查询课件信息
	public StudyCourseware getCourseEleWareByElement(Long elementId)
			throws FrameworkRuntimeException {
		try{
			return studyCourseManage.getCourseEleWareByElement(elementId);
		}catch(Exception e){
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public void setStudyCourseManage(StudyCourseManage studyCourseManage) {
		this.studyCourseManage = studyCourseManage;
	}
}