package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;

/**
 * 
 * <p>
 * Description: 学习卡订单
 * </p>
 * 
 * @author chenlaibin
 * @version 1.0 2013-12-17
 */
@SuppressWarnings("serial")
public class SystemCardOrder extends BaseObject {

	private Long id;
	private Long cardTypeId;		//对应学习卡类型表
	private Long userId;			//用户ID
	private Date orderDate;			//订单生成时间
	private Long quantity;			//订单数量
	private Double price;			//单价
	private Double amount;			//是指优惠后的总价格，优惠前总价格=订单数量x单价
	private Date payDate;			//付款时间
	private String orderResource;	//暂时不用
	private String paymodeCode;		//对应表：SYSTEM_ORDER_PAYMODE //支付方式
	private Integer invoiceStatus;	//0：不需要发票 1：需要发票 2：已开发票
	private String invoiceTitle;	//发票抬头
	private String recipient;		//收件人
	private String tel;				//收件人联系电话
	private String address;			//收件人联系地址
	private String orderOper;		//订单处理人
	private Date operDate;			//订单处理时间
	private String remark;			//备注
	private String postCode;		//邮编
	private String orderNumber;		//订单号
	private Integer orderType;		//订单类别
	private Integer status;			//订单状态  1：正常 -1 删除   2:未付款  3:已付款 4:订单完成  5:退款

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Long cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getOrderResource() {
		return orderResource;
	}

	public void setOrderResource(String orderResource) {
		this.orderResource = orderResource;
	}

	public String getPaymodeCode() {
		return paymodeCode;
	}

	public void setPaymodeCode(String paymodeCode) {
		this.paymodeCode = paymodeCode;
	}

	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderOper() {
		return orderOper;
	}

	public void setOrderOper(String orderOper) {
		this.orderOper = orderOper;
	}

	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date operDate) {
		this.operDate = operDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
