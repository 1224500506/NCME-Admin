package com.hys.exam.model;


/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：zdz 2010-10-25
 * 
 * 描述：
 * 
 * 说明:试题修改返回用
 */
public class ExamPropValTemp2{


	/**
	 * 属性值ID
	 */
	private String id;
	
	/**
	 * 属性名
	 */
	private String name;
	
	/**
	 * 父节点
	 */
	private ExamPropValTemp2 parObj;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExamPropValTemp2 getParObj() {
		return parObj;
	}

	public void setParObj(ExamPropValTemp2 parObj) {
		this.parObj = parObj;
	}
	


}
