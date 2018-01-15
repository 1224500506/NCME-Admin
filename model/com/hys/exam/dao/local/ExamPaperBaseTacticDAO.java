package com.hys.exam.dao.local;

import java.util.List;

import com.hys.exam.model.ExamPaperBaseTactic;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2011-4-8
 * 
 * 描述：
 * 
 * 说明:
 */
public interface ExamPaperBaseTacticDAO {
	
	public void addExamPaperBaseTactic(List<ExamPaperBaseTactic> tacticList);
	
	public void deleteExamPaperBaseTacticByPaperId(Long paperId);
	
}
