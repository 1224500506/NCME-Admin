package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.exam.common.util.DateUtil;

/**
 * 专家委员会届期信息类
 * @author Han
 *
 */
public class ExpertGroupTerm  implements Serializable {

	private static final long serialVersionUID = -1217567995666788022L;
	
	//ID
	private Long id;
	//委员会Id
	private Long groupId;
	//委员会名称
	private String groupName;
	//开始时间
	private Date startDate;
	//截止时间
	private Date endDate;
	//开始时间字符串
	private String startDateStr = "";
	//截止时间字符串
	private String endDateStr = "";
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		startDateStr = DateUtil.format(startDate, "yyyy-MM-dd");
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		endDateStr = DateUtil.format(endDate, "yyyy-MM-dd");
	}
	
	
}
