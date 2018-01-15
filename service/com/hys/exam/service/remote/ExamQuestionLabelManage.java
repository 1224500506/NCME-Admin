package com.hys.exam.service.remote;

import java.util.List;

import com.hys.exam.model.ExamQuestionLabel;

/**
 * 
 * 
 * 作者：Tony 2010-10-19
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionLabelManage {
	
	/**
	 * 取试题题型 
	 * @param type 0: 取所有 throws Exception; 1： 不是子试题题型
 	 * @return
	 */
	public List<ExamQuestionLabel> getQuestionLabel(String key,int type) throws Exception;

}
