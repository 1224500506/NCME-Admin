package com.hys.exam.model;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 *******************************************************************************
 *     File Name         :   LogStudyCVSet.java
 *     
 *     Description       :   学习记录实体类(项目)
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-01-10                                   张建国	               Created
 ********************************************************************************
 */

public class LogStudyCVSet {

	private Long id;
	
	//用户ID
	private Long systemUserId;
	
	//站点ID
	private Long siteId;
	
	//项目ID
	private Long cvSetId;
	
	//所有单元完成 1 -学习中 2 -已完成
	private int state;
	
	//1 -未申请 2 -已申请
	private int isApplyCredit;
	
	//上次学习时间
	private String lastUpdateDate;
	
	//本次学习时间
	private String applyDate;
	
	private Integer logCVSetStatus;//项目的学习记录状态：0.未学习、1.学习中、2.已完成
	
	private Integer cvSetStatus;//该项目状态 例如：3.审核合格 5.已发布 6.已下线

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getIsApplyCredit() {
		return isApplyCredit;
	}

	public void setIsApplyCredit(int isApplyCredit) {
		this.isApplyCredit = isApplyCredit;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getLogCVSetStatus() {
		return logCVSetStatus;
	}

	public void setLogCVSetStatus(Integer logCVSetStatus) {
		this.logCVSetStatus = logCVSetStatus;
	}

	public Integer getCvSetStatus() {
		return cvSetStatus;
	}

	public void setCvSetStatus(Integer cvSetStatus) {
		this.cvSetStatus = cvSetStatus;
	}

}
