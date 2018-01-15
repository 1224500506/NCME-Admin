package com.hys.exam.model.system;

import java.util.ArrayList;
import java.util.List;

import com.hys.exam.model.BaseObject;

public class SystemUserType extends BaseObject {

	private static final long serialVersionUID = 2887995986460238087L;

	/**
	 * typeID
	 */
	private Long id;
	
	/**
	 * typeName
	 */
	private String user_type_name;
	
	private String user_type_remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUser_type_name() {
		return user_type_name;
	}

	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}

	public String getUser_type_remark() {
		return user_type_remark;
	}

	public void setUser_type_remark(String user_type_remark) {
		this.user_type_remark = user_type_remark;
	}


}
