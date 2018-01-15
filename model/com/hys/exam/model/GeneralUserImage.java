package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class GeneralUserImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5322952155913101648L;

	private List<PropUnit> areaPropList;
	
	private List<PropUnit> hospitalPropList;
	
	private List<PropUnit> dutyPropList;

	public List<PropUnit> getAreaPropList() {
		return areaPropList;
	}

	public void setAreaPropList(List<PropUnit> areaPropList) {
		this.areaPropList = areaPropList;
	}

	public List<PropUnit> getHospitalPropList() {
		return hospitalPropList;
	}

	public void setHospitalPropList(List<PropUnit> hospitalPropList) {
		this.hospitalPropList = hospitalPropList;
	}

	public List<PropUnit> getDutyPropList() {
		return dutyPropList;
	}

	public void setDutyPropList(List<PropUnit> dutyPropList) {
		this.dutyPropList = dutyPropList;
	}
	
}
