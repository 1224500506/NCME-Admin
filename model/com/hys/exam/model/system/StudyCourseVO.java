/**
 *
 * <p>Description: 学习课程</p>
 * @author chenlaibin
 * @version 1.0 2013-12-15
 */

package com.hys.exam.model.system;

import com.hys.exam.model.StudyCourse;

@SuppressWarnings("serial")
public class StudyCourseVO extends StudyCourse{

	private String creditType;		//类型
	
	private Double creditNum;		//课程分数
	
	private Integer isObligatory;	//1 必修, 0 选修
	
	private String creditName;		//名称
	
	private Integer weight;

	
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

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}				 
}


