package com.hys.exam.model.system;

import com.hys.exam.model.BaseObject;

/**
*
* <p>Description: 描述</p>
* @author chenlaibin
* @version 1.0 2013-12-21
*/
@SuppressWarnings("serial")
public class SystemCreditType extends BaseObject{

	private String creditType;
	
	private String creditName;
	
	private Integer weight;
	
	private Integer checked;	//是否选中
	

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}
}


