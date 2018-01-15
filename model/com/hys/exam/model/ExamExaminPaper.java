package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminPaper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7095402718569397505L;

	/**
	 * 考试ID
	 */
	private Long exam_id;
	
	/**
	 * 试卷ID
	 */
	private Long paper_id;
	
	/**
	 * 顺序
	 */
	private Integer seq;
	
	/**
	 * 试卷名
	 */
	private String paper_name;
	
	/**
	 * 出卷方式
	 */
	private Integer paper_mode;
	
	/**
	 * 子试卷个数
	 */
	private Integer child_paper_num;

	private Double paper_score;
	
	private Integer question_num;
	
	private String create_date;
	
	public Long getExam_id() {
		return exam_id;
	}

	public void setExam_id(Long examId) {
		exam_id = examId;
	}

	public Long getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(Long paperId) {
		paper_id = paperId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_name(String paperName) {
		paper_name = paperName;
	}

	public Integer getPaper_mode() {
		return paper_mode;
	}

	public void setPaper_mode(Integer paperMode) {
		paper_mode = paperMode;
	}

	public Integer getChild_paper_num() {
		return child_paper_num;
	}

	public void setChild_paper_num(Integer childPaperNum) {
		child_paper_num = childPaperNum;
	}

	public Double getPaper_score() {
		return paper_score;
	}

	public void setPaper_score(Double paper_score) {
		this.paper_score = paper_score;
	}

	public Integer getQuestion_num() {
		return question_num;
	}

	public void setQuestion_num(Integer question_num) {
		this.question_num = question_num;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	
}
