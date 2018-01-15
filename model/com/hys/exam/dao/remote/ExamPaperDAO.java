package com.hys.exam.dao.remote;

import java.sql.SQLException;
import java.util.List;

import com.hys.exam.model.ExamPaper;
import com.hys.exam.queryObj.ExamPaperQuery;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-2-18
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPaperDAO {
	
	/**
	 * 添加试卷返回试卷ID
	 * @param paper
	 * @return
	 */
	public Long addExamPaper(String key,ExamPaper paper) throws SQLException;
	
	/**
	 * 取ID
	 * @return
	 */
	public Long getExamPaperId(String key) throws SQLException;
	
	/**
	 * 取试卷
	 * @return
	 */
	public ExamPaper getExamPaperById(String key,Long id) throws SQLException;
	
	/**
	 * 删除试卷
	 * @param id
	 */
	public void deleteExamPaper(String key,Long[] id) throws SQLException;
	
	/**
	 * 修改试卷
	 * @param paper
	 */
	public void updateExamPaper(String key,ExamPaper paper) throws SQLException;
	
	/**
	 * 试卷查询分页
	 * @param ExamPaper
	 * @return
	 */
	public List<ExamPaper> getExamPaperList(String key,ExamPaperQuery examPaperQuery) throws SQLException;
	public int getExamPaperListSize(String key,ExamPaperQuery examPaperQuery) throws SQLException;
	
	
	/**
	 * 通过考场ID 取 试卷列表
	 * @param examId
	 * @return
	 */
	public List<ExamPaper> getExamPaperListByExamId(String key,Long examId) throws SQLException;
	
	/**
	 * 查询主试卷和子试卷的List
	 * @param idArr
	 * @return
	 */
	public List<ExamPaper> getExamPaperAndChildPaper(String key,Long[] idArr) throws SQLException;
	
	/**
	 * 通过试卷ID修改试卷分类
	 * @param paper_type_id 试卷分类ID
	 * @param paperId	试卷ID
	 */
	public void updateExamePaperTypeByPaperId(String key,Long paper_type_id,Long paperId) throws SQLException;
	
	/**
	 * 更换试卷试题
	 * @param paperId
	 * @param oldQuestionID
	 * @param newQuestionId
	 * @param score
	 */
	public void updateExamPaperQuestion(String key,Long paperId,Long oldQuestionID,Long newQuestionId,Double score) throws SQLException;
}
