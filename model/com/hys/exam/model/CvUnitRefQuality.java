package com.hys.exam.model;

import java.io.Serializable;

public class CvUnitRefQuality   implements Serializable{
	private static final long serialVersionUID = -517234630532590888L;
	
	private Long unit_id ;
	private Long prop_id ;
	private Integer level ;
	private Integer state ;
	private Long parent_prop_id ;
	
	public Long getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(Long unit_id) {
		this.unit_id = unit_id;
	}
	public Long getProp_id() {
		return prop_id;
	}
	public void setProp_id(Long prop_id) {
		this.prop_id = prop_id;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getParent_prop_id() {
		return parent_prop_id;
	}
	public void setParent_prop_id(Long parent_prop_id) {
		this.parent_prop_id = parent_prop_id;
	}
		
}
