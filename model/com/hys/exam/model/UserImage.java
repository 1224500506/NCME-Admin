package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

public class UserImage implements Serializable {

	/**
	 * boy 11/08/16
	 */
	private static final long serialVersionUID = 1962202751275484776L;

	private Long id;
	
	private ModelType type;
	
	private String name;
	
	private Long level;
	
	private List<PropUnit> departmentPropList;
	
	private GeneralUserImage generalUserImage;
	
	private SpecialUserImage specialUserImage;

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

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public List<PropUnit> getDepartmentPropList() {
		return departmentPropList;
	}

	public void setDepartmentPropList(List<PropUnit> departmentPropList) {
		this.departmentPropList = departmentPropList;
	}

	public GeneralUserImage getGeneralUserImage() {
		return generalUserImage;
	}

	public void setGeneralUserImage(GeneralUserImage generalUserImage) {
		this.generalUserImage = generalUserImage;
	}

	public SpecialUserImage getSpecialUserImage() {
		return specialUserImage;
	}

	public void setSpecialUserImage(SpecialUserImage specialUserImage) {
		this.specialUserImage = specialUserImage;
	}
}
