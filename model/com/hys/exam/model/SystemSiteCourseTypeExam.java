package com.hys.exam.model;

/**
 * 
 * 标题：站点课程分类考试
 * 
 * 作者：陈明凯 2013-3-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemSiteCourseTypeExam extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1939240308615975665L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 站点ID
	 */
	private Long siteId;
	/**
	 * 课程分类ID
	 */
	private Long courseTypeId;
	/**
	 * 考试ID
	 */
	private Long examId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getCourseTypeId() {
		return courseTypeId;
	}

	public void setCourseTypeId(Long courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

}
