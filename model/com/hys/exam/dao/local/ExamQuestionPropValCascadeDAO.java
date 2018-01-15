package com.hys.exam.dao.local;

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
	public ExamQuestionPropValCascade getQuestionPropValCascadeById(Long id);
	
	/**
	 * 增加试题属性级联关系
	 * @param id
	 * @return
	 */	
	public void addQuestionPropValCascade(List<ExamQuestionPropValCascade> questPropVal);
	
	/**
	 * 修改试题属性级联关系
	 * @param id
	 * @return
	 */		
	public void updateQuestionPropValCascade(ExamQuestionPropValCascade questPropVal);
	
	/**
	 * 删除试题属性级联关系
	 * @param questPropVal
	 */
	public void deleteQuestionPropValCascade(List<ExamQuestionPropValCascade> questPropVal);

	/**
	 * 删除试题属性级联关系
	 * @param id
	 */
	public void deleteQuestionPropValCascadeByQuestionId(List<Object[]> id);

}
