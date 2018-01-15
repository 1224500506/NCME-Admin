package com.hys.exam.model;

import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestAtt implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1206904149840868427L;
	
	private Long id;
	
	private String name;
	
	private int type;
	
	private Long sysPropValId;
	
	private String x_y;
	
	private List<ExamQuestRelation> relation;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getSysPropValId() {
		return sysPropValId;
	}

	public void setSysPropValId(Long sysPropValId) {
		this.sysPropValId = sysPropValId;
	}

	public String getX_y() {
		return x_y;
	}

	public void setX_y(String xY) {
		x_y = xY;
	}

	public List<ExamQuestRelation> getRelation() {
		return relation;
	}

	public void setRelation(List<ExamQuestRelation> relation) {
		this.relation = relation;
	}
	
	
	
	
	
	

}
