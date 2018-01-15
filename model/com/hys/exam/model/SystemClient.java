package com.hys.exam.model;

/**
 * 
 * 标题：用户信息
 * 
 * 作者：张伟清 2013-1-24
 * 
 * 描述：客户表
 * 
 * 说明:
 */
public class SystemClient extends BaseObject {

	private static final long serialVersionUID = 6667467945281570472L;

	/**
	 * 主键ID
	 */
	private Long id ;
	
	/**
	 * 客户名称
	 */
	private String clientName ;
	
	/**
	 * 机构ID
	 */
	private Long orgId ;
	
	/**
	 * 机构名称_VO
	 */
	private String orgName ;
	
	
	/**
	 * 联系人
	 */
	private String contact ;
	
	/**
	 * 联系电话
	 */
	private String contactPhone ;
	
	/**
	 * 备用电话
	 */
	private String backupPhone ;
	
	/**
	 * 主要负责人
	 */
	private String mainCharge ;
	
	/**
	 * 负责人联系方式
	 */
	private String mainChargeContact ;
	
	/**
	 * 备注
	 */
	private String remark ;
	
	/**
	 * 状态1 正常 -1 删除
	 */
	private Integer status ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getBackupPhone() {
		return backupPhone;
	}

	public void setBackupPhone(String backupPhone) {
		this.backupPhone = backupPhone;
	}

	public String getMainCharge() {
		return mainCharge;
	}

	public void setMainCharge(String mainCharge) {
		this.mainCharge = mainCharge;
	}

	public String getMainChargeContact() {
		return mainChargeContact;
	}

	public void setMainChargeContact(String mainChargeContact) {
		this.mainChargeContact = mainChargeContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
