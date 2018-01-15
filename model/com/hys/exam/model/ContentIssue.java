package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.exam.common.util.DateUtil;

public class ContentIssue  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer type;
	
	private String title;
	
	private String startDate;
	
	private String endDate;
	
	private Date startDateObj;

	private Date endDateObj;
	
	private Integer orderNum;
	
	private String content;
	
	private Long siteId;
	
	private Long[] siteIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
		startDateObj = DateUtil.parse(startDate, "yyyy-MM-dd");
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
		endDateObj = DateUtil.parse(endDate, "yyyy-MM-dd");
	}
	
	public Date getStartDateObj() {
		return startDateObj;
	}

	public Date getEndDateObj() {
		return endDateObj;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Long[] getSiteIds() {
		return siteIds;
	}

	public void setSiteIds(Long[] siteIds) {
		this.siteIds = siteIds;
	}
	
	
}
