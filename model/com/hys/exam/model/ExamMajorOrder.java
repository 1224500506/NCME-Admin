package com.hys.exam.model;

import java.io.Serializable;

/**
 * 专业排名
 * @author Han
 *
 */
public class ExamMajorOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8227958804783659252L;

	private Long id;
	private String major;
	private String hospital;
	private String orderName;
	private Integer	classId;
	private Long hospitalId;
	
	public Long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	
}
