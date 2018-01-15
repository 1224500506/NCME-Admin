package com.hys.exam.model;

import java.util.Date;

/**
 * 
 * 标题：用户信息
 * 
 * 作者：张伟清 2013-1-24
 * 
 * 描述：组织机构
 * 
 * 说明:
 */
public class SystemOrg extends BaseObject {

	private static final long serialVersionUID = -5460208602315874183L;

	/**
	 * 主键ID
	 */
	private Long id ;
	
	/**
	 * 机构类别ID
	 */
	private Long orgTypeId ;
	
	/**
	 * 父组织机构ID
	 */
	private Long parentOrgId ;
	
	/**
	 * 行政上级机构ID
	 */
	private Long adminParentOrgId ;
	
	/**
	 * 机构编码
	 */
	private String orgCode ;
	
	/**
	 * 机构名称
	 */
	private String orgName ;
	
	/**
	 * 状态 1.正常 -1.删除
	 */
	private Integer status ;
	
	/**
	 * 组织机构标识 100.全国 200.省厅 300.市局 400.区县 500.乡镇 600.学会 601.大学 602.管理机构 700.虚拟机构 800.医院	
	 */
	private Long orgTypeFlag ;
	
	/**
	 * 别名
	 */
	private String aliasName ;
	
	/**
	 * 顺序
	 */
	private Integer orgSeq ;
	
	/**
	 * 顺序2
	 */
	private Integer orgSeq2 ;

	/**
	 * 最后修改时间
	 */
	private Date lastUpdateDate ;
	
	/**
	 * 机构缩写
	 */
	private String orgAbbr ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Long orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Long getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public Long getAdminParentOrgId() {
		return adminParentOrgId;
	}

	public void setAdminParentOrgId(Long adminParentOrgId) {
		this.adminParentOrgId = adminParentOrgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOrgTypeFlag() {
		return orgTypeFlag;
	}

	public void setOrgTypeFlag(Long orgTypeFlag) {
		this.orgTypeFlag = orgTypeFlag;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Integer getOrgSeq() {
		return orgSeq;
	}

	public void setOrgSeq(Integer orgSeq) {
		this.orgSeq = orgSeq;
	}

	public Integer getOrgSeq2() {
		return orgSeq2;
	}

	public void setOrgSeq2(Integer orgSeq2) {
		this.orgSeq2 = orgSeq2;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getOrgAbbr() {
		return orgAbbr;
	}

	public void setOrgAbbr(String orgAbbr) {
		this.orgAbbr = orgAbbr;
	}
}
