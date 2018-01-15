/**
 *
 * <p>Description: 描述</p>
 * @author chenlaibin
 * @version 1.0 2015-12-29
 */

package com.hys.exam.model.system;

public class SystemPayCardCourse {

	/**
	 * 卡类型ID
	 */
	private Long cardTypeId;
	
	/**
	 * 课程ID
	 */
	private Long courseId;
	
	/**
	 * 课程名称
	 */
	private String studyCourseName;

	public Long getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Long cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getStudyCourseName() {
		return studyCourseName;
	}

	public void setStudyCourseName(String studyCourseName) {
		this.studyCourseName = studyCourseName;
	}
}


