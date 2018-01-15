package com.hys.exam.dao.remote;

import java.sql.SQLException;
import java.util.List;

import com.hys.exam.model.ExamQuestionPropValCascade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-11-4
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionPropValCascadeDAO {
	
	/**
	 * 取试题属性级联关系
	 * @param id
	 * @return
	 */
	public ExamQuestionPropValCascade getQuestionPropValCascadeById(String key,Long id) throws SQLException;
	
	/**
	 * 增加试题属性级联关系
	 * @param id
	 * @return
	 */	
	public void addQuestionPropValCascade(String key,List<ExamQuestionPropValCascade> questPropVal) throws SQLException;
	
	/**
	 * 修改试题属性级联关系
	 * @param id
	 * @return
	 */		
	public void updateQuestionPropValCascade(String key,ExamQuestionPropValCascade questPropVal) throws SQLException;
	
	/**
	 * 删除试题属性级联关系
	 * @param questPropVal
	 */
	public void deleteQuestionPropValCascade(String key,List<ExamQuestionPropValCascade> questPropVal) throws SQLException;

	/**
	 * 删除试题属性级联关系
	 * @param id
	 */
	public void deleteQuestionPropValCascadeByQuestionId(String key,List<Object[]> id) throws SQLException;

}
