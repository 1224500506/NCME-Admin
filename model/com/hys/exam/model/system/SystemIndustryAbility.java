package com.hys.exam.model.system;

import com.hys.exam.model.BaseObject;

/**
* <p>Description: 岗位能力</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:33:25
*/
@SuppressWarnings("serial")
public class SystemIndustryAbility extends BaseObject{

	private Long id;			//id
	
	private String abilityName;	//岗位能力名称
	
	private Long industryId;	//行业id
		
	private Integer status;		//1 -正常 -1 -删除

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbilityName() {
		return abilityName;
	}

	public void setAbilityName(String abilityName) {
		this.abilityName = abilityName;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}


