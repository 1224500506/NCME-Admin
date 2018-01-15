package com.hys.exam.model;

public class SystemEditUser extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1939240308615975665L;

	/**
	 * 
	 */
	private Long id;
	
	/**
	 * 
	 */
	private String editName;
	
	/**
	 * 
	 */
	private Long phoneNUm;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEditName() {
		return editName;
	}

	public void setEditName(String editName) {
		this.editName = editName;
	}

	public Long getPhoneNUm() {
		return phoneNUm;
	}

	public void setPhoneNUm(Long phoneNUm) {
		this.phoneNUm = phoneNUm;
	}

}
