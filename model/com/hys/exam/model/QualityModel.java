package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class QualityModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052441535717701624L;

	private Long id;
	
	private ModelType type;
	
	private String name;

	private Long parentId;
	
	private Long level;
	
	private List<PropUnit> subjectPropList;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public ModelType getType() {
		return type;
	}

	public void setType(ModelType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public List<PropUnit> getSubjectPropList() {
		return subjectPropList;
	}

	public void setSubjectPropList(List<PropUnit> subjectPropList) {
		this.subjectPropList = subjectPropList;
	}
}
