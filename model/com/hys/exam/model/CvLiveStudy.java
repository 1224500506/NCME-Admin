package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CvLiveStudy implements Serializable {

	private static final long serialVersionUID = -4329091807921776017L;
	
	private Long id;
	private Long uid;//学生ID 如果进入直播没有传值，则展示互动生成
	private String nickname;//账户名称
	private String mobile;
	private Long joinTime;//加入直播时间
	private Long leaveTime;//离开直播时间
	private String ip;
	private Integer device;//设备类型 设备类型 0  PC 客户端  1  PC Web 端  2  PC Web 端
	private String company;
	private String area;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Long joinTime) {
		this.joinTime = joinTime;
	}
	public Long getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Long leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getDevice() {
		return device;
	}
	public void setDevice(Integer device) {
		this.device = device;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
