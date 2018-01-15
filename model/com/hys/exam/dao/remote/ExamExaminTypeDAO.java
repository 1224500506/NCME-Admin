package com.hys.exam.dao.remote;

import java.sql.SQLException;
import java.util.List;

import com.hys.exam.model.ExamExaminType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-1-10
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamExaminTypeDAO {

	
	/**
	 * 增加考试分类
	 * @param ExamExaminType etype
	 * @return ExamExaminType
	 */
	public ExamExaminType addExamExaminType(String key,ExamExaminType etype) throws SQLException;
	
	/**
	 * 修改考试分类
	 * @param ExamExaminType etype
	 * @return ExamExaminType
	 */
	public ExamExaminType updateExamExaminType(String key,ExamExaminType etype) throws SQLException;
	
	/**
	 * 通过分类ID删除考试分类
	 * @param ExamExaminType etype
	 */
	public void deleteExamExaminTypeById(String key,ExamExaminType etype) throws SQLException;
	
	/**
	 * 通过分类ID取考试分类
	 * @param Long id
	 * @return ExamExaminType
	 */
	public ExamExaminType getExamExaminTypeById(String key,Long id) throws SQLException;
	
	/**
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param id
	 * @return List<ExamExaminType>
	 */
	public List<ExamExaminType> getExamExaminTypeRootBySysId(String key,Integer[] id) throws SQLException;
	
	
	/**
	 * 通过ID 查询 根结点
	 * @param id
	 * @return List<ExamExaminType>
	 */
	public List<ExamExaminType> getExamExaminTypeRootById(String key,Integer[] id) throws SQLException;
	
	/**
	 * 通过父ID 取子结点
	 * @param ExamExaminTypeQuery
	 * @return ExamReturnExaminType
	 */
	public ExamReturnExaminType getExamExaminTypeListByParentId(String key,ExamExaminTypeQuery query) throws SQLException;
	
	/**
	 * 移动节点
	 * 
	 * @param parent_id,code 
	 * @return
	 */	
	public void updateMoveExaminType(String key,ExamExaminType etype) throws SQLException;
	
}
