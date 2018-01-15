package com.hys.exam.model.system;

import java.io.Serializable;
import java.util.List;

import com.hys.exam.model.BaseObject;
import com.hys.exam.model.StudyCourseType;

/**
*
* <p>Description: 学习卡</p>
* @author chenlaibin
* @version 1.0 2013-12-16
*/
@SuppressWarnings("serial")
public class SystemCard extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 卡号
	 */
	private String cardNumber;
	/**
	 * 卡密码
	 */
	private String cardPassword;
	/**
	 * 卡类型 1 -学习卡
	 */
	private Long cardType;
	/**
	 * 导入时间
	 */
	private java.util.Date importDate;
	/**
	 * 有效期
	 */
	private java.util.Date usefulDate;
	/**
	 * 是否绑定
	 */
	private Integer isnotBind;
	/**
	 * 面值
	 */
	private Integer faceValue;
	/**
	 * 创建用户ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 最后更新用户ID
	 */
	private Long lastUpdateUserId;
	/**
	 * 最后更新时间
	 */
	private java.util.Date lastUpdateDate;

	/**
	 * 绑定时间
	 */
	private java.util.Date bindDate;

	/**
	 * 卡号绑定课程分类
	 */
	private List<StudyCourseType> studyCourseTypeList;
	
	/**
	 * 卡类型名称
	 */
	private String cardTypeName;
	/**
	 * 有效期开始时间  在卡类型中存
	 */
	private java.util.Date startDate;
	
	/**
	 * 有效期结束时间  在卡类型中存
	 */
	private java.util.Date endDate;
	
	/**
	 * 剩余学分
	 */
	private Double balance;
	
	//卡销售方式：1 网上   2 地面
	private Integer sellStyle;
	
	//是否售出  1 售出   0 为售出
	private Integer isselled;
	
	private Long cost;
	
	public List<StudyCourseType> getStudyCourseTypeList() {
		return studyCourseTypeList;
	}

	public void setStudyCourseTypeList(List<StudyCourseType> studyCourseTypeList) {
		this.studyCourseTypeList = studyCourseTypeList;
	}

	public java.util.Date getBindDate() {
		return bindDate;
	}

	public void setBindDate(java.util.Date bindDate) {
		this.bindDate = bindDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}

	public Long getCardType() {
		return cardType;
	}

	public void setCardType(Long cardType) {
		this.cardType = cardType;
	}

	public java.util.Date getImportDate() {
		return importDate;
	}

	public void setImportDate(java.util.Date importDate) {
		this.importDate = importDate;
	}

	public java.util.Date getUsefulDate() {
		return usefulDate;
	}

	public void setUsefulDate(java.util.Date usefulDate) {
		this.usefulDate = usefulDate;
	}

	public Integer getIsnotBind() {
		return isnotBind;
	}

	public void setIsnotBind(Integer isnotBind) {
		this.isnotBind = isnotBind;
	}

	public Integer getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(Integer faceValue) {
		this.faceValue = faceValue;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLastUpdateUserId() {
		return lastUpdateUserId;
	}

	public void setLastUpdateUserId(Long lastUpdateUserId) {
		this.lastUpdateUserId = lastUpdateUserId;
	}

	public java.util.Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(java.util.Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getSellStyle() {
		return sellStyle;
	}

	public void setSellStyle(Integer sellStyle) {
		this.sellStyle = sellStyle;
	}

	public Integer getIsselled() {
		return isselled;
	}

	public void setIsselled(Integer isselled) {
		this.isselled = isselled;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

}


