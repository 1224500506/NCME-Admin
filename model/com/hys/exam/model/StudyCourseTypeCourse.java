package com.hys.exam.model;


/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：课程分类课程表
 * 
 * 说明:
 */
public class StudyCourseTypeCourse extends BaseObject {

	private static final long serialVersionUID = 7997149247553958690L;

	/**
	 * 课程分类ID
	 */
	private Long studyCourseTypeId ;

	/**
	 * 课程ID
	 */
	private Long studyCourseId ;

	public Long getStudyCourseTypeId() {
		return studyCourseTypeId;
	}

	public void setStudyCourseTypeId(Long studyCourseTypeId) {
		this.studyCourseTypeId = studyCourseTypeId;
	}

	public Long getStudyCourseId() {
		return studyCourseId;
	}

	public void setStudyCourseId(Long studyCourseId) {
		this.studyCourseId = studyCourseId;
	}
}
