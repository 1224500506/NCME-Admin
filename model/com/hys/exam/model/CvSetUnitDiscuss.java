package com.hys.exam.model;

import java.io.Serializable;

/**
 * *****************************************************************************
 *
 *               Copyright(c) 2017 by 伟宏互联网科技有限公司.
 *               
 *                       All rights reserved.
 *
 ******************************************************************************* 
 *     File Name         :   CvSetUnitDiscuss.java
 *     
 *     Description       :   病例讨论实体类
 * -----------------------------------------------------------------------------
 *     No.        Date              Revised by           Description
 *     0       2017-02-20                                    张建国	           Created
 ********************************************************************************
 */

public class CvSetUnitDiscuss implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//主键Id
	private Long id;
	
	//项目Id
	private Long cvSetId;
	
	//单元Id
	private Long cvUnitId;
	
	//讨论点Id
	private Long discussId;
	
	//病例Id
	private Long caseId;
	
	//讨论时间
	private String discussDate;
	
	//讨论内容
	private String discussContent;
	
	//讨论类型 0:单元讨论 1：病例讨论 2：主题讨论
	private int discussType;
	
	//评论用户id
	private Long systemUserId;
	
	//评论用户名称
	private String systemUserName;
	
	//评论用户头像
	private String userAvatar;
	
	//评论用户性别
	private Integer sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCvSetId() {
		return cvSetId;
	}

	public void setCvSetId(Long cvSetId) {
		this.cvSetId = cvSetId;
	}

	public Long getCvUnitId() {
		return cvUnitId;
	}

	public void setCvUnitId(Long cvUnitId) {
		this.cvUnitId = cvUnitId;
	}

	public Long getDiscussId() {
		return discussId;
	}

	public void setDiscussId(Long discussId) {
		this.discussId = discussId;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getDiscussDate() {
		return discussDate;
	}

	public void setDiscussDate(String discussDate) {
		this.discussDate = discussDate;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	public int getDiscussType() {
		return discussType;
	}

	public void setDiscussType(int discussType) {
		this.discussType = discussType;
	}

	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	public String getSystemUserName() {
		return systemUserName;
	}

	public void setSystemUserName(String systemUserName) {
		this.systemUserName = systemUserName;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

}
