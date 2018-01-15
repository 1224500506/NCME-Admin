package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Aug 5, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamRelationProp implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6966465146863287302L;

	private Long study1_prop_id;
	
	private Long study2_prop_id;
	
	private Long study3_prop_id;
	
	private Long unit_prop_id;
	
	private Long point_prop_id;
	
	private String relationPropName;
	
	private String relationPropId;

	public Long getStudy1_prop_id() {
		return study1_prop_id;
	}

	public void setStudy1_prop_id(Long study1PropId) {
		study1_prop_id = study1PropId;
	}

	public Long getStudy2_prop_id() {
		return study2_prop_id;
	}

	public void setStudy2_prop_id(Long study2PropId) {
		study2_prop_id = study2PropId;
	}

	public Long getStudy3_prop_id() {
		return study3_prop_id;
	}

	public void setStudy3_prop_id(Long study3PropId) {
		study3_prop_id = study3PropId;
	}

	public Long getUnit_prop_id() {
		return unit_prop_id;
	}

	public void setUnit_prop_id(Long unitPropId) {
		unit_prop_id = unitPropId;
	}

	public Long getPoint_prop_id() {
		return point_prop_id;
	}

	public void setPoint_prop_id(Long pointPropId) {
		point_prop_id = pointPropId;
	}

	public String getRelationPropName() {
		return relationPropName;
	}

	public void setRelationPropName(String relationPropName) {
		this.relationPropName = relationPropName;
	}

	public String getRelationPropId() {
		return relationPropId;
	}

	public void setRelationPropId(String relationPropId) {
		this.relationPropId = relationPropId;
	}

	
}
