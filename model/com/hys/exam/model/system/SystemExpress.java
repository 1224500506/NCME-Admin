/**
 *
 * <p>快递</p>
 * @author chenlaibin
 * @version 1.0 2015-8-13
 */

package com.hys.exam.model.system;

import java.util.Date;

import com.hys.exam.model.BaseObject;

@SuppressWarnings("serial")
public class SystemExpress extends BaseObject{

	private Long id;
	
	private String code;
	
	private String name;
	
	private String site;
	
	private String description;
	
	private Date createDate;
	
	private Integer status;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}


