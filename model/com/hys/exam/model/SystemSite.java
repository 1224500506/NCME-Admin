package com.hys.exam.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：张伟清 2013-02-25
 * 
 * 描述：站点信息
 * 
 * 说明:
 */
public class SystemSite extends BaseObject {

	@Override
	public String toString() {
		return "SystemSite [id=" + id + ", domainName=" + domainName + ", remark=" + remark + ", clientName="
				+ clientName + ", clientId=" + clientId + ", applicationName=" + applicationName + ", applicationId="
				+ applicationId + ", status=" + status + ", roleId=" + roleId + ", courseTypeIds=" + courseTypeIds
				+ ", courseTypeNames=" + courseTypeNames + ", aliasName=" + aliasName + ", siteCourseTypes="
				+ siteCourseTypes + "]";
	}

	private static final long serialVersionUID = -6176689592278935723L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 域名
	 */
	private String domainName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 客户名称_VO
	 */
	private String clientName;
	
	/**
	 * 客户ID
	 */
	private Long clientId;

	/**
	 * 应用名称_VO
	 */
	private String applicationName;
	/**
	 * 应用ID
	 */
	private Long applicationId;

	/**
	 * 站点状态 1 -正常 -1 -删除 -2 -禁用
	 */
	private Integer status;

	/**
	 * 学员注册角色ID
	 */
	private Long roleId;
	
	/**
	 * 站点课程分类IDs
	 */
	private String courseTypeIds;
	
	/**
	 * 站点课程分类NAMEs
	 */
	private String courseTypeNames;
	
	private String aliasName;
	
	/**
	 * 站点课程分类明细
	 */
	private List<SystemSiteCourseType> siteCourseTypes = new ArrayList<SystemSiteCourseType>();
	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public List<SystemSiteCourseType> getSiteCourseTypes() {
		return siteCourseTypes;
	}

	public void setSiteCourseTypes(List<SystemSiteCourseType> siteCourseTypes) {
		this.siteCourseTypes = siteCourseTypes;
	}

	public String getCourseTypeIds() {
		return courseTypeIds;
	}

	public void setCourseTypeIds(String courseTypeIds) {
		this.courseTypeIds = courseTypeIds;
	}

	public String getCourseTypeNames() {
		return courseTypeNames;
	}

	public void setCourseTypeNames(String courseTypeNames) {
		this.courseTypeNames = courseTypeNames;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}
