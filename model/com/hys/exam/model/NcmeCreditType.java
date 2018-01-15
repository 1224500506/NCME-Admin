package com.hys.exam.model;

/**
 * 
 * 标题：课程授权级别表
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCreditType extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1854522724635340993L;
	/**
	 * 授权类型
	 */
	private String creditType;
	/**
	 * 授权名称
	 */
	private String creditName;
	/**
	 * 权重
	 */
	private Integer weight;

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
