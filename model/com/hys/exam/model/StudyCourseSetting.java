/**
 *
 * <p>课程设置</p>
 * @author chenlaibin
 * @version 1.0 2014-4-14
 */

package com.hys.exam.model;

import java.util.Date;

public class StudyCourseSetting {

	private Long id;
	
	private Double time;
	
	private Integer status;
	
	private String creator;
	
	private Date createDate;
	
	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}


