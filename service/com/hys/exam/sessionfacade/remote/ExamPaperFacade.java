package com.hys.exam.sessionfacade.remote;

import java.util.List;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.BaseSessionFacade;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-3-9
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPaperFacade extends BaseSessionFacade {

	
	/**
	 * 添加试卷返回试卷ID
	 * @param paper
	 * @return
	 */
	public Long addExamPaper(String key,ExamPaper paper) throws FrameworkRuntimeException;

	
	/**
	 * 取试卷
	 * @return
	 */
	public ExamPaper getExamPaperById(String key,Long id) throws FrameworkRuntimeException;
	
	/**
	 * 删除试卷
	 * @param id
	 */
	public void deleteExamPaper(String key,Long[] id) throws FrameworkRuntimeException;
	
	/**
	 * 修改试卷
	 * @param paper
	 */
	public void updateExamPaper(String key,ExamPaper paper) throws FrameworkRuntimeException;
	
	/**
	 * 试卷查询分页
	 * @param ExamPaper
	 * @return
	 */
	public List<ExamPaper> getExamPaperList(String key,ExamPaperQuery examPaperQuery) throws FrameworkRuntimeException;
	public int getExamPaperListSize(String key,ExamPaperQuery examPaperQuery) throws FrameworkRuntimeException;
	
	
	
	/**
	 * 通过考场ID 取 试卷列表
	 * @param examId
	 * @return
	 */
	public List<ExamPaper> getExamPaperListByExamId(String key,Long examId) throws FrameworkRuntimeException;
	
	/**
	 * 删除快捷策略
	 * @param id
	 */
	public void deleteExamPaperFasterTactic(String key,Long id) throws FrameworkRuntimeException;
	
	/**
	 * 通过ID取策略模板
	 * @param id
	 * @return
	 */
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,Long id) throws FrameworkRuntimeException;
	
	/**
	 * 通过试卷分类查询快捷策略列表
	 * @param tactic　
	 * @return
	 */
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(String key,ExamPaperFasterTactic tactic) throws FrameworkRuntimeException;
	
	/**
	 * 通过试卷ID修改试卷分类
	 * @param paper_type_id 试卷分类ID
	 * @param paperId	试卷ID
	 */
	public void updateExamePaperTypeByPaperId(String key,Long paper_type_id,Long paperId) throws FrameworkRuntimeException;
	
	/**
	 * 查询符合策略条件试题数
	 * @param paper
	 * @return
	 * @throws FrameworkRuntimeException
	 */
	public ExamPaper getQuestSizeByFasterTactic(String key,ExamPaper paper) throws FrameworkRuntimeException;
	
	/**
	 * 更换试卷试题
	 * @param paperId
	 * @param oldQuestionID
	 * @param newQuestionId
	 * @param score
	 */
	public void updateExamPaperQuestion(String key,Long paperId,Long oldQuestionID,Long newQuestionId,Double score) throws FrameworkRuntimeException;

}
