package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-13
 * 
 * 描述：
 * 
 * 说明: 试题分类表
 */
public class ExamQuestionType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6848968524545630850L;
	
	
	/**
	 * 分类ID
	 */
	private Long id;
	
	/**
	 *父ID
	 */
	private Long parent_id;
	
	/**
	 * 子系统ID
	 */
	private Long sub_sys_id;
	
	/**
	 * 分类名称
	 */
	private String name;
	
	/**
	 * 分类code
	 */
	private String code;
	
	/**
	 * 顺序
	 */
	private Integer seq;
	
	/**
	 * 状态
	 * 1正常
	 */
	private Integer state;
	
	/**
	 * 层级
	 */
	private Integer layer;
	
	/**
	 * 子节点数
	 */
	private int childNum;
	
	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parentId) {
		parent_id = parentId;
	}

	public Long getSub_sys_id() {
		return sub_sys_id;
	}

	public void setSub_sys_id(Long subSysId) {
		sub_sys_id = subSysId;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
