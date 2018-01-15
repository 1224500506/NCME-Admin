package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-14
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamOlemPropRefBaseProp  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7557323996648700533L;

	private Long olem_prop_id;
	
	private Long base_prop_id;
	
	private Long id;
	
	private Integer type;
	
	private String name;
	
	private String x_y;
	
	public String getX_y() {
		return x_y;
	}

	public void setX_y(String xY) {
		x_y = xY;
	}

	public Long getOlem_prop_id() {
		return olem_prop_id;
	}

	public void setOlem_prop_id(Long olemPropId) {
		olem_prop_id = olemPropId;
	}

	public Long getBase_prop_id() {
		return base_prop_id;
	}

	public void setBase_prop_id(Long basePropId) {
		base_prop_id = basePropId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

}
