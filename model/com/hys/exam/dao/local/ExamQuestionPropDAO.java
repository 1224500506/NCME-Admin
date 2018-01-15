package com.hys.exam.dao.local;

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
	 * 批量添加试题属性
	 * @param questionPropList
	 * @return
	 */
	public void addQuestionProp(Map<String,List<ExamQuestionProp>> questionPropMap); 
	
	
	/**
	 * 批量添加试题属性
	 * @param ExamQuestionProp
	 * @return
	 */	
	public void addQuestionProp(ExamQuestionProp questionProp,String propKey); 
	
	/**
	 * 删除试题属性
	 * @param 试题id
	 * @return
	 */
	public void deleteQuestionPropByQuestionId(List<Object[]> id);
	
	/**
	 * 删除关联属性试题属性
	 * @param 试题id
	 */
	public void deleteQuestionProprByQuestionId(List<Object[]> id);

	
	/**
	 * 获取试题属性
	 * @param id
	 * @return
	 */
	public Map<String,List<ExamQuestionProp>> getQuestionPropByQuestionId(Long id);
	
	/**
	 * 试题属性是否存在
	 * @param tableName
	 * @param prop
	 * @return
	 */
	public boolean checkProp(String tableName,ExamQuestionProp prop);
}
