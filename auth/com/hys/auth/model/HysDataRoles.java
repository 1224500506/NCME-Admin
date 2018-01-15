package com.hys.auth.model;

import java.util.List;

import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * 标题：角色
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class HysDataRoles extends AbstractObject {

	private static final long serialVersionUID = 1L;
	
	private Integer roleId;
	private List<String> propAuth;
	private List<String> questAuth;
	private List<String> matiAuth;
	private List<String> expertAuth;
	private List<String> bingliAuth;
	private List<String> systemAuth;

	public HysDataRoles() {
	}

	public HysDataRoles(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<String> getPropAuth() {
		return propAuth;
	}

	public void setPropAuth(List<String> propAuth) {
		this.propAuth = propAuth;
	}
	
	public List<String> getMatiAuth() {
		return matiAuth;
	}

	public void setMatiAuth(List<String> matiAuth) {
		this.matiAuth = matiAuth;
	}
	
	public List<String> getQuestAuth() {
		return questAuth;
	}

	public void setQuestAuth(List<String> questAuth) {
		this.questAuth = questAuth;
	}
	
	public List<String> getExpertAuth() {
		return expertAuth;
	}

	public void setExpertAuth(List<String> expertAuth) {
		this.expertAuth = expertAuth;
	}
	
	public List<String> getBingliAuth() {
		return bingliAuth;
	}

	public void setBingliAuth(List<String> bingliAuth) {
		this.bingliAuth = bingliAuth;
	}
	
	public List<String> getSystemAuth() {
		return systemAuth;
	}

	public void setSystemAuth(List<String> systemAuth) {
		this.systemAuth = systemAuth;
	}
	
}