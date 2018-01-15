package com.hys.exam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Icd属性信息类
 * @author Han
 *
 */
public class ExamICD  implements Serializable {

	private static final long serialVersionUID = 1L;

	//Id
	private Long id;
	//类型
	private Integer type;
	//名称
	private String name;
	//编码
	private String code;
	//英文名称
	private String nameEn;
	//记住吗
	private String hint;
	
	//关联学科ID列表
	private String propIds;
	//关联学科名称列表
	private String propNames;
	//关联学科列表
	private List<ExamProp> prop;
	
	
	public String getPropIds() {
		return propIds;
	}
	public void setPropIds(String propIds) {
		this.propIds = propIds;
	}
	public String getPropNames() {
		return propNames;
	}
	public void setPropNames(String propNames) {
		this.propNames = propNames;
	}
	public List<ExamProp> getProp() {
		return prop;
	}
	public void setProp(List<ExamProp> prop) {

		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();

		for(ExamProp p: prop){
			ids.add(p.getProp_val_id());
			names.add(p.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setPropIds(idStr);
		this.setPropNames(nameStr);

		this.prop = prop;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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

	
}
