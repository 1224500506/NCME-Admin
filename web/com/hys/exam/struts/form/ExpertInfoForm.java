package com.hys.exam.struts.form;


import com.hys.framework.web.form.BaseForm;
import org.apache.struts.upload.FormFile;

/**
 * 专家信息Form
 * @author Han
 *
 */
public class ExpertInfoForm extends BaseForm{

	private static final long serialVersionUID = -7619826935718904514L;

	private FormFile photo;
	
	private Long id;
	private String name;
	private String code;
	private Long groupId;
	private Integer office;
	private Long subGroupId;
	private Long term;
	private Integer state;
	private Long job;
	private String breakDate;
	private Integer lockState;
	private String workUnit;
	private String phone1;
	private String phone2;
	private String email;
	private String clerkName;
	private String clerkPhone;
	private String bank;
	private String bankCard;
	private String identityNum;
	private String userName;
	private String summary;
	private Integer isAddAccount;
	private Integer isNation;
	
	private String propIds;
	private String propNames;
	
	private String groupIds;
	private String groupNames;
	
	private String jobName;
	
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJob() {
		return job;
	}
	public void setJob(Long job) {
		this.job = job;
	}
	public Integer getLockState() {
		return lockState;
	}
	public void setLockState(Integer lockState) {
		this.lockState = lockState;
	}
	public FormFile getPhoto() {
		return photo;
	}
	public void setPhoto(FormFile photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Integer getOffice() {
		return office;
	}
	public void setOffice(Integer office) {
		this.office = office;
	}
	public Long getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(Long subGroupId) {
		this.subGroupId = subGroupId;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(String breakDate) {
		this.breakDate = breakDate;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
	}
	public String getClerkPhone() {
		return clerkPhone;
	}
	public void setClerkPhone(String clerkPhone) {
		this.clerkPhone = clerkPhone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getIsAddAccount() {
		return isAddAccount;
	}
	public void setIsAddAccount(Integer isAddAccount) {
		this.isAddAccount = isAddAccount;
	}
	public Integer getIsNation() {
		return isNation;
	}
	public void setIsNation(Integer isNation) {
		this.isNation = isNation;
	}
	public String getPropIds() {
		return propIds;
	}
	public void setPropIds(String propIds) {
		this.propIds = propIds;
	}
	public String getPropNames() {
		return propNames;
	}
	public void setPropNames(String propNames) {
		this.propNames = propNames;
	}
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String getGroupNames() {
		return groupNames;
	}
	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}
	
	
}
