package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试日志实体类
 * 
 * 作者：张建国 2017-02-22
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamLog implements Serializable {

	private static final long serialVersionUID = -9004882208055943890L;

	//主键Id
	private Long id;
	
	//用户Id
	private Long userId;
	
	//系统Id
	private Long subSystemId;
	
	//开始时间
	private String startTime;
	
	//结束时间
	private String endTime;
	
	//最后答题时间
	private String lastTime;
	
	//使用时间
	private Long useTime;
	
	//状态 1：已提交  2：已评卷  3：暂停中  4：考试中  5：评卷中  6：已发布 目前未使用 7:强行收卷 
	private int state;
	
	//正确率
	private int rightRate;
	
	//得分
	private Double result;
	
	//是否通过
	private int isnotPass;
	
	//用户Ip
	private String ip;
	
	//评卷人Id
	private Long checkUserId;
	
	//考试名称
	private String examName;
	
    //考试分类
	private Long examTypeId;
	
	//考试子分类
	private int examSubTypeId;
	
	//考试Id
	private Long examinationId;
	
	//考试类别
	private int examType;
	
	//考试类别一
	private int examType1;
	
	//考试级别
	private int examLevel;
	
	//试卷Id
	private Long testpaperId;
	
	//评卷类型 1自动 2手工
	private int correctingMode;
	
	//是否盲判
	private int isnotDecide;
	
	//是否锁定
	private int isLock;
	
	
	/*
	//课程Id
	private int studyCourseId;
	
	//课程补充信息
	private int stydySourseElement;
	
	*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSubSystemId() {
		return subSystemId;
	}

	public void setSubSystemId(Long subSystemId) {
		this.subSystemId = subSystemId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public Long getUseTime() {
		return useTime;
	}

	public void setUseTime(Long useTime) {
		this.useTime = useTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getRightRate() {
		return rightRate;
	}

	public void setRightRate(int rightRate) {
		this.rightRate = rightRate;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public int getIsnotPass() {
		return isnotPass;
	}

	public void setIsnotPass(int isnotPass) {
		this.isnotPass = isnotPass;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Long getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(Long examTypeId) {
		this.examTypeId = examTypeId;
	}

	public int getExamSubTypeId() {
		return examSubTypeId;
	}

	public void setExamSubTypeId(int examSubTypeId) {
		this.examSubTypeId = examSubTypeId;
	}

	public Long getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(Long examinationId) {
		this.examinationId = examinationId;
	}

	public int getExamType() {
		return examType;
	}

	public void setExamType(int examType) {
		this.examType = examType;
	}

	public int getExamType1() {
		return examType1;
	}

	public void setExamType1(int examType1) {
		this.examType1 = examType1;
	}

	public int getExamLevel() {
		return examLevel;
	}

	public void setExamLevel(int examLevel) {
		this.examLevel = examLevel;
	}

	public Long getTestpaperId() {
		return testpaperId;
	}

	public void setTestpaperId(Long testpaperId) {
		this.testpaperId = testpaperId;
	}

	public int getCorrectingMode() {
		return correctingMode;
	}

	public void setCorrectingMode(int correctingMode) {
		this.correctingMode = correctingMode;
	}

	public int getIsnotDecide() {
		return isnotDecide;
	}

	public void setIsnotDecide(int isnotDecide) {
		this.isnotDecide = isnotDecide;
	}

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	/*

	public int getStudyCourseId() {
		return studyCourseId;
	}

	public void setStudyCourseId(int studyCourseId) {
		this.studyCourseId = studyCourseId;
	}

	public int getStydySourseElement() {
		return stydySourseElement;
	}

	public void setStydySourseElement(int stydySourseElement) {
		this.stydySourseElement = stydySourseElement;
	}

    */
}