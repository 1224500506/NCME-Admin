package com.hys.exam.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * 标题：权限验证
 * 
 * 作者：陈明凯 2012-03-27
 * 
 * 描述：角色
 * 
 * 说明:
 */
public class SystemRole extends BaseObject {

	private static final long serialVersionUID = 2887995986460238087L;

	/**
	 * 角色ID
	 */
	private Long roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	private String roleNameDesc;
	
	private Integer status;

	/**
	 * 角色资源信息
	 */
	private List<SystemResource> resources = new ArrayList<SystemResource>();

	public List<SystemResource> getResources() {
		return resources;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setResources(List<SystemResource> resources) {
		this.resources = resources;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer state) {
		this.status = state;
	}

	public String getRoleNameDesc() {
		return roleNameDesc;
	}

	public void setRoleNameDesc(String roleNameDesc) {
		this.roleNameDesc = roleNameDesc;
	}
	
}
