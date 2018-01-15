package com.hys.exam.sessionfacade.remote;

import java.util.List;

import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.BaseSessionFacade;

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
public interface ExamPaperTypeFacade extends BaseSessionFacade {

	
	/**
	 * 增加试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType addExamPaperType(String key,ExamPaperType ptype) throws FrameworkRuntimeException;
	
	/**
	 * 修改试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType updateExamPaperType(String key,ExamPaperType ptype) throws FrameworkRuntimeException;
	
	/**
	 * 删除试卷分类
	 * @param ptype
	 */
	public boolean deleteExamPaperType(String key,ExamPaperType ptype) throws FrameworkRuntimeException;
	
	/**
	 * 通过分类ID取试卷分类
	 * @param id
	 * @return ExamPaperType
	 */
	public ExamPaperType getExamPaperTypeById(String key,Long id) throws FrameworkRuntimeException;

	/**
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(String key,Integer[] idArr) throws FrameworkRuntimeException;
	
	/**
	 * 通过父ID 取子结点
	 * @param ExamPaperTypeQuery
	 * @return ExamReturnPaperType
	 */
	public ExamReturnPaperType getExamPaperTypeListByParentId(String key,ExamPaperTypeQuery query) throws FrameworkRuntimeException;
	
	/**
	 * 移动节点
	 * 
	 * @param parent_id,code 
	 * @return
	 */
	public boolean updateMovePaperType(String key,ExamPaperType ptype)  throws FrameworkRuntimeException;
	
	/**
	 * 通过ID 查询 根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListById(String key,Integer[] idArr) throws FrameworkRuntimeException;
}
