package com.hys.exam.sessionfacade.local;

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
	public List<ExamQuestionType> getQuestionTypeRootBySysId(Integer[] idArr) throws FrameworkRuntimeException;
	
	
	/**
	 * 取所有root结点
	 * @return
	 */
	public List<ExamQuestionType> getQuestionTypeRootById(Integer[] idArr) throws FrameworkRuntimeException;
	

	/**
	 * 通过分类ID取试题分类及系统ID
	 * @param id
	 * @return
	 */
	public List<ExamQuestionType> getSubSysTypeByTypeId(Long[] idArr) throws FrameworkRuntimeException;
	

	
	/**
	 * 增加试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType addExamQuestType(ExamQuestionType qtype)  throws FrameworkRuntimeException;
	
	/**
	 * 修改试题分类
	 * @param ExamQuestionType qtype
	 * @return ExamQuestionType
	 */
	public ExamQuestionType updateExamQuestTypeById(ExamQuestionType qtype)  throws FrameworkRuntimeException;
	
	/**
	 * 通过分类ID删除试题分类
	 * @param ExamQuestionType qtype
	 */
	public boolean deleteExamQuestTypeById(ExamQuestionType qtype)  throws FrameworkRuntimeException;
	
	/**
	 * 通过分类ID取试题分类
	 * @param Long id
	 * @return ExamQuestionType
	 */
	public ExamQuestionType getExamQuestionTypeById(Long id) throws FrameworkRuntimeException;
	
	
	/**
	 * 通过父ID查子节点
	 * @param ExamQuestionTypeQuery
	 * @return ExamReturnQuestionType
	 */
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(ExamQuestionTypeQuery query) throws FrameworkRuntimeException;

	
	/**
	 * 移动节点
	 * 
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMoveQuestionType(ExamQuestionType qtype) throws FrameworkRuntimeException;
	
	/**
	 * 通过id 查询所有的上级节点
	 * @param id
	 * @return
	 */
	public List<ExamQuestionType> getExamQuestionTypeRootListByChildId(Long id) throws FrameworkRuntimeException;
}
