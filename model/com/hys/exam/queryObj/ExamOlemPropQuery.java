package com.hys.exam.queryObj;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemPropQuery extends AbstractQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3779081579077109190L;

	
	private Long id;
	
	private Long parent_id;
	
	private String olem_prop_name;
	
	/**
	 * 属性类型
	 * 1 专业
	 * 2 考查方向
	 * 3 学科
	 * 4 知识单元
	 * 5 知识点
	 * 6 科室
	 * 7 二级科室
	 * 8 知识点
	 * 9 考查内容
	 * 10 知识点
	 */
	private Integer olem_prop_type;
	
	private Integer seq;
	
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
	
	/**
	 * 状态
	 * 1 正常,-1 禁用
	 */
	private Integer state;

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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
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
	
	
	
}
