package com.hys.exam.model;

import java.io.Serializable;

public class CaseCase  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pId;
	private Long diseaseId;
	private String createDate;
	private String onlineDate;
	private Integer state;
	private String deliOpinion;
	private String deliMan;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public Long getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(Long diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDeliOpinion() {
		return deliOpinion;
	}
	public void setDeliOpinion(String deliOpinion) {
		this.deliOpinion = deliOpinion;
	}
	public String getDeliMan() {
		return deliMan;
	}
	public void setDeliMan(String deliMan) {
		this.deliMan = deliMan;
	}
}
