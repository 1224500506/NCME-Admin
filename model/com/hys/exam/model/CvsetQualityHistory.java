package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

public class CvsetQualityHistory implements Serializable {

	/**
	 * chenlb add
	 * 20161231 
	 * 项目审核历史表
	 * 
	 */
	private static final long serialVersionUID = -5172346305325905061L;

	/**
	 * 项目id
	 */
	private Long cvSetId;
	
	/**
	 * 专家Id
	 */
	private Long expertId;
	
	/**
	 * 审核状态  3：审核通过   4：审核不通过		//与cvset保持一致
	 */
	private Integer qualifyStatus;			
	
	/**
	 * 其他原因
	 */
	private String opinion;
	
	/**
	 * 选择未通过原因, 多个, 用 ^ 分割
	 */
	private String opinionType;
	
	
	private Date createDate;
	
	
	private int status;
	
	//专家名称
	private String expertName;


	public Long getCvSetId() {
		return cvSetId;
	}


	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}


	public Long getExpertId() {
		return expertId;
	}


	public void setExpertId(Long expertId) {
		this.expertId = expertId;
	}


	public Integer getQualifyStatus() {
		return qualifyStatus;
	}


	public void setQualifyStatus(Integer qualifyStatus) {
		this.qualifyStatus = qualifyStatus;
	}


	public String getOpinion() {
		return opinion;
	}


	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	public String getOpinionType() {
		return opinionType;
	}


	public void setOpinionType(String opinionType) {
		this.opinionType = opinionType;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}		
	
}
