package com.hys.exam.dao.remote;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamQuestionProp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-13
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionPropDAO {
	
	/**
	 * 添加试题属性
	 * @param questionPropList
	 * @return
	 */
	public void addQuestionProp(String key,Map<String,List<ExamQuestionProp>> questionPropMap) throws SQLException;
	
	/**
	 * 删除试题属性
	 * @param 试题id
	 * @return
	 */
	public void deleteQuestionPropByQuestionId(String key,List<Object[]> id) throws SQLException;
	
	/**
	 * 删除关联属性试题属性
	 * @param 试题id
	 */
	public void deleteQuestionProprByQuestionId(String key,List<Object[]> id) throws SQLException;

	
	/**
	 * 获取试题属性
	 * @param id
	 * @return
	 */
	public Map<String,List<ExamQuestionProp>> getQuestionPropByQuestionId(String key,Long id) throws SQLException;
	
	/**
	 * 试题属性是否存在
	 * @param tableName
	 * @param prop
	 * @return
	 */
	public boolean checkProp(String key,String tableName,ExamQuestionProp prop) throws SQLException;
}
