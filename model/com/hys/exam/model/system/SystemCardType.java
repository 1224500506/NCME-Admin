package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;

/**
*
* <p>Description: 卡类型</p>
* @author chenlaibin
* @version 1.0 2013-12-14
*/
@SuppressWarnings("serial")
public class SystemCardType extends BaseObject{

	private Long id;
	
	private String cardTypeName;
	
	private Date startDate;
	
	private Date endDate;
	
	private String creditScope;
	
	private Long creditSum;		//总学时
	
	private Double price;
	
	private Long evpValue;
	
	private Integer isNetpay;	//1,仅限网上销售.  2,仅限地面销售（前台不需要销售）.  3,地面网上销售.
	
	private Integer isSdsync;
	
	private String creditType;		//授权类型
	
	private String balance;			//可学学分

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreditScope() {
		return creditScope;
	}

	public void setCreditScope(String creditScope) {
		this.creditScope = creditScope;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getEvpValue() {
		return evpValue;
	}

	public void setEvpValue(Long evpValue) {
		this.evpValue = evpValue;
	}

	public Integer getIsNetpay() {
		return isNetpay;
	}

	public void setIsNetpay(Integer isNetpay) {
		this.isNetpay = isNetpay;
	}

	public Integer getIsSdsync() {
		return isSdsync;
	}

	public void setIsSdsync(Integer isSdsync) {
		this.isSdsync = isSdsync;
	}

	public Long getCreditSum() {
		return creditSum;
	}

	public void setCreditSum(Long creditSum) {
		this.creditSum = creditSum;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
}


