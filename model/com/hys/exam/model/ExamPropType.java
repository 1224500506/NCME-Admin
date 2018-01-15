package com.hys.exam.model;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-6-21
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropType implements java.io.Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6157065092858118438L;

	private Integer prop_type;
	
	private String prop_type_name;

	public Integer getProp_type() {
		return prop_type;
	}

	public void setProp_type(Integer propType) {
		prop_type = propType;
	}

	public String getProp_type_name() {
		return prop_type_name;
	}

	public void setProp_type_name(String propTypeName) {
		prop_type_name = propTypeName;
	}
	
}
