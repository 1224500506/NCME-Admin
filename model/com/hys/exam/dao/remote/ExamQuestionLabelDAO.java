package com.hys.exam.dao.remote;

import java.sql.SQLException;
import java.util.List;

import com.hys.exam.model.ExamQuestionLabel;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-19
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionLabelDAO {
	
	
	/**
	 * 取试题题型 
	 * @param type 0: 取所有; 1： 不是子试题题型
 	 * @return
	 */
	public List<ExamQuestionLabel> getQuestionLabel(String key,int type) throws SQLException;

}
