package com.hys.exam.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：学习课件
 * 
 * 说明:
 */
public class StudyCourseware extends BaseObject {

	private static final long serialVersionUID = -6011244499451428278L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 课件代码
	 */
	private String code;

	/**
	 * 课件名称
	 */
	private String name;

	/**
	 * 课件类型 1.单屏课件 2.3屏课件 3.flash课件 4.静态页面(课件)
	 */
	private Integer type;

	/**
	 * 时长(分钟)
	 */
	private Integer times;

	/**
	 * 学时
	 */
	private Double classHours;

	/**
	 * 内容摘要
	 */
	private String summary;

	/**
	 * 关键字
	 */
	private String keyWord;

	/**
	 * 授课老师姓名
	 */
	private String teacherName;

	/**
	 * 授课老师职称
	 */
	private String teacherTitle;

	/**
	 * 授课老师单位
	 */
	private String teacherUnit;

	/**
	 * 制作年份
	 */
	private String makeYear;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 课件意义
	 */
	private String meaning;

	/**
	 * 课程收获
	 */
	private String gains;

	/**
	 * 课程注意事项
	 */
	private String attentions;

	/**
	 * 课件地址
	 */
	private String path;
	
	//备用地址
	private String asPath;

	/**
	 * 课件状态
	 */
	private Integer status;

	/**
	 * 文件地址
	 */
	private String filePath;

	/**
	 * 发布地址
	 */
	private String publishPath;

	/**
	 * 是否发布
	 */
	private Integer isPublish;

	/**
	 * 用于上传附件的映射地址
	 */
	private String relativeAddress;

	/**
	 * 课程主键ID
	 */
	private Long courseId;

	/**
	 * 课程组件分类 1.单屏 2.3屏 3.flash 4.静态页面 11.课前练习 12.课中练习 13.课后练习
	 */
	private Integer courseElementType;
	
	/**
	 * 课程组件ID
	 */
	private Long courseElementId ;
	
	/**
	 * 所属课程的序列
	 */
	private Integer seq;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdateDate;

	/**
	 * 最后更新用户
	 */
	private Long lastUpdateUserId;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 创建用户
	 */
	private Long createUserId;

	/**
	 * 行业
	 */
	private List<ExamPropVal> industryList;
	/**
	 * 适用对象
	 */
	private List<ExamPropVal> applicableList;
	/**
	 * 知识分类
	 */
	private List<ExamPropVal> knowList;

	public List<ExamPropVal> getIndustryList() {
		return industryList;
	}

	public void setIndustryList(List<ExamPropVal> industryList) {
		this.industryList = industryList;
	}

	public List<ExamPropVal> getApplicableList() {
		return applicableList;
	}

	public void setApplicableList(List<ExamPropVal> applicableList) {
		this.applicableList = applicableList;
	}

	public List<ExamPropVal> getKnowList() {
		return knowList;
	}

	public void setKnowList(List<ExamPropVal> knowList) {
		this.knowList = knowList;
	}

	public Long getCourseElementId() {
		return courseElementId;
	}

	public void setCourseElementId(Long courseElementId) {
		this.courseElementId = courseElementId;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Double getClassHours() {
		return classHours;
	}

	public void setClassHours(Double classHours) {
		this.classHours = classHours;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public String getTeacherUnit() {
		return teacherUnit;
	}

	public void setTeacherUnit(String teacherUnit) {
		this.teacherUnit = teacherUnit;
	}

	public String getMakeYear() {
		return makeYear;
	}

	public void setMakeYear(String makeYear) {
		this.makeYear = makeYear;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public String getGains() {
		return gains;
	}

	public void setGains(String gains) {
		this.gains = gains;
	}

	public String getAttentions() {
		return attentions;
	}

	public void setAttentions(String attentions) {
		this.attentions = attentions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getPublishPath() {
		return publishPath;
	}

	public void setPublishPath(String publishPath) {
		this.publishPath = publishPath;
	}

	public Integer getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	public String getRelativeAddress() {
		return relativeAddress;
	}

	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseElementType() {
		return courseElementType;
	}

	public void setCourseElementType(Integer courseElementType) {
		this.courseElementType = courseElementType;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getAsPath() {
		return asPath;
	}

	public void setAsPath(String asPath) {
		this.asPath = asPath;
	}
}