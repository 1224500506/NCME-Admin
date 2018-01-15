package com.hys.exam.model.system;

import com.hys.exam.model.BaseObject;

/**
* <p>Description: 能力课程关联</p>
* @author chenlaibin
* @version 1.0 2013-12-10 下午9:33:25
*/
@SuppressWarnings("serial")
public class SystemAbilityCourse extends BaseObject{

	private Long abilityId;			//岗位能力id
	
	private Long courseId;			//课程id
	
	private String creditType;		//关联 ncme_credit_type
	
	private Double creditNum;		//课程学分数如：5
	
	private Integer isObligatory;	//0 -选修 1 -必修
	

	public Long getAbilityId() {
		return abilityId;
	}

	public void setAbilityId(Long abilityId) {
		this.abilityId = abilityId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public Double getCreditNum() {
		return creditNum;
	}

	public void setCreditNum(Double creditNum) {
		this.creditNum = creditNum;
	}

	public Integer getIsObligatory() {
		return isObligatory;
	}

	public void setIsObligatory(Integer isObligatory) {
		this.isObligatory = isObligatory;
	}
}


