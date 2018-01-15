package com.hys.exam.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


public class CVSetAuthorQuery implements Serializable {
	
	private static final long serialVersionUID = -8343521518469184415L;

	private Long authorizationId;
	
	private Long cvSetId;
	
	private String cvSetName;//项目名称

	private List<UserImage> userImageList;
	
	private String serialNumber;
	
	private Double score;
	
	private Integer level;
	
	private Integer forma;//授课形式
	
	private String maker;
	
	private Integer costType;//0 免费，1 学习卡 ，2 收费(默认都为免费)
	
	private Date startDate;
	
	private Date endDate;
	
	private String sign;
	
	private Long status;
	
	private Integer authorStatus;//授权状态
	
	private List<SystemSite> siteList;
	
	private List<ExpertInfo> managerList;

	
	public Long getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(Long authorizationId) {
		this.authorizationId = authorizationId;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public String getCvSetName() {
		return cvSetName;
	}

	public void setCvSetName(String cvSetName) {
		this.cvSetName = cvSetName;
	}

	public List<UserImage> getUserImageList() {
		return userImageList;
	}

	public void setUserImageList(List<UserImage> userImageList) {
		this.userImageList = userImageList;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getForma() {
		return forma;
	}

	public void setForma(Integer forma) {
		this.forma = forma;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Integer getCostType() {
		return costType;
	}

	public void setCostType(Integer costType) {
		this.costType = costType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Integer getAuthorStatus() {
		return authorStatus;
	}

	public void setAuthorStatus(Integer authorStatus) {
		this.authorStatus = authorStatus;
	}

	public List<SystemSite> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SystemSite> siteList) {
		this.siteList = siteList;
	}

	public List<ExpertInfo> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<ExpertInfo> managerList) {
		this.managerList = managerList;
	}
	
}