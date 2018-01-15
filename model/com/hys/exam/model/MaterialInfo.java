package com.hys.exam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MaterialInfo  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217567995666788022L;
	
	private Long id;
	private String code;
	private String name;
	private String surname;
	private Integer type;
	private String format;
	private String fileName;
	private String duration;
	private String savePath;
	private Double fps;
	private String resolution;
	private Integer national_state;
	private String summary;
	private String otherSource;
	private String expertName;
	private Integer state;
	private Integer cognize;
	
	private String upload_date;
	private String deli_date;
	private String deli_opinion;
	private String deli_man;
	
	
	private Map<String, List<MaterialProp>> prop_map;
	
	public Map<String, List<MaterialProp>> getProp_map() {
		return prop_map;
	}
	public void setProp_map(Map<String, List<MaterialProp>> questionPropMap) {
		this.prop_map = questionPropMap;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Double getFps() {
		return fps;
	}
	public void setFps(Double fps) {
		this.fps = fps;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Integer getNational_state() {
		return national_state;
	}
	public void setNational_state(Integer national_state) {
		this.national_state = national_state;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getOtherSource() {
		return otherSource;
	}
	public void setOtherSource(String otherSource) {
		this.otherSource = otherSource;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getDeli_date() {
		return deli_date;
	}
	public void setDeli_date(String deli_date) {
		this.deli_date = deli_date;
	}
	public String getDeli_man() {
		return deli_man;
	}
	public void setDeli_man(String deli_man) {
		this.deli_man = deli_man;
	}
	public Integer getCognize() {
		return cognize;
	}
	public void setCognize(Integer cognize) {
		this.cognize = cognize;
	}
	public String getDeli_opinion() {
		return deli_opinion;
	}
	public void setDeli_opinion(String deli_opinion) {
		this.deli_opinion = deli_opinion;
	}
	
}
