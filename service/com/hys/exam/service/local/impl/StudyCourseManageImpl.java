package com.hys.exam.service.local.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hys.exam.common.util.Page;
import com.hys.exam.common.util.Utils;
import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.StudyCourseDAO;
import com.hys.exam.model.StudyCourse;
import com.hys.exam.model.StudyCourseElement;
import com.hys.exam.model.StudyCourseware;
import com.hys.exam.service.local.StudyCourseManage;
import com.hys.framework.service.impl.BaseMangerImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：课程信息 manageImpl
 * 
 * 说明:
 */
public class StudyCourseManageImpl extends BaseMangerImpl implements StudyCourseManage {

	private StudyCourseDAO studyCourseDAO ;
	
	@Override //查询课程分类下的课程信息
	public void getStudyCourseList(Page<StudyCourse> page, StudyCourse course, String courseTypes) {
		studyCourseDAO.getStudyCourseList(page, course, courseTypes) ;
	}
	
	//授权课程列表
	@Override
	public void getStudyCreditCourseList(Page<StudyCourse> page, StudyCourse course, String CourseTypeIds){
		studyCourseDAO.getStudyCreditCourseList(page, course, CourseTypeIds);
	}
	
	@Override //添加课程信息
	public int addStudyCourse(StudyCourse course) {
		//studyCourseDAO.addStudyCourse(course) ;
		//return studyCourseDAO.addStudyCourseTypeCourse(course.getStudyCourseTypeId(), course.getId()) ;
		return studyCourseDAO.addStudyCourse(course);
	}
	
	//批量更新课程状态
	@Override
	public int updateBatchStatus(String ids, Integer status){
		int ret = 0;
		if(StringUtils.isNotBlank(ids)){
			String [] array = ids.split(",");
			if(!Utils.isArrayEmpty(array)){
				for(int i=0; i<array.length; i++){
					if(StringUtils.isNotBlank(array[i])){
						ret += this.studyCourseDAO.updateBatchStatus(Long.parseLong(array[i]), status);
					}
				}
			}
		}
		return ret;
	}
	
	@Override //修改课程信息
	public int updateStudyCourse(StudyCourse course) {
		return studyCourseDAO.updateStudyCourse(course) ;
	}
	
	@Override //修改课程信息2 StringBuilder 拼装修改
	public int updateStudyCourse2(StudyCourse course) {
		return studyCourseDAO.updateStudyCourse2(course) ;
	}
	
	@Override //根据课程ID 查询指定课程信息
	public StudyCourse getStudyCourseById(Long courseId) {
		return studyCourseDAO.getStudyCourseById(courseId) ;
	}
	
	@Override //批量删除课程信息
	public int deleteStudyCourse(List<StudyCourse> curList) {
		int val = 0 ;
		if(!Utils.isListEmpty(curList)){
			for (StudyCourse studyCourse : curList) {
				studyCourse.setStatus(Constants.STATUS_2) ;
				int tem = studyCourseDAO.updateStudyCourse2(studyCourse) ;
				if(tem > 0){
					val += 1 ;
				}
			}
		}
		return val ;
	}
	
	@Override //删除指定课程信息
	public int deleteStudyCourse(Long curId) {
		StudyCourse studyCourse = new StudyCourse() ;
		studyCourse.setId(curId) ;
		studyCourse.setStatus(Constants.STATUS_2) ;
		return studyCourseDAO.updateStudyCourse2(studyCourse) ;
	}
	
	@Override //根据课程ID 查询课程下课件信息
	public List<StudyCourseElement> getStudyCourseElementByCourse(Long courseId){
		return studyCourseDAO.getStudyCourseElementByCourse(courseId) ;
	}
	
	@Override //根据组件ID 查询课件信息
	public StudyCourseware getCourseEleWareByElement(Long elementId) {
		return studyCourseDAO.getCourseEleWareByElement(elementId) ;
	}
	
	public void setStudyCourseDAO(StudyCourseDAO studyCourseDAO) {
		this.studyCourseDAO = studyCourseDAO;
	}
}