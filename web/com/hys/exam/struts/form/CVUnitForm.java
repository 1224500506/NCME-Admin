package com.hys.exam.struts.form;

import com.hys.framework.web.form.BaseForm;

public class CVUnitForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6581916631590859529L;
	
	private Long id;
	
	private String name;
	
	private Integer type;
	
	private Integer point;
	
	private Integer state;
	
	private String content;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
