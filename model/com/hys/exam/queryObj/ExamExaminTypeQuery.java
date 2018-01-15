package com.hys.exam.queryObj;
/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-19
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamExaminTypeQuery extends AbstractQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2167079788722046746L;
	
	private Long id;
	
	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 父ID
	 */
	private Long parent_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parentId) {
		parent_id = parentId;
	}
	
	

}
