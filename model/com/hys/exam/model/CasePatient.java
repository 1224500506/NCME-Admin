package com.hys.exam.model;

import java.io.Serializable;
public class CasePatient  implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String DiagDate;
	private String PName;
	private Integer sex;
	private String birthday;
	private String Certificate;
	private Integer NationalState;
	private Integer Number1Type;
	private String Number1;
	private Integer Number2Type;
	private String Number2;
	private String phoneNumber1;
	private String phoneNumber2;
	private String diagName;
	private Integer pAge; 
	public Integer getpAge() {
		return pAge;
	}
	public void setpAge(Integer pAge) {
		this.pAge = pAge;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDiagDate() {
		return DiagDate;
	}
	public void setDiagDate(String diagDate) {
		DiagDate = diagDate;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		this.PName = pName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCertificate() {
		return Certificate;
	}
	public void setCertificate(String certificate) {
		Certificate = certificate;
	}
	public Integer getNationalState() {
		return NationalState;
	}
	public void setNationalState(Integer nationalState) {
		NationalState = nationalState;
	}
	public Integer getNumber1Type() {
		return Number1Type;
	}
	public void setNumber1Type(Integer number1Type) {
		Number1Type = number1Type;
	}
	public String getNumber1() {
		return Number1;
	}
	public void setNumber1(String number1) {
		Number1 = number1;
	}
	public Integer getNumber2Type() {
		return Number2Type;
	}
	public void setNumber2Type(Integer number2Type) {
		Number2Type = number2Type;
	}
	public String getNumber2() {
		return Number2;
	}
	public void setNumber2(String number2) {
		Number2 = number2;
	}
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	public String getDiagName() {
		return diagName;
	}
	public void setDiagName(String diagName) {
		this.diagName = diagName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
