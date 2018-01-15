package com.hys.exam.sessionfacade.remote;

import java.util.List;

import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.BaseSessionFacade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-12-13
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamQuestionTypeFacade extends BaseSessionFacade {
	
	
	/**
	 * 取所有root结点
	 * @return
	 */
	public List<ExamQuestionType> getQuestionTypeRootBySysId(String key,Integer[] idArr) throws FrameworkRuntimeException;
	
	
	/**
	 * 取所有root结点
	 * @return
	 */
	public List<ExamQuestionType> getQuestionTypeRootById(String key,Integer[] idArr) throws FrameworkRuntimeException;
	

	/**
	 * 通过分类ID取试题分类及系统ID
	 * @param id
	 * @return
	 */
	public List<ExamQuestionType> getSubSysTypeByTypeId(String key,Long[] idArr) throws FrameworkRuntimeException;
	

	
	/**
	 * 增加试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType addExamQuestType(String key,ExamQuestionType qtype)  throws FrameworkRuntimeException;
	
	/**
	 * 修改试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType updateExamQuestTypeById(String key,ExamQuestionType qtype)  throws FrameworkRuntimeException;
	
	/**
	 * 通过分类ID删除试题分类
	 * @param ExamQuestionType qtype
	 */
	public boolean deleteExamQuestTypeById(String key,ExamQuestionType qtype)  throws FrameworkRuntimeException;
	
	/**
	 * 通过分类ID取试题分类
	 * @param Long id
	 * @return ExamQuestionType
	 */
	public ExamQuestionType getExamQuestionTypeById(String key,Long id) throws FrameworkRuntimeException;
	
	
	/**
	 * 通过父ID查子节点
	 * @param ExamQuestionTypeQuery
	 * @return ExamReturnQuestionType
	 */
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(String key,ExamQuestionTypeQuery query) throws FrameworkRuntimeException;

	
	/**
	 * 移动节点
	 * 
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMoveQuestionType(String key,ExamQuestionType qtype) throws FrameworkRuntimeException;
}
