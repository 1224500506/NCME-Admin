package com.hys.exam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 专家委员会信息类
 * @author Han
 *
 */
public class ExpertGroup  implements Serializable {

	private static final long serialVersionUID = -1217567995666788022L;
	
	//Id
	private Long id;
	//名称
	private String name;
	
	private Integer type;
	private Long prop_val_id;
	//联系人
	private String contact;
	//父类Id
	private Long parent;
	//固定电话
	private String phone1;
	//手机号
	private String phone2;
	//邮箱
	private String email;
	//地址
	private String address;
	//备注
	private String note;
	//成立时间
	private Date organizeDate;
	//离职时间
	private Date breakDate;
	//主任
	private String master;
	//任职状态
	private Integer state;
	//状态
	private Integer lockState;
	//简介
	private String summary;
	
	//学组数
	private Integer numSubGroup;
	//届期
	private String termStr;
	
	//关联学科Id
	private String propIds;
	//关联学科名称
	private String propNames;
	//关联学科
	private List<ExamProp> prop;
	//排序
	private Integer ordernum;
	
	private Integer sortNum;
	private Integer sort;
	private String teamMember;
	
	private String startDate;
	private String endDate;
	
	private Integer check_state;
	
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	public Integer getCheck_state() {
		return check_state;
	}
	public void setCheck_state(Integer check_state) {
		this.check_state = check_state;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(String teamMember) {
		this.teamMember = teamMember;
	}
	public Integer getLockState() {
		return lockState;
	}
	public void setLockState(Integer lockState) {
		this.lockState = lockState;
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
	
	public Integer getNumSubGroup() {
		return numSubGroup;
	}
	public void setNumSubGroup(Integer numSubGroup) {
		this.numSubGroup = numSubGroup;
	}
	public String getTermStr() {
		return termStr;
	}
	public void setTermStr(String termStr) {
		this.termStr = termStr;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getOrganizeDate() {
		return organizeDate;
	}
	public void setOrganizeDate(Date organizeDate) {
		this.organizeDate = organizeDate;
	}
	public Date getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(Date breakDate) {
		this.breakDate = breakDate;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getProp_val_id() {
		return prop_val_id;
	}
	public void setProp_val_id(Long prop_val_id) {
		this.prop_val_id = prop_val_id;
	}
	

}
