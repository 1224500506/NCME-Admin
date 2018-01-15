package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-23
 * 
 * 描述：
 * 
 * 说明: 执业资格考试单元属性
 */
public class ExamZyzgPropUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5384061412384934415L;
	
	private Long id;
	
	private String name;
	
	/**
	 * 考察内容 综合笔试试题学科
	 */
	private Long examine_id;
	
	/**
	 * 考察内容 综合笔试试题学科
	 */
	private ExamZyzgPropExamine zyzgPropExamine;

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

	public Long getExamine_id() {
		return examine_id;
	}

	public void setExamine_id(Long examineId) {
		examine_id = examineId;
	}

	public ExamZyzgPropExamine getZyzgPropExamine() {
		return zyzgPropExamine;
	}

	public void setZyzgPropExamine(ExamZyzgPropExamine zyzgPropExamine) {
		this.zyzgPropExamine = zyzgPropExamine;
	}
	
	

}
