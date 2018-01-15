package com.hys.exam.model;

import java.io.Serializable;
import java.util.List;

import com.hys.xiangyi.model.XiangYiProvince;

public class Sentence implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6244319531794294629L;
	
	private Long id;
	private String title;
	private Integer type;
	private String deli_date;
	private Integer state;
	private Integer ordernum;
	private String content;
	private List<SystemSite> siteList;
	private Long site_id;
	private String source;
	private List<XiangYiProvince> xiangYiProvinceList;
	private String start_date;
	private String end_date;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDeli_date() {
		return deli_date;
	}

	public void setDeli_date(String deli_date) {
		this.deli_date = deli_date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<SystemSite> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SystemSite> siteList) {
		this.siteList = siteList;
	}

	public Long getSite_id() {
		return site_id;
	}

	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}

	public List<XiangYiProvince> getXiangYiProvinceList() {
		return xiangYiProvinceList;
	}

	public void setXiangYiProvinceList(List<XiangYiProvince> xiangYiProvinceList) {
		this.xiangYiProvinceList = xiangYiProvinceList;
	}
	
	
}
