package com.hys.exam.dao.remote;

import java.sql.SQLException;
import java.util.List;

import com.hys.exam.model.ExamPaperFasterTactic;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-5-9
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPaperFasterTacticDAO {
	
	/**
	 * 添加快捷策略
	 * @param Tactic
	 */
	public void addExamPaperFasterTactic(String key,ExamPaperFasterTactic tactic) throws SQLException;
	
	/**
	 * 删除快捷策略
	 * @param id
	 */
	public void deleteExamPaperFasterTactic(String key,Long id) throws SQLException;
	
	/**
	 * 通过ID取策略模板
	 * @param id
	 * @return
	 */
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,Long id) throws SQLException;
	
	/**
	 * 通过试卷分类查询快捷策略列表
	 * @param tactic　
	 * @return
	 */
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(String key,ExamPaperFasterTactic tactic) throws SQLException;

}
