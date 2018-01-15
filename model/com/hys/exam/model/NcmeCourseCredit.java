package com.hys.exam.model;

import java.util.Date;

/**
 * 
 * 标题：课程授权表
 * 
 * 作者：陈明凯 2013-5-20
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseCredit extends StudyCourse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7467442887257849836L;
	/**
	 * 授权年份
	 */
	private String creditYear;
	/**
	 * 课程ID 对应study_course中course_number字段
	 */
	private String courseId;
	/**
	 * 机构ID
	 */
	private String organId;
	
	/**
	 * 授权类型
	 */
	private String creditType;
	/**
	 * 课程类别
	 */
	private Integer courseType;
	
	//授权类型名(one or more)
	private String courseTypeNames;
	
	//授权类型Id(one or more)
	private String courseTypeIds;
	
	/**
	 * 学分数
	 */
	private Double creditNum;
	
	/**
	 * 学时数
	 */
	private Double creditHours;
	
	/**
	 * E元值
	 */
	//private Integer evpValue;
	/**
	 * 发证编号
	 */
	private String courseSerial;
	/**
	 * 培训要求
	 */
	private String request;
	/**
	 * 重学标记
	 */
	private Integer reStudyFlag;
	/**
	 * 授权日期
	 */
	private Date creditDate;

	/**
	 * 授权开始日期
	 */
	private Date startDate;

	/**
	 * 授权开始日期
	 */
	private Date endDate;

	/**
	 * 机构名称
	 */
	private String organName;
	/**
	 * 机构描述
	 */
	private String organDescription;

	/**
	 * 课程授权类型表
	 */
	private String courseTypeName;

	/**
	 * 授权级别名称
	 */
	private String creditName;
	
	/**
	 * 站点名
	 */
	private String domainName;

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getOrganDescription() {
		return organDescription;
	}

	public void setOrganDescription(String organDescription) {
		this.organDescription = organDescription;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreditYear() {
		return creditYear;
	}

	public void setCreditYear(String creditYear) {
		this.creditYear = creditYear;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public Double getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(Double creditNum) {
		this.creditNum = creditNum;
	}

	public String getCourseSerial() {
		return courseSerial;
	}

	public void setCourseSerial(String courseSerial) {
		this.courseSerial = courseSerial;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getReStudyFlag() {
		return reStudyFlag;
	}

	public void setReStudyFlag(Integer reStudyFlag) {
		this.reStudyFlag = reStudyFlag;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public Double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(Double creditHours) {
		this.creditHours = creditHours;
	}

	public String getCourseTypeNames() {
		return courseTypeNames;
	}

	public void setCourseTypeNames(String courseTypeNames) {
		this.courseTypeNames = courseTypeNames;
	}

	public String getCourseTypeIds() {
		return courseTypeIds;
	}

	public void setCourseTypeIds(String courseTypeIds) {
		this.courseTypeIds = courseTypeIds;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}
