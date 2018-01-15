package com.hys.exam.model;

import java.io.Serializable;

public class MobileMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1580837873845017690L;
	private Long ID;   //
	private Integer	MOBILE_PHONE; //手机号
	private String	CONTENT;   //内容
	private String	MESSAGE_DESC;  //内容描述
	private Integer	SEND_STATUS;  //发送状态   
	private Integer STATUS;   //状态
	private String	SEND_TIME;  //发送时间
	private Long	SITE_ID;
	private String	SENDID;
	private Integer	MOBILE_CODE;  //验证码
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Integer getMOBILE_PHONE() {
		return MOBILE_PHONE;
	}
	public void setMOBILE_PHONE(Integer mOBILE_PHONE) {
		MOBILE_PHONE = mOBILE_PHONE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getMESSAGE_DESC() {
		return MESSAGE_DESC;
	}
	public void setMESSAGE_DESC(String mESSAGE_DESC) {
		MESSAGE_DESC = mESSAGE_DESC;
	}
	public Integer getSEND_STATUS() {
		return SEND_STATUS;
	}
	public void setSEND_STATUS(Integer sEND_STATUS) {
		SEND_STATUS = sEND_STATUS;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public String getSEND_TIME() {
		return SEND_TIME;
	}
	public void setSEND_TIME(String sEND_TIME) {
		SEND_TIME = sEND_TIME;
	}
	public Long getSITE_ID() {
		return SITE_ID;
	}
	public void setSITE_ID(Long sITE_ID) {
		SITE_ID = sITE_ID;
	}
	public String getSENDID() {
		return SENDID;
	}
	public void setSENDID(String sENDID) {
		SENDID = sENDID;
	}
	public Integer getMOBILE_CODE() {
		return MOBILE_CODE;
	}
	public void setMOBILE_CODE(Integer mOBILE_CODE) {
		MOBILE_CODE = mOBILE_CODE;
	}
	
	


}
