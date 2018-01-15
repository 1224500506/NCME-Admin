package com.hys.exam.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 model
 * 
 * 说明:
 */
public class SystemAccount extends BaseObject {

	private static final long serialVersionUID = -5471491937369200094L;

	/**
	 * 账户id
	 */
	private Long accountId;

	/**
	 * 账户名
	 */
	private String accountName;

	/**
	 * 账户密码
	 */
	private String accountPassword;

	/**
	 * 账户备注
	 */
	private String accountRemark;

	/**
	 * 资源ID
	 */
	private String resourceId;
	
	private Integer account_status;

	public Integer getAccount_status() {
		return account_status;
	}

	public void setAccount_status(Integer account_status) {
		this.account_status = account_status;
	}

	/**
	 * 人员角色列表
	 */
	private List<SystemRole> roleList = new ArrayList<SystemRole>();
	
	/**
	 * 人员资源列表 
	 */
	private List<SystemResource> resourceList = new ArrayList<SystemResource>();
	
	/**
	 * 资源地址列表
	 */
	private List<String> resourceUrlList = new ArrayList<String>();
	
	/**
	 * 人员认证信息
	 */
	private Map<String, String> accountPropertyMap = new HashMap<String, String>();

	public SystemAccount(Long accountId, String accountName, String accountPassword, String accountRemark ) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.accountRemark = accountRemark;
	}

	public SystemAccount(){}
	
	public String getAccountName() {
		return accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public String getAccountRemark() {
		return accountRemark;
	}

	public List<SystemResource> getResourceList() {
		return resourceList;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Map<String, String> getAccountPropertyMap() {
		return accountPropertyMap;
	}

	public List<String> getResourceUrlList() {
		return resourceUrlList;
	}

	public List<SystemRole> getRoleList() {
		return roleList;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public void setAccountRemark(String accountRemark) {
		this.accountRemark = accountRemark;
	}

	public void setResourceList(List<SystemResource> resourceList) {
		this.resourceList = resourceList;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public void setAccountPropertyMap(Map<String, String> accountPropertyMap) {
		this.accountPropertyMap = accountPropertyMap;
	}

	public void setResourceUrlList(List<String> resourceUrlList) {
		this.resourceUrlList = resourceUrlList;
	}

	public void setRoleList(List<SystemRole> roleList) {
		this.roleList = roleList;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getAccountProperty(String accountPropertyKey) {
		return accountPropertyMap.get(accountPropertyKey);
	}
}
