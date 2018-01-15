package com.hys.exam.model.systemQuery;

import com.hys.exam.model.system.SystemCardOrder;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-17
*/
@SuppressWarnings("serial")
public class SystemCardOrderQuery extends SystemCardOrder {

	/**
	 * 学员姓名
	 */
	private String userName;
	
	/**
	 * 账户名(用户名)
	 */
	private String accountName;
	
	/**
	 * 卡类型名称
	 */
	private String cardTypeName;
	
	//订单日期
	private String orderDateStart;
	private String orderDateEnd; 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(String orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public String getOrderDateStart() {
		return orderDateStart;
	}

	public void setOrderDateStart(String orderDateStart) {
		this.orderDateStart = orderDateStart;
	}
}


