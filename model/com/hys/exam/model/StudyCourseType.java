package com.hys.exam.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：课程分类表
 * 
 * 说明:
 */
public class StudyCourseType extends BaseObject {

	private static final long serialVersionUID = -5612308194881797122L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 名称
	 */
	private String courseTypeName;

	/**
	 * 父分类ID
	 */
	private Long parentCourseTypeId;

	/**
	 * 顺序
	 */
	private Integer seq;

	/**
	 * 总学时
	 */
	private Integer allClassHours;

	/**
	 * 类型 1.培训/课程级 2.分类级
	 */
	private Integer type;

	/**
	 * 考核要求
	 */
	private String examineRequire;

	/**
	 * 培训大纲
	 */
	private String trainPrinciples;

	/**
	 * 介绍
	 */
	private String introduction;

	/**
	 * 状态 1.正常 -1.删除
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 最后修改时间
	 */
	private Date lastUpdateDate;

	/**
	 * 是否最末级 0.不是 1.是
	 */
	private Integer isLastLevel;

	/**
	 * 分类下课程数
	 */
	private Integer studyCourse;

	/**
	 * 课程分类列表
	 */
	private List<StudyCourseType> typeList;

	/**
	 * 课程列表
	 */
	private List<StudyCourse> curList;

	/**
	 * 创建用户ID
	 */
	private Long createUserId;

	/**
	 * 最后更新用户ID
	 */
	private Long lastUpdateUserId;
	
	/**
	 * 上、下移动 重新初始化SEQ 信息
	 */
	private Integer upOrDownSeq ;
	
	/**
	 * 是否免费 1.免费 0.非免费
	 */
	private Integer isFree ;

	/**
	 * 三级学科I D
	 */
	private String subjectId;
	/**
	 * 三级学科名称
	 */
	private String subjectName;
	/**
	 * 二级学科代码
	 */
	private String subject2Id;
	/**
	 * 二级学科名称
	 */
	private String subject2Name;
	/**
	 * 学习建议
	 */
	private String guide;
	/**
	 * 推荐专家ID
	 */
	private String expId;
	/**
	 * 关键点导读
	 */
	private String keyGuide;
	/**
	 * 虚拟实践导读
	 */
	private String pracGuide;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubject2Id() {
		return subject2Id;
	}

	public void setSubject2Id(String subject2Id) {
		this.subject2Id = subject2Id;
	}

	public String getSubject2Name() {
		return subject2Name;
	}

	public void setSubject2Name(String subject2Name) {
		this.subject2Name = subject2Name;
	}

	public String getGuide() {
		return guide;
	}

	public void setGuide(String guide) {
		this.guide = guide;
	}

	public String getExpId() {
		return expId;
	}

	public void setExpId(String expId) {
		this.expId = expId;
	}

	public String getKeyGuide() {
		return keyGuide;
	}

	public void setKeyGuide(String keyGuide) {
		this.keyGuide = keyGuide;
	}

	public String getPracGuide() {
		return pracGuide;
	}

	public void setPracGuide(String pracGuide) {
		this.pracGuide = pracGuide;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public Integer getStudyCourse() {
		return studyCourse;
	}

	public void setStudyCourse(Integer studyCourse) {
		this.studyCourse = studyCourse;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseTypeName() {
		return courseTypeName;
	}

	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
	}

	public Long getParentCourseTypeId() {
		return parentCourseTypeId;
	}

	public void setParentCourseTypeId(Long parentCourseTypeId) {
		this.parentCourseTypeId = parentCourseTypeId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getAllClassHours() {
		return allClassHours;
	}

	public void setAllClassHours(Integer allClassHours) {
		this.allClassHours = allClassHours;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getExamineRequire() {
		return examineRequire;
	}

	public void setExamineRequire(String examineRequire) {
		this.examineRequire = examineRequire;
	}

	public String getTrainPrinciples() {
		return trainPrinciples;
	}

	public void setTrainPrinciples(String trainPrinciples) {
		this.trainPrinciples = trainPrinciples;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public List<StudyCourseType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<StudyCourseType> typeList) {
		this.typeList = typeList;
	}

	public List<StudyCourse> getCurList() {
		return curList;
	}

	public void setCurList(List<StudyCourse> curList) {
		this.curList = curList;
	}

	public Integer getIsLastLevel() {
		return isLastLevel;
	}

	public void setIsLastLevel(Integer isLastLevel) {
		this.isLastLevel = isLastLevel;
	}

	public Integer getUpOrDownSeq() {
		return upOrDownSeq;
	}

	public void setUpOrDownSeq(Integer upOrDownSeq) {
		this.upOrDownSeq = upOrDownSeq;
	}

	public Integer getIsFree() {
		return isFree;
	}

	public void setIsFree(Integer isFree) {
		this.isFree = isFree;
	}
}
