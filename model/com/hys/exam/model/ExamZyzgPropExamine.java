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
 * 说明: 执业资格考试考察内容属性
 */
public class ExamZyzgPropExamine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2860567409801671679L;
	
	private Integer id;
	
	private String name;
	/**
	 * 1:综合笔试试题学科
	 * 2:实践技能试题分类
	 */
	private Integer type;
	
	/**
	 * 专业
	 */
	private Integer profession_id;
	
	/**
	 * 职称
	 */
	private Integer title_id;
	
	/**
	 * 考查方向
	 */
	private Integer explore_id;
	
	private ExamZyzgPropPro zyzgPropPro;
	
	private ExamZyzgPropTitle zyzgPropTitle;
	
	private ExamZyzgPropExplore zyzgPropExplore;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getProfession_id() {
		return profession_id;
	}

	public void setProfession_id(Integer professionId) {
		profession_id = professionId;
	}

	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer titleId) {
		title_id = titleId;
	}

	public Integer getExplore_id() {
		return explore_id;
	}

	public void setExplore_id(Integer exploreId) {
		explore_id = exploreId;
	}

	public ExamZyzgPropPro getZyzgPropPro() {
		return zyzgPropPro;
	}

	public void setZyzgPropPro(ExamZyzgPropPro zyzgPropPro) {
		this.zyzgPropPro = zyzgPropPro;
	}

	public ExamZyzgPropTitle getZyzgPropTitle() {
		return zyzgPropTitle;
	}

	public void setZyzgPropTitle(ExamZyzgPropTitle zyzgPropTitle) {
		this.zyzgPropTitle = zyzgPropTitle;
	}

	public ExamZyzgPropExplore getZyzgPropExplore() {
		return zyzgPropExplore;
	}

	public void setZyzgPropExplore(ExamZyzgPropExplore zyzgPropExplore) {
		this.zyzgPropExplore = zyzgPropExplore;
	}	

}
