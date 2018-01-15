package com.hys.exam.struts.form;

import org.apache.struts.upload.FormFile;

import com.hys.framework.web.form.BaseForm;

public class CVS_Form extends BaseForm {

	/**
	 * Ma
	 */
	private static final long serialVersionUID = 1L;
	
	private FormFile matFile;
	
	private String name;
	
	private Integer forma;
	
	private String userImage;
	
	private String code;
	
	private String manager;
	
	private String lessonTeacher;
	
	private String generalTeacher;
	
	private String introduction;
	
	private String cover; 
	
	private String announcement;
	
	private String knowledge_needed;
	
	private String knowledge_base;
	
	private String reference;
	
	private String reference_lately;
	
	private Integer cv_set_type ; //YHQ，2017-06-04，0继教项目，1乡医培训（默认为0，老的项目都是0）
	private Integer cost_type ;//0 免费，1 学习卡 ，2 收费(默认都为免费)--tl
	
	

	public Integer getCv_set_type() {
		return cv_set_type;
	}
	public void setCv_set_type(Integer cv_set_type) {
		this.cv_set_type = cv_set_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getForma() {
		return forma;
	}

	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLessonTeacher() {
		return lessonTeacher;
	}

	public void setLessonTeacher(String lessonTeacher) {
		this.lessonTeacher = lessonTeacher;
	}

	public String getGeneralTeacher() {
		return generalTeacher;
	}

	public void setGeneralTeacher(String generalTeacher) {
		this.generalTeacher = generalTeacher;
	}

	public String getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getKnowledge_needed() {
		return knowledge_needed;
	}

	public void setKnowledge_needed(String knowledge_needed) {
		this.knowledge_needed = knowledge_needed;
	}

	public String getKnowledge_base() {
		return knowledge_base;
	}

	public void setKnowledge_base(String knowledge_base) {
		this.knowledge_base = knowledge_base;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReference_lately() {
		return reference_lately;
	}

	public void setReference_lately(String reference_lately) {
		this.reference_lately = reference_lately;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public FormFile getMatFile() {
		return matFile;
	}

	public void setMatFile(FormFile matFile) {
		this.matFile = matFile;
	}
	public Integer getCost_type() {
		return cost_type;
	}
	public void setCost_type(Integer cost_type) {
		this.cost_type = cost_type;
	}

}
