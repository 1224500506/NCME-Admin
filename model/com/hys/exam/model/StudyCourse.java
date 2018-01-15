package com.hys.exam.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：课程表
 * 
 * 说明:
 */
public class StudyCourse extends BaseObject {

	private static final long serialVersionUID = 4937226001544566407L;

	/**
	 * 主键ID
	 */
	private Long id ;
	
	/**
	 * 课程名称
	 */
	private String studyCourseName ;
	
	/**
	 * 主讲老师
	 */
	private String teacher ;
	
	/**
	 * 关键字
	 */
	private String keyWord ;
	
	/**
	 * 类型 1.单屏 2.3屏 3.flash 4.静态页面 5.混合
	 */
	private Integer studyCourseType ;
	
	/**
	 * 时长
	 */
	private Double times ;
	
	/**
	 * 学时
	 */
	private Double classHours ;
	
	/**
	 * 摘要
	 */
	private String summary ;
	
	/**
	 * 简述
	 */
	private String description ;
	
	/**
	 * 导读
	 */
	private String review ;
	
	/**
	 * 状态
	 */
	private Integer status ;
	
	/**
	 * 页面显示-课件数量
	 */
	private Integer wareNumber ;
	
	/**
	 * 页面显示-试题数量
	 */
	private Integer quesNumber ;
	
	/**
	 * 课程分类ID
	 */
	private Long studyCourseTypeId ;
	
	/**
	 * 课程分类名称
	 */
	private String courseTypeName ;
	
	/**
	 * 是否允许查看读取
	 */
	private Integer isReadLook ;
	
	/**
	 * 用户ID
	 */
	private Long userId ;
	
	/**
	 * 站点ID
	 */
	private Long siteId ;
	
	/**
	 * 课程学习状态 1.学习中 2.已学习
	 */
	private Integer state ;
	
	/**
	 * 课程中的练习数量
	 */
	private Integer coursePractice ;
	
	/**
	 * 课程中的知识点
	 */
	private Integer courseKnowledge ;
	
	/**
	 * 课程下的课程组件信息
	 */
	private List<StudyCourseElement> eleList ;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 课程编号年份加上8位ID
	 */
	private String courseNumber;

	/**
	 * 发布时间
	 */
	private Date pubDate;

	/**
	 * 难度 1 -初 2 -中 3 -高
	 */
	private Integer difficulty;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 本年度点击量
	 */
	private Long clickTimes;

	/**
	 * 历年总点击量
	 * 
	 */
	private Long totalClickTimes;
	
	/**
	 * 课程图片地址
	 */
	private String courseImgPath;

	public String getCourseImgPath() {
		return courseImgPath;
	}

	public void setCourseImgPath(String courseImgPath) {
		this.courseImgPath = courseImgPath;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Long clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Long getTotalClickTimes() {
		return totalClickTimes;
	}

	public void setTotalClickTimes(Long totalClickTimes) {
		this.totalClickTimes = totalClickTimes;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Double getTimes() {
		return times;
	}

	public void setTimes(Double times) {
		this.times = times;
	}

	public Double getClassHours() {
		return classHours;
	}

	public void setClassHours(Double classHours) {
		this.classHours = classHours;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudyCourseName() {
		return studyCourseName;
	}

	public void setStudyCourseName(String studyCourseName) {
		this.studyCourseName = studyCourseName;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getStudyCourseType() {
		return studyCourseType;
	}

	public void setStudyCourseType(Integer studyCourseType) {
		this.studyCourseType = studyCourseType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getStudyCourseTypeId() {
		return studyCourseTypeId;
	}

	public void setStudyCourseTypeId(Long studyCourseTypeId) {
		this.studyCourseTypeId = studyCourseTypeId;
	}

	public List<StudyCourseElement> getEleList() {
		return eleList;
	}

	public void setEleList(List<StudyCourseElement> eleList) {
		this.eleList = eleList;
	}

	public Integer getWareNumber() {
		return wareNumber;
	}

	public void setWareNumber(Integer wareNumber) {
		this.wareNumber = wareNumber;
	}

	public Integer getQuesNumber() {
		return quesNumber;
	}

	public void setQuesNumber(Integer quesNumber) {
		this.quesNumber = quesNumber;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public Integer getIsReadLook() {
		return isReadLook;
	}

	public void setIsReadLook(Integer isReadLook) {
		this.isReadLook = isReadLook;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCoursePractice() {
		return coursePractice;
	}

	public void setCoursePractice(Integer coursePractice) {
		this.coursePractice = coursePractice;
	}

	public Integer getCourseKnowledge() {
		return courseKnowledge;
	}

	public void setCourseKnowledge(Integer courseKnowledge) {
		this.courseKnowledge = courseKnowledge;
	}
}