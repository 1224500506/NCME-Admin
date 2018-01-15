package com.hys.exam.model.system;

import java.util.Date;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-16
*/
@SuppressWarnings("serial")
public class SystemCardUser extends SystemCard{

	private Long userId;
	
	private Long cardId;
	
	private Long siteId;
	
	private Date bindDate;
	
	//CHY 临时存储
	private Date bindDate2;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Date getBindDate2() {
		return bindDate2;
	}

	public void setBindDate2(Date bindDate2) {
		this.bindDate2 = bindDate2;
	}
	
	
}


