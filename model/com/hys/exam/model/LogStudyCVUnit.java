package com.hys.exam.model;

import com.hys.exam.model.CVUnit;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 	Han
 * @time	2017-01-12
 * 
 */
public class LogStudyCVUnit extends CVUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7662294477457962357L;

	private Long id;
	
	// log ID
	private Long logStudyCVSetId;
	
	// 用户ID
	private Long systemUserId;
	
	// 课程ID
	private Long cvId;
	
	// 单元ID
	private Long cvUnitId;
	
	/**
	 * 单元完成 
	 * 1 ：学习中
	 * 2： 已完成
	 */
	private Integer status;
	
	//最后更新时间
	private Date lastUpdateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLogStudyCVSetId() {
		return logStudyCVSetId;
	}

	public void setLogStudyCVSetId(Long logStudyCVSetId) {
		this.logStudyCVSetId = logStudyCVSetId;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public Long getCvId() {
		return cvId;
	}

	public void setCvId(Long cvId) {
		this.cvId = cvId;
	}

	public Long getCvUnitId() {
		return cvUnitId;
	}

	public void setCvUnitId(Long cvUnitId) {
		this.cvUnitId = cvUnitId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	
}
