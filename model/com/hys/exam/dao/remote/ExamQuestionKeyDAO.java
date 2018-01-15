package com.hys.exam.dao.remote;

import java.sql.SQLException;
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
	public int[] addQuestionKey(String key,List<ExamQuestionKey> questionKeyList) throws SQLException;
	
	/**
	 * 删除试题答案
	 * @param 试题id
	 */
	public int[] deleteQuestionKey(String key,List<Object[]> id)throws SQLException;
	
	/**
	 * 取试题答案
	 * @param 试题id 
	 * @return
	 */
	public List<ExamQuestionKey> getQuestionKeys(String key,Long id) throws SQLException;
	

}
