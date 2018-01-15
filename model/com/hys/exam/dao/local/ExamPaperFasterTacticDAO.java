package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamPaper;
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
	 * @param paper
	 */
	public void addExamPaperFasterTactic(ExamPaper paper);
	
	/**
	 * 删除快捷策略
	 * @param paper_id
	 */
	public void deleteExamPaperFasterTactic(Long paper_id);
	
	/**
	 * 通过试卷ID取策略模板
	 * @param id
	 * @return
	 */
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long paper_id);
	
	/**
	 * 通过试卷分类查询快捷策略列表
	 * @param tactic　
	 * @return
	 */
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(ExamPaperFasterTactic tactic);

}
