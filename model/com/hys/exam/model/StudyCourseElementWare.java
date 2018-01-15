package com.hys.exam.model;


/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：课程组件课件
 * 
 * 说明:
 */
public class StudyCourseElementWare extends BaseObject {

	private static final long serialVersionUID = 8184113836724346035L;

	/**
	 * 课程组件ID
	 */
	private Long studyCourseElementId ;

	/**
	 * 课件ID
	 */
	private Long studyCoursewareId ;

	public Long getStudyCourseElementId() {
		return studyCourseElementId;
	}

	public void setStudyCourseElementId(Long studyCourseElementId) {
		this.studyCourseElementId = studyCourseElementId;
	}

	public Long getStudyCoursewareId() {
		return studyCoursewareId;
	}

	public void setStudyCoursewareId(Long studyCoursewareId) {
		this.studyCoursewareId = studyCoursewareId;
	}
}
