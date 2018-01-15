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
public class ExamQuestionTypeQuery extends AbstractQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8905194483018526646L;
	
	/**
	 * 分类ID
	 */
	private Long id;
	
	/**
	 *父ID
	 */
	private Long parent_id;
	
	/**
	 * 分类名称
	 */
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
