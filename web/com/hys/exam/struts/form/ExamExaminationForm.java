package com.hys.exam.struts.form;

import com.hys.exam.model.ExamExamination;
import com.hys.framework.web.form.BaseForm;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 form
 * 
 * 说明:
 */
public class ExamExaminationForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 189625609171665011L;
	private ExamExamination examExamination = new ExamExamination();
	private String paperNamesId;
	private String stuNamesId;
	private String userNamesId;
	private String orgNamesId;
	public String getPaperNamesId() {
		return paperNamesId;
	}
	public void setPaperNamesId(String paperNamesId) {
		this.paperNamesId = paperNamesId;
	}
	public String getStuNamesId() {
		return stuNamesId;
	}
	public void setStuNamesId(String stuNamesId) {
		this.stuNamesId = stuNamesId;
	}
	public String getUserNamesId() {
		return userNamesId;
	}
	public void setUserNamesId(String userNamesId) {
		this.userNamesId = userNamesId;
	}
	public String getOrgNamesId() {
		return orgNamesId;
	}
	public void setOrgNamesId(String orgNamesId) {
		this.orgNamesId = orgNamesId;
	}
	public ExamExamination getExamExamination() {
		return examExamination;
	}
	public void setExamExamination(ExamExamination examExamination) {
		this.examExamination = examExamination;
	}
	
}
