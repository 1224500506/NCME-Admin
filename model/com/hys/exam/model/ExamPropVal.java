package com.hys.exam.model;

import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-15
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPropVal implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5587828996745790369L;
	
	/**
	 * 属性值ID
	 */
	private Long id;
	
	/**
	 * 属性值类型
	 */
	private Integer type;
	
	/**
	 * 属性名
	 */
	private String name;
	
	/**
	 * 属性编码
	 */
	private String code;
	
	/**
	 * 关联id
	 */
	private Long refId;
	
	private List<ExamPropExport> ref;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public List<ExamPropExport> getRef() {
		return ref;
	}

	public void setRef(List<ExamPropExport> ref) {
		this.ref = ref;
	}
	
	

}
