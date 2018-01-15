package com.hys.exam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

import com.hys.exam.service.local.ExpertGroupManage;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;

/**
 * 专家信息类
 * @author Han
 *
 */
public class ExpertInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217567995666788022L;
	
	//Id
	private Long id;
	//名称
	private String name;
	//编码
	private String code;
	//所属委员会Id
	private Long groupId;
	//所属学组Id
	private Long subGroupId;
	//职务Id
	private Integer office;
	//届期Id
	private Long term;
	//职称Id
	private Long job;
	//状态
	private Integer state;
	//离职时间
	private Date breakDate;
	//禁用状态
	private Integer lockState;
	//工作单位
	private String workUnit;
	//固定电话
	private String phone1;
	//手机号
	private String phone2;
	//邮箱
	private String email;
	//国籍
	private Integer isNation;
	//秘书名称
	private String clerkName;
	//秘书电话
	private String clerkPhone;
	//银行
	private String bank;
	//银行卡号
	private String bankCard;
	//身份证号
	private String identityNum;
	//照片
	private String photo;
	//账号
	private String userName;
	//简介
	private String summary;
	
	//学组名称
	private String subGroupName;
	//委员会名称
	private String groupName;
	//届期
	private String termStr;
	//职称
	private String jobName;
	
	//关联学科Id
	private String propIds;
	//关联学科名称
	private String propNames;
	//关联学科
	private List<ExamProp> prop;
	//用于筛选未加入委员会的专家
	private String searchGroup;
	
	//多选择委员会
	private String groupIds;
	private String groupNames;
	private List<ExpertGroup> group; 
	private Integer workUnit_office;
	
	
	//关联职务
	private List<ExamProp> officeNew;
	private String officeIds;
	
	private String officeNames;
	
	
	//关联页面内容中间表
	private Integer editionId;

	
	public Integer getEditionId() {
		return editionId;
	}
	public void setEditionId(Integer editionId) {
		this.editionId = editionId;
	}
	public String getSearchGroup() {
		return searchGroup;
	}
	public void setSearchGroup(String searchGroup) {
		this.searchGroup = searchGroup;
	}
	public String getOfficeIds() {
		return officeIds;
	}
	public void setOfficeIds(String officeIds) {
		this.officeIds = officeIds;
	}
	public String getOfficeNames() {
		return officeNames;
	}
	public void setOfficeNames(String officeNames) {
		this.officeNames = officeNames;
	}
	public List<ExamProp> getOfficeNew() {
		return officeNew;
	}
	public void setOfficeNew(List<ExamProp> officeNew) {
		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();
		for(ExamProp p: officeNew){
			ids.add(p.getId());
			names.add(p.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setOfficeIds(idStr);
		this.setOfficeNames(nameStr);
		this.officeNew = officeNew;
	}
	//-------------------
	
	public Integer getWorkUnit_office() {
		return workUnit_office;
	}
	public void setWorkUnit_office(Integer workUnit_office) {
		this.workUnit_office = workUnit_office;
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
	public List<ExamProp> getProp() {
		return prop;
	}
	public void setProp(List<ExamProp> prop) {

		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();

		for(ExamProp p: prop){
			ids.add(p.getProp_val_id());
			names.add(p.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setPropIds(idStr);
		this.setPropNames(nameStr);

		this.prop = prop;
	}
	
	public List<ExpertGroup> getGroup() {
		return group;
	}
	public void setGroup(List<ExpertGroup> group) {

		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();

		for(ExpertGroup g: group){
			ids.add(g.getId());
			names.add(g.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setGroupIds(idStr);
		this.setGroupNames(nameStr);

		this.group = group;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTermStr() {
		return termStr;
	}
	public void setTermStr(String termStr) {
		this.termStr = termStr;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getIsNation() {
		return isNation;
	}
	public void setIsNation(Integer isNation) {
		this.isNation = isNation;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getSubGroupId() {
		return subGroupId;
	}
	public void setSubGroupId(Long subGroupId) {
		this.subGroupId = subGroupId;
	}
	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}
	public Integer getOffice() {
		return office;
	}
	public void setOffice(Integer office) {
		this.office = office;
	}
	public Long getTerm() {
		return term;
	}
	public void setTerm(Long term) {
		this.term = term;
	}
	public Long getJob() {
		return job;
	}
	public void setJob(Long job) {
		this.job = job;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(Date breakDate) {
		this.breakDate = breakDate;
	}
	public Integer getLockState() {
		return lockState;
	}
	public void setLockState(Integer lockState) {
		this.lockState = lockState;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
