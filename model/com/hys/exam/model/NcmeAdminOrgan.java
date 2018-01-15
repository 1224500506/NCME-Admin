package com.hys.exam.model;

/**
 * 
 * 标题：继教地区表
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeAdminOrgan extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6863589752229896307L;
	/**
	 * 机构ID
	 */
	private String organId;
	/**
	 * 机构名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 简称
	 */
	private String shortname;
	/**
	 * 行政区划ID
	 */
	private String districtId;
	/**
	 * 标题
	 */
	private String policyTitle;
	/**
	 * 内容
	 */
	private String policyContent;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String userPasswd;
	/**
	 * 强制显示类别
	 */
	private Integer forceType;
	/**
	 * 强制显示内容
	 */
	private String forceContent;
	/**
	 * 办事处联系方式
	 */
	private String officeTel;
	/**
	 * 学习卡类型
	 */
	private String cardType;
	/**
	 * 组织机构ID
	 */
	private Long orgId;

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getPolicyTitle() {
		return policyTitle;
	}

	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	public String getPolicyContent() {
		return policyContent;
	}

	public void setPolicyContent(String policyContent) {
		this.policyContent = policyContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPasswd() {
		return userPasswd;
	}

	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}

	public Integer getForceType() {
		return forceType;
	}

	public void setForceType(Integer forceType) {
		this.forceType = forceType;
	}

	public String getForceContent() {
		return forceContent;
	}

	public void setForceContent(String forceContent) {
		this.forceContent = forceContent;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
