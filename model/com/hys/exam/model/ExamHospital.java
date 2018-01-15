package com.hys.exam.model;

import java.io.Serializable;

/**
 * 医院级别
 * @author Han
 *
 */
public class ExamHospital implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2074018474148514156L;

	private Long id;
	private String name;
	private String examiner;
	private Long propId;
	private String startName;
	
	private Long regionId1;
	private Long regionId2;
	private Long regionId;
	
	private String region1;
	private String region2;
	private String region3;
	
	private String hospital_address;
	
	public String getHospital_address() {
		return hospital_address;
	}
	public void setHospital_address(String hospital_address) {
		this.hospital_address = hospital_address;
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
	public String getExaminer() {
		return examiner;
	}
	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Long getPropId() {
		return propId;
	}
	public void setPropId(Long propId) {
		this.propId = propId;
	}
	public String getRegion1() {
		return region1;
	}
	public void setRegion1(String region1) {
		this.region1 = region1;
	}
	public String getRegion2() {
		return region2;
	}
	public void setRegion2(String region2) {
		this.region2 = region2;
	}
	public String getRegion3() {
		return region3;
	}
	public void setRegion3(String region3) {
		this.region3 = region3;
	}
	public Long getRegionId1() {
		return regionId1;
	}
	public void setRegionId1(Long regionId1) {
		this.regionId1 = regionId1;
	}
	public Long getRegionId2() {
		return regionId2;
	}
	public void setRegionId2(Long regionId2) {
		this.regionId2 = regionId2;
	}
	public String getStartName() {
		return startName;
	}
	public void setStartName(String startName) {
		this.startName = startName;
	}
}
