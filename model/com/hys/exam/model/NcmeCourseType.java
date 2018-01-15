package com.hys.exam.model;

/**
 * 
 * 标题：
 * 
 * 作者：陈明凯 2013-5-17
 * 
 * 描述：
 * 
 * 说明:
 */
public class NcmeCourseType extends BaseObject {

	/**
	 * 课程授权类型表
	 */
	private static final long serialVersionUID = 5010160882954294206L;

	/**
	 * 课程类型id
	 */
	private Integer courseType;
	/**
	 * 课程类型名称
	 */
	private String typeName;
	/**
	 * 权重
	 */
	private Integer weight;
	/**
	 * 备注
	 */
	private String remark;

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
