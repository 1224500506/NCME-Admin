package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamQuestionKey;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：tony 2009-12-29
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionKeyDAO {
	
	/**
	 * 写入试题答案
	 * @param key
	 */
	public int[] addQuestionKey(List<ExamQuestionKey> key);
	
	/**
	 * 删除试题答案
	 * @param 试题id
	 */
	public int[] deleteQuestionKey(List<Object[]> id);
	
	/**
	 * 取试题答案
	 * @param 试题id 
	 * @return
	 */
	public List<ExamQuestionKey> getQuestionKeys(Long id);
	

}
