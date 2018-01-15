package com.hys.auth.model;

import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * 标题：资源
 * 
 * 作者：zdz 2009-12-30
 * 
 * 描述：
 * 
 * 说明:
 */
public class HysResources extends AbstractObject {

	private static final long serialVersionUID = 1L;

	private Integer type;
	private String name;
	private String value;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("type", type).append("name", name).append("value", value).toString();
	}

}