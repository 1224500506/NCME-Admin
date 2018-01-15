package com.hys.exam.struts.form;

import com.hys.framework.web.form.BaseForm;

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
public class ExamPropForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 189625609171665011L;
	
	private Long id;
	
	private String propName;
	
	private String code;
	
	private Integer type;
	
	private Integer c_type;
	
	private Long prop_val1;
	
	private Long prop_val_id;
	
	private String nameEn;
	
	private String source;
	
	private String old;
	
	private String hint;
	
	private Integer ext_type;

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getProp_val1() {
		return prop_val1;
	}

	public void setProp_val1(Long propVal1) {
		prop_val1 = propVal1;
	}

	public Long getProp_val_id() {
		return prop_val_id;
	}

	public void setProp_val_id(Long propValId) {
		prop_val_id = propValId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getC_type() {
		return c_type;
	}

	public void setC_type(Integer cType) {
		c_type = cType;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOld() {
		return old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public Integer getExt_type() {
		return ext_type;
	}

	public void setExt_type(Integer ext_type) {
		this.ext_type = ext_type;
	}

	
	
}
