package com.hys.exam.model;

import org.apache.struts.upload.FormFile;


/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：郭津 2013-02-27
 * 
 * 描述：账户信息 model
 * 
 * 说明:
 */
public class PeixunOrg extends SystemAccount {
	
	private static final long serialVersionUID = 3120104883143599691L;

	private Long id;
	
	private String  name;
	
	private Integer type;
	
	private Integer  level;
	
	private Long hospital_Id;
	
	private Long region1_Id;
	
	private Long region2_Id;
	
	private Long region3_Id;
	
	private String contact;
	
	private String phone_Number;
	
	private Integer state;
	
	private String  description;
	
	private Long user_Id;
	
	private FormFile file;

	private String filePath;
	
	private String photoPath;
	
	private String hospital_name;
	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public FormFile getFile() {
		return file;
	}
	
	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Long getHospital_Id() {
		return hospital_Id;
	}

	public void setHospital_Id(Long hospital_Id) {
		this.hospital_Id = hospital_Id;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone_Number() {
		return phone_Number;
	}

	public void setPhone_Number(String phone_Number) {
		this.phone_Number = phone_Number;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(Long user_Id) {
		this.user_Id = user_Id;
	}

	public Long getRegion1_Id() {
		return region1_Id;
	}

	public void setRegion1_Id(Long region1_Id) {
		this.region1_Id = region1_Id;
	}

	public Long getRegion2_Id() {
		return region2_Id;
	}

	public void setRegion2_Id(Long region2_Id) {
		this.region2_Id = region2_Id;
	}

	public Long getRegion3_Id() {
		return region3_Id;
	}

	public void setRegion3_Id(Long region3_Id) {
		this.region3_Id = region3_Id;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	
}