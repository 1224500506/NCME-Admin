package com.hys.exam.model;

import java.io.Serializable;

public class Qualify implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long cv_set_id;
	
	private Long deli_man_id;
	
	private String opinion;
	
	private String opinion_type;
	
	private String real_name;

	public Long getCv_set_id() {
		return cv_set_id;
	}

	public void setCv_set_id(Long cv_set_id) {
		this.cv_set_id = cv_set_id;
	}

	public Long getDeli_man_id() {
		return deli_man_id;
	}

	public void setDeli_man_id(Long deli_man_id) {
		this.deli_man_id = deli_man_id;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOpinion_type() {
		return opinion_type;
	}

	public void setOpinion_type(String opinion_type) {
		this.opinion_type = opinion_type;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	


}
