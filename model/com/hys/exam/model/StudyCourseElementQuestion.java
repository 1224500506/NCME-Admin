package com.hys.exam.model;


/**
 * 
 * 标题：考试学习
 * 
 * 作者：张伟清 2013-01-24
 * 
 * 描述：课程组件试题
 * 
 * 说明:
 */
public class StudyCourseElementQuestion extends BaseObject {

	private static final long serialVersionUID = 728019580981644215L;

	/**
	 * 课程组件ID
	 */
	private Long studyCourseElementId ;

	/**
	 * 课程试题ID
	 */
	private Long studyCourseQuestionId ;
	
	/**
	 * 顺序
	 */
	private Integer seq ;

	public Long getStudyCourseElementId() {
		return studyCourseElementId;
	}

	public void setStudyCourseElementId(Long studyCourseElementId) {
		this.studyCourseElementId = studyCourseElementId;
	}

	public Long getStudyCourseQuestionId() {
		return studyCourseQuestionId;
	}

	public void setStudyCourseQuestionId(Long studyCourseQuestionId) {
		this.studyCourseQuestionId = studyCourseQuestionId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
