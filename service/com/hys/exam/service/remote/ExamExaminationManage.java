package com.hys.exam.service.remote;

import java.util.List;

import com.hys.exam.model.ExamExamination;
import com.hys.exam.queryObj.ExamExaminationQuery;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-14
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamExaminationManage {

	
	/**
	 * 增加考场
	 * @param ExamExamination exam
	 * @return 考场ID
	 */
	public Long addExamination(String key,ExamExamination exam) throws Exception;
	
	/**
	 * 批量删除考场
	 * @param Long id
	 * @return
	 */
	public void deleteExamination(String key,List<Long> id) throws Exception;
	
	
	/**
	 * 修改考场含考场试卷
	 * @param ExamExamination exam
	 */
	public void updateExaminationById(String key,ExamExamination exam) throws Exception;
	
	/**
	 * 获取考场信息和考场试卷
	 * @param id
	 * @return
	 */
	public ExamExamination getExamExaminationById(String key,Long id) throws Exception;
	
	/**
	 * 
	 * FunName:getExaminationList
	 * Description: 查询考试列表
	 * @param examExaminationQuery 考试查询对象
	 * @return: List<ExamExamination>
	 */
	public List<ExamExamination> getExaminationList(String key,ExamExaminationQuery examExaminationQuery) throws Exception;

	/**
	 * 
	 * FunName:getExaminationListSize
	 * Description: 查询考试列表总条数
	 * @param examExaminationQuery 考试查询对象
	 * @return: int
	 */
	public int getExaminationListSize(String key,ExamExaminationQuery examExaminationQuery) throws Exception;
	
	
	
	/**
	 * 通过考试分类取考试列表
	 * @param examTypeId 考试分类ID
	 * @return 考试列表 List<ExamExamination>
	 */
	public List<ExamExamination> getExaminationListByExamTypeId(String key,Long examTypeId) throws Exception;
	
	/**
	 * 通过考试ID 数组取考试列表
	 * @param query
	 * @return
	 */
	public List<ExamExamination> getExaminationListByIds(String key,ExamExaminationQuery query) throws Exception;
	
	/**
	 * 通过考试ID 数组取size
	 * @param query
	 * @return
	 */
	public int getExaminationListByIdsSize(String key,ExamExaminationQuery query) throws Exception;
	
	
	/**
	 * 通过考试ID 修改考试分类
	 * @param typeId 分类ID
	 * @param id 考试ID
	 */
	public void updateExaminationTypeByExamId(String key,Long typeId,Long id) throws Exception;
	
}
