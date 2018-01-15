package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;

import com.hys.exam.model.system.SystemCard;
import com.hys.exam.model.system.SystemCardType;

/**
 * 
 * 支付订单
 * SystemCardOrder
 * 创建人:chy
 * 时间：2017-4-18-下午7:31:21 
 * @version 1.0.0
 *
 */
public class SystemCardOrderEntity extends SystemCardType implements Serializable {

	/**
	 * serialVersionUID:TODO（）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer	CARD_TYPE_ID;
	private Long  USER_ID;
	private Date USEFUL_DATE;
	private Double	PRICE;
	private Integer	AMOUNT;
	private String PAY_DATE;
	private String	PAYMODE_CODE;
	private String	ORDER_NUMBER;
	private Integer	STATUS;
	private Integer	ORDER_TYPE;
	private Long CV_SET_ID;
	private String ITEM_NAME;
	private String CARD_NUMBER;
	private Integer use_status;//标识改卡对应的项目是否已经使用过了该卡 【0未使用 1已使用】---taoliang
	private Integer cvSetTotal;//某卡类型下的项目总数
	private Integer cvSetUseTotal;//已被使用的项目数
	private Integer cvSetNoUseTotal;//未被使用项目数
	
	
	public Integer getCvSetTotal() {
		return cvSetTotal;
	}
	public void setCvSetTotal(Integer cvSetTotal) {
		this.cvSetTotal = cvSetTotal;
	}
	public Integer getCvSetUseTotal() {
		return cvSetUseTotal;
	}
	public void setCvSetUseTotal(Integer cvSetUseTotal) {
		this.cvSetUseTotal = cvSetUseTotal;
	}
	public Integer getCvSetNoUseTotal() {
		return cvSetNoUseTotal;
	}
	public void setCvSetNoUseTotal(Integer cvSetNoUseTotal) {
		this.cvSetNoUseTotal = cvSetNoUseTotal;
	}
	public Integer getUse_status() {
		return use_status;
	}
	public void setUse_status(Integer use_status) {
		this.use_status = use_status;
	}
	public String getITEM_NAME() {
		return ITEM_NAME;
	}
	public void setITEM_NAME(String iTEM_NAME) {
		ITEM_NAME = iTEM_NAME;
	}
	public Integer getCARD_TYPE_ID() {
		return CARD_TYPE_ID;
	}
	public void setCARD_TYPE_ID(Integer cARD_TYPE_ID) {
		CARD_TYPE_ID = cARD_TYPE_ID;
	}
	public Long getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(Long uSER_ID) {
		USER_ID = uSER_ID;
	}
	 
	public Date getUSEFUL_DATE() {
		return USEFUL_DATE;
	}
	public void setUSEFUL_DATE(Date uSEFUL_DATE) {
		USEFUL_DATE = uSEFUL_DATE;
	}
	public void setPAY_DATE(String pAY_DATE) {
		PAY_DATE = pAY_DATE;
	}
	public Double getPRICE() {
		return PRICE;
	}
	public void setPRICE(Double pRICE) {
		PRICE = pRICE;
	}
	public Integer getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Integer aMOUNT) {
		AMOUNT = aMOUNT;
	}
	
	public String getPAYMODE_CODE() {
		return PAYMODE_CODE;
	}
	public void setPAYMODE_CODE(String pAYMODE_CODE) {
		PAYMODE_CODE = pAYMODE_CODE;
	}
	public String getORDER_NUMBER() {
		return ORDER_NUMBER;
	}
	public void setORDER_NUMBER(String oRDER_NUMBER) {
		ORDER_NUMBER = oRDER_NUMBER;
	}
	public Integer getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Integer sTATUS) {
		STATUS = sTATUS;
	}
	public Integer getORDER_TYPE() {
		return ORDER_TYPE;
	}
	public void setORDER_TYPE(Integer oRDER_TYPE) {
		ORDER_TYPE = oRDER_TYPE;
	}
	public Long getCV_SET_ID() {
		return CV_SET_ID;
	}
	public void setCV_SET_ID(Long cV_SET_ID) {
		CV_SET_ID = cV_SET_ID;
	}
	public String getPAY_DATE() {
		return PAY_DATE;
	}
	public String getCARD_NUMBER() {
		return CARD_NUMBER;
	}
	public void setCARD_NUMBER(String cARD_NUMBER) {
		CARD_NUMBER = cARD_NUMBER;
	}


	
	
	




	
	
	
}
