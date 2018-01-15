package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamProp implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8023445430511732631L;

	/**
	 * 属性值ID
	 */
	private Long id;
	
	/**
	 * 属性类型
	 */
	private Integer type;
	
	/**
	 * 属性类型名称
	 */
	private String typeName;
	
	/**
	 * 属性名
	 */
	private String name;
	
	/**
	 * 属性编码
	 */
	private String code;
	
	
	private Integer c_type;
	
	private String c_type_name;
	
	private Integer ext_type;
	
	private Integer img_type;
	/**
	 * 系统属性ID
	 */
	private Long prop_val_id;
	
	
	public Integer getImg_type() {
		return img_type;
	}

	public void setImg_type(Integer img_type) {
		this.img_type = img_type;
	}

	private Integer questionNum;
	private List<ExamICD> icdList;
	
	
	private Long prop_val1;
	private Long prop_val2;
	
	private Long codenum;

	private String sort;
	private String dir;
	
	public Long getCodenum() {
		return codenum;
	}

	public void setCodenum(Long codenum) {
		this.codenum = codenum;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public Long getProp_val_id() {
		return prop_val_id;
	}

	public void setProp_val_id(Long propValId) {
		prop_val_id = propValId;
	}

	public Long getProp_val1() {
		return prop_val1;
	}

	public void setProp_val1(Long propVal1) {
		prop_val1 = propVal1;
	}

	public Long getProp_val2() {
		return prop_val2;
	}

	public void setProp_val2(Long propVal2) {
		prop_val2 = propVal2;
	}

	public Integer getC_type() {
		return c_type;
	}

	public void setC_type(Integer cType) {
		c_type = cType;
	}

	public String getC_type_name() {
		return c_type_name;
	}

	public void setC_type_name(String cTypeName) {
		c_type_name = cTypeName;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public List<ExamICD> getIcdList() {
		return icdList;
	}

	public void setIcdList(List<ExamICD> icdList) {
		this.icdList = icdList;
	}

	public Integer getExt_type() {
		return ext_type;
	}

	public void setExt_type(Integer ext_type) {
		this.ext_type = ext_type;
	}
	

}
