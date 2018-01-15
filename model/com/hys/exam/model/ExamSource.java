package com.hys.exam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExamSource implements Serializable {

	private static final long serialVersionUID = 6489461846448178927L;

	private Long id;
	
	private Long type;
	
	private String typeName;
	
	private String name;
	
	private String code;
	
	private String source;
	
	private String old;
	
	private Long subject;
	
	private String subjectName;
	
	private Integer state;

	private String propIds;
	private String propNames;
	private List<ExamProp> prop;
	
	private String zhutiIds;
	private String zhutiNames;
	private List<ExamProp> zhuti;
	
	private Long prop_val_id;

	private String author;//作者
	private String website;//网址
	
	public String getZhutiIds() {
		return zhutiIds;
	}
	public void setZhutiIds(String zhutiIds) {
		this.zhutiIds = zhutiIds;
	}
	public String getZhutiNames() {
		return zhutiNames;
	}
	public void setZhutiNames(String zhutiNames) {
		this.zhutiNames = zhutiNames;
	}
	public List<ExamProp> getZhuti() {
		return zhuti;
	}
	public void setZhuti(List<ExamProp> zhuti) {
		List<Long> ids = new ArrayList<Long>();
		List<String> names = new ArrayList<String>();

		for(ExamProp p: zhuti){
			ids.add(p.getProp_val_id());
			names.add(p.getName());
		}
		String idStr = ids.toString().replace(", ", ",");
		idStr = idStr.substring(1, idStr.length()-1);
		String nameStr = names.toString().replace(", ", ",");
		nameStr = nameStr.substring(1, nameStr.length()-1);
		this.setZhutiIds(idStr);
		this.setZhutiNames(nameStr);

		this.zhuti = zhuti;
	}
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
		this.prop_val_id = id;
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

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public Long getSubject() {
		return subject;
	}

	public void setSubject(Long subject) {
		this.subject = subject;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Long getProp_val_id() {
		return prop_val_id;
	}
	public void setProp_val_id(Long prop_val_id) {
		this.prop_val_id = prop_val_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}

