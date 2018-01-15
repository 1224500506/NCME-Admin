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
public class HysDataList{

	private static final long serialVersionUID = 1L;

	private Integer manage_Kind;
	private String control_auth;
	private String statistics_auth;
	private String variety;

	public HysDataList() {
	}
	
	public Integer getManageKind() {
		return this.manage_Kind;
	}

	public void setManageKind(Integer manage_Kind) {
		this.manage_Kind = manage_Kind;
	}

	public String getControlAuth() {
		return this.control_auth;
	}

	public void setControlAuth(String control_auth) {
		this.control_auth = control_auth;
	}	
	
	public String getStatisticsAuth() {
		return this.statistics_auth;
	}

	public void setStatisticsAuth(String statistics_auth) {
		this.statistics_auth = statistics_auth;
	}
	
	public String getVariety() {
		return this.variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}
	
}