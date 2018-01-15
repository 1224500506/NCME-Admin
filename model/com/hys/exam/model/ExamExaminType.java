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
 * 说明:		考试分类
 */
public class ExamExaminType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5545674695578422498L;

	private Long id;
	
	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 父ID
	 */
	private Long parent_id;
	
	/**
	 * 子系统ID
	 */
	private Integer sub_sys_id;
	
	/**
	 * 子系统名称
	 */
	private String sub_sys_name;
	
	/**
	 * code
	 */
	private String code;
	
	/**
	 * 顺序
	 */
	private Integer seq;
	
	/**
	 * 层级
	 */
	private Integer layer;
	
	/**
	 * 状态
	 * 1 正常
	 * -1 禁用
	 */
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
