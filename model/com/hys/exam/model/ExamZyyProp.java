package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-23
 * 
 * 描述：
 * 
 * 说明: 住院医试题属性表
 */
public class ExamZyyProp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8538013508040678429L;
	
	private Long id;
	
	private Long parent_id;
	
	/**
	 * 属性名称
	 */
	private String zyy_prop_name;
	
	/**
	 * 属性类型
	 * 1 科室
	 * 2 二级科室
	 * 3 疾病 (对应基本属性的知识点)
	 */
	private Integer zyy_prop_type;
	
	/**
	 * 是否有下级属性关联
	 * 1 有, 0 无
	 */	
	private Integer relation;
	
	/**
	 * 状态
	 * 1 正常,-1 禁用
	 */	
	private Integer state;
	
	private Integer seq;
	
	/**
	 * 是否与基础属性关联
	 * 1有,0无
	 */	
	private Integer sys_prop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parentId) {
		parent_id = parentId;
	}

	public String getZyy_prop_name() {
		return zyy_prop_name;
	}

	public void setZyy_prop_name(String zyyPropName) {
		zyy_prop_name = zyyPropName;
	}

	public Integer getZyy_prop_type() {
		return zyy_prop_type;
	}

	public void setZyy_prop_type(Integer zyyPropType) {
		zyy_prop_type = zyyPropType;
	}

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getSys_prop() {
		return sys_prop;
	}

	public void setSys_prop(Integer sysProp) {
		sys_prop = sysProp;
	}
	
}
