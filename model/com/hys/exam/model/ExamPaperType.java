package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-16
 * 
 * 描述：
 * 
 * 说明: 试卷分类
 */
public class ExamPaperType implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1208140754628407486L;

	private Long id;
	
	private String name;
	
	private Long parent_id;
	
	private Integer sub_sys_id;
	
	private String sub_sys_name;
	
	private String code;
	
	private Integer seq;
	
	private Integer layer;
	
	private Integer state;
	
	private int childNum;
	
	private String remark;
	
	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

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

	public Integer getSub_sys_id() {
		return sub_sys_id;
	}

	public void setSub_sys_id(Integer subSysId) {
		sub_sys_id = subSysId;
	}

	public String getSub_sys_name() {
		return sub_sys_name;
	}

	public void setSub_sys_name(String subSysName) {
		sub_sys_name = subSysName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
