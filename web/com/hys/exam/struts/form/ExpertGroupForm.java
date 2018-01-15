package com.hys.exam.struts.form;


import java.util.Date;

import com.hys.framework.web.form.BaseForm;
import org.apache.struts.upload.FormFile;

/**
 * 专家委员会信息Form
 * @author Han
 *
 */
public class ExpertGroupForm extends BaseForm{

	private static final long serialVersionUID = -7619826935718904514L;

	private Long id;
	private String name;
	private String contact;
	private Long parent;
	private String phone1;
	private String phone2;
	private String email;
	private String address;
	private String note;
	private String organizeDate;
	private String breakDate;
	private String master;
	private Integer state;
	private String summary;
	
	private String startDate;
	private String endDate;
	
	private String propIds;
	private String propNames;
	
	
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
	public String getOrganizeDate() {
		return organizeDate;
	}
	public void setOrganizeDate(String organizeDate) {
		this.organizeDate = organizeDate;
	}
	public String getBreakDate() {
		return breakDate;
	}
	public void setBreakDate(String breakDate) {
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
	
	
}
