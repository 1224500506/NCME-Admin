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
 * 说明: 卫生专业技术资格 属性
 */
public class ExamWszgProp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166011001624627594L;
	
	private Long id;
	
	private Long parent_id;
	
	private String wszg_prop_name;
	
	/**
	 * 属性类型
	 * 1 主专业
	 * 2 亚专业
	 * 3 职称
	 * 4 考察方向
	 * 5 单元
	 * 6 细目
	 * 7 副知识点
	 */
	private Integer wszg_prop_type;
	
	/**
	 * 是否有下级属性关联
	 * 1 有, 0 无
	 */
	private Integer relation;
	
	/**
	 * 是否与基础属性关联
	 * 1有,0无
	 */
	private Integer sys_prop;
	
	/**
	 * 状态
	 * 1 正常,-1 禁用
	 */
	private Integer state;
	
	private Integer seq;

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

	public String getWszg_prop_name() {
		return wszg_prop_name;
	}

	public void setWszg_prop_name(String wszgPropName) {
		wszg_prop_name = wszgPropName;
	}

	public Integer getWszg_prop_type() {
		return wszg_prop_type;
	}

	public void setWszg_prop_type(Integer wszgPropType) {
		wszg_prop_type = wszgPropType;
	}

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public Integer getSys_prop() {
		return sys_prop;
	}

	public void setSys_prop(Integer sysProp) {
		sys_prop = sysProp;
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
	
	

}
