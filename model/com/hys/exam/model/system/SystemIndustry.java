package com.hys.exam.model.system;

import com.hys.exam.model.BaseObject;

/**
*
* <p>Description: 行业岗位</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:27:01
*/

@SuppressWarnings("serial")
public class SystemIndustry extends BaseObject{

	private Long id;				//id
	private Long parentId;			//上级id
	private String industryName;	//行业名称
	private Integer status;			//状态  1：正常, -1：删除
	private Integer seq;			//排序
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}


