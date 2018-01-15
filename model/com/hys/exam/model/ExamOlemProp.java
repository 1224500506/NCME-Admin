package com.hys.exam.model;

import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-22
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemProp {
	
	private Long id;
	
	private Long parent_id;
	
	private String olem_prop_name;
	
	/**
	 * 属性类型
	 * 1 专业
	 * 2 考查方向-基础理论
	 * 3 考查方向-基本知识
	 * 4 考查方向-基本技能
	 * 5 学科
	 * 6 知识单元
	 * 7 知识点
	 * 8 科室
	 * 9 二级科室
	 * 10 知识点
	 * 11 考查内容
	 * 12 知识点
	 */
	private Integer olem_prop_type;
	
	/**
	 * 是否有下级属性关联
	 * 1 有， 0 无
	 */
	private Integer relation;
	
	/**
	 * 是否与基础属性关联
	 * 1 有， 0 无
	 * 
	 */
	private Integer sys_prop;
	
	private String x_y;
	
	
	private List<ExamOlemProp> nextOlemPropList;
	
	public List<ExamOlemProp> getNextOlemPropList() {
		return nextOlemPropList;
	}

	public void setNextOlemPropList(List<ExamOlemProp> nextOlemPropList) {
		this.nextOlemPropList = nextOlemPropList;
	}

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

	public String getOlem_prop_name() {
		return olem_prop_name;
	}

	public void setOlem_prop_name(String olemPropName) {
		olem_prop_name = olemPropName;
	}

	public Integer getOlem_prop_type() {
		return olem_prop_type;
	}

	public void setOlem_prop_type(Integer olemPropType) {
		olem_prop_type = olemPropType;
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

	public String getX_y() {
		return x_y;
	}

	public void setX_y(String xY) {
		x_y = xY;
	}

}
