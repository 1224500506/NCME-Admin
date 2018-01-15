package com.hys.exam.model;

import java.util.List;

/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：课程组件表
 * 
 * 说明:
 */
public class StudyCourseElement extends BaseObject {

	private static final long serialVersionUID = -3633033174810379045L;

	/**
	 * 主键ID
	 */
	private Long id ;

	/**
	 * 类别 1.单屏 2.3屏 3.flash 4.静态页面 11.课前练习 12.课中练习 13.课后练习
	 */
	private Integer courseElementType ;
	
	/**
	 * 课程ID
	 */
	private Long courseId ;
	
	/**
	 * 顺序
	 */
	private Long seq ;
	
	/**
	 * 状态 1.正常 -1.删除
	 */
	private Integer status ;
	
	/**
	 * 组件页面显示下标
	 */
	private Integer eleIndex ;
	
	/**
	 * 组件ID
	 */
	private Long eleWareId ;
	
	/**
	 * 课程组件课件
	 */
	private StudyCourseElementWare elementWare ;
	
	/**
	 * 课程组件课件列表
	 */
	private List<StudyCourseElementWare> wareList ;
	
	/**
	 * 课程组件试题
	 */
	private List<StudyCourseElementQuestion> questList ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCourseElementType() {
		return courseElementType;
	}

	public void setCourseElementType(Integer courseElementType) {
		this.courseElementType = courseElementType;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public StudyCourseElementWare getElementWare() {
		return elementWare;
	}

	public void setElementWare(StudyCourseElementWare elementWare) {
		this.elementWare = elementWare;
	}

	public List<StudyCourseElementQuestion> getQuestList() {
		return questList;
	}

	public void setQuestList(List<StudyCourseElementQuestion> questList) {
		this.questList = questList;
	}

	public Integer getEleIndex() {
		return eleIndex;
	}

	public void setEleIndex(Integer eleIndex) {
		this.eleIndex = eleIndex;
	}

	public List<StudyCourseElementWare> getWareList() {
		return wareList;
	}

	public void setWareList(List<StudyCourseElementWare> wareList) {
		this.wareList = wareList;
	}
	
	public Long getEleWareId() {
		return eleWareId;
	}

	public void setEleWareId(Long eleWareId) {
		this.eleWareId = eleWareId;
	}
}
