package com.hys.exam.model;

import java.io.Serializable;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Dec 9, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionLabelNum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7903481691865873411L;

	private Integer labelId;
	
	private Integer num;

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}
