package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamQuestAtt;
import com.hys.exam.model.ExamQuestRelation;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-27
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamImportQuestAttDAO {

	/**
	 * 添加试题属性
	 * @param propList
	 */
	public void addQuestProp(List<ExamQuestAtt> propList);
	
	/**
	 * 添加系统试题属性
	 * @param propList
	 */
	public void addSysPropVal(List<ExamQuestAtt> propList);
	
	/**
	 * 添加系统属性上下级关系
	 */
	public void addSyspropValRelation(List<ExamQuestRelation> relationList);

}
