package com.hys.exam.model;

import java.util.List;


/**
 * 
 * 标题：站点课程分类
 * 
 * 作者：郭津 2013-2-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class SystemSiteCourseType extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7194173519944381003L;
	/**
	 * 站点ID
	 */
	private Long siteId;
	/**
	 * 课程分类ID
	 */
	private Long courseTypeId;
	
	/**
	 * 站点名称
	 */
	private String siteName;
	/**
	 * 课程分类名称
	 */
	private String courseTypeName;
	
	/**
	 * 课程分类介绍
	 */
	private String introduction;

	/**
	 * 考试信息列表
	 */
	private List<ExamExamination> examExaminationList;

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<ExamExamination> getExamExaminationList() {
		return examExaminationList;
	}

	public void setExamExaminationList(List<ExamExamination> examExaminationList) {
		this.examExaminationList = examExaminationList;
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

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}
}
