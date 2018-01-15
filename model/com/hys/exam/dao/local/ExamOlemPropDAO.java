package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamOlemProp;
import com.hys.exam.model.ExamOlemPropRefBaseProp;
import com.hys.exam.queryObj.ExamOlemPropQuery;
import com.hys.exam.returnObj.ExamReturnOlemProp;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-27
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamOlemPropDAO {
	
	/**
	 * 添加三基属性
	 * @param prop
	 */
	public void addExamOlemProp(ExamOlemProp prop);
	
	/**
	 * 批量添加三基属性
	 * @param propList
	 */
	public void addBatchExamOlemProp(List<ExamOlemProp> propList);
	
	/**
	 * 取seq
	 * @return
	 */
	public Long getId();
	
	
	/**
	 * 修改三基属性
	 * @param prop
	 */
	public void updateExamOlemProp(ExamOlemProp prop);
	
	/**
	 * 查三基属性
	 * @param prop
	 * @return
	 */
	public ExamOlemProp getExamOlemPropById(ExamOlemProp prop);
	
	/**
	 * 删除属性
	 * @param prop
	 */
	public boolean deleteExamOlemPropById(ExamOlemProp prop);
	
	
	/**
	 * 查询三基属性列表
	 * @param query
	 * @return
	 */
	public ExamReturnOlemProp getExamOlemPropList(ExamOlemPropQuery query);
	
	
	/**
	 * 增加三基属性与基本属性的关系
	 * @param prop
	 */
	public void addExamOlemPropRefBaseProp(List<ExamOlemPropRefBaseProp> prop);
	
	/**
	 * 删除三基属性与基本属性的关系
	 * @param prop
	 */
	public void deleteExamOlemPropRefBaseProp(ExamOlemPropRefBaseProp prop);
	
	/**
	 * 取对应基本属性
	 * @param id
	 * @return
	 */
	public List<ExamOlemPropRefBaseProp> getExamOlemPropRefBasePropList(Long id);
	
	/**
	 * 取关系基本属性类型
	 * @param id
	 * @return
	 */
	public int getExamOlemPropType(Long id);

}
