package com.hys.exam.returnObj;

import java.util.List;

import com.hys.exam.model.ExamQuestion;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-16
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamReturnQuestion extends AbstractReturn<ExamQuestion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -383855668307341882L;
	
	private List<ExamQuestion> list;

	public List<ExamQuestion> getList() {
		return list;
	}

	public void setList(List<ExamQuestion> list) {
		this.list = list;
	}
	

}
