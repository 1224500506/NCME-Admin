package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jul 27, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionImportTemplate implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4940759584831630996L;

	private Long id;

	private Long parent_id;

	private Integer questionLabel;

	private String content;

	private String answerA;

	private String answerB;

	private String answerC;

	private String answerD;

	private String answerE;

	private String answerF;

	private String answer;

	private String analyse;

	private String grade;

	private String differ;

	private String source;

	private String author;
	private String type;
	private String point2;
	private String cognize;
	private String title;
	
	private String point;
	
	private String isnot_multimedia;
	
	/**
	 * 0：原题
	 * 1:已存在试题
	 */
	private int status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParent_id() {
		return parent_id;
	}
	public void setParent_id(Long parentId) {
		parent_id = parentId;
	}
	public Integer getQuestionLabel() {
		return questionLabel;
	}
	public void setQuestionLabel(Integer questionLabel) {
		this.questionLabel = questionLabel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getAnswerE() {
		return answerE;
	}
	public void setAnswerE(String answerE) {
		this.answerE = answerE;
	}
	public String getAnswerF() {
		return answerF;
	}
	public void setAnswerF(String answerF) {
		this.answerF = answerF;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalyse() {
		return analyse;
	}
	public void setAnalyse(String analyse) {
		this.analyse = analyse;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDiffer() {
		return differ;
	}
	public void setDiffer(String differ) {
		this.differ = differ;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPoint2() {
		return point2;
	}
	public void setPoint2(String point2) {
		this.point2 = point2;
	}
	public String getCognize() {
		return cognize;
	}
	public void setCognize(String cognize) {
		this.cognize = cognize;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getIsnot_multimedia() {
		return isnot_multimedia;
	}
	public void setIsnot_multimedia(String isnotMultimedia) {
		isnot_multimedia = isnotMultimedia;
	}
	
	
	
}
