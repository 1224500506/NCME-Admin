package com.hys.exam.service.local;

import java.util.List;

import com.hys.exam.model.ExamPaperType;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.framework.service.BaseService;

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
public interface ExamPaperTypeManage extends BaseService {
	
	
	/**
	 * 增加试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType addExamPaperType(ExamPaperType ptype);
	
	/**
	 * 修改试卷分类
	 * @param ptype
	 * @return ExamPaperType
	 */
	public ExamPaperType updateExamPaperType(ExamPaperType ptype);
	
	/**
	 * 删除试卷分类
	 * @param ptype
	 */
	public void deleteExamPaperType(ExamPaperType ptype);
	
	/**
	 * 删除试卷分类
	 * @param ptype
	 */
	public void deleteExamPaperType(List<Long> delArray) ;
	
	/**
	 * 通过分类ID取试卷分类
	 * @param id
	 * @return ExamPaperType
	 */
	public ExamPaperType getExamPaperTypeById(Long id);

	/**
	 * 通过系统ID 查询 根结点,如果 idArr is null 则为所有系统根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(Integer[] idArr);
	
	/**
	 * 通过父ID 取子结点
	 * @param ExamPaperTypeQuery
	 * @return ExamReturnPaperType
	 */
	public ExamReturnPaperType getExamPaperTypeListByParentId(ExamPaperTypeQuery query);
	
	
	/**
	 * 移动节点
	 * 
	 * @param parent_id,code 
	 * @return
	 */
	public void updateMovePaperType(ExamPaperType ptype);
	
	/**
	 * 通过ID 查询 根结点
	 * @param idArr
	 * @return List<ExamPaperType>
	 */
	public List<ExamPaperType> getExamPaperTypeRootListById(Integer[] idArr);
	
	/**
	 * 通过id 查询所有的上级节点
	 * @param id
	 * @return
	 */
	public List<ExamPaperType> getExamPaperTypeRootListByChildId(Long id);

}
