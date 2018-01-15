package com.hys.exam.model;

import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：zdz 2010-10-25
 * 
 * 描述：
 * 
 * 说明:属性值生成文件用
 */
public class ExamPropValTemp implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 705695573363040398L;

	/**
	 * 属性值ID
	 */
	private String id;
	
	/**
	 * 属性名
	 */
	private String name;
	
	/**
	 * 子节点集合
	 */
	private List<ExamPropValTemp> subItems;
	
	/**
	 * 试题数量
	 */
	private Integer quesCount;
	

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

	public List<ExamPropValTemp> getSubItems() {
		return subItems;
	}

	public void setSubItems(List<ExamPropValTemp> subItems) {
		this.subItems = subItems;
	}

	public Integer getQuesCount() {
		return quesCount;
	}

	public void setQuesCount(Integer quesCount) {
		this.quesCount = quesCount;
	}
	
	

}
