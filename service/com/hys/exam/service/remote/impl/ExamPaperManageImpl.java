package com.hys.exam.service.remote.impl;

import java.util.List;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.remote.ExamPaperDAO;
import com.hys.exam.dao.remote.ExamPaperFasterTacticDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.service.remote.ExamPaperManage;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 10, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamPaperManageImpl implements ExamPaperManage {
	
	private ExamPaperDAO remoteExamPaperDAO;
	
	private ExamPaperFasterTacticDAO remoteExamPaperFasterTacticDAO;
	
	public ExamPaperDAO getRemoteExamPaperDAO() {
		return remoteExamPaperDAO;
	}

	public void setRemoteExamPaperDAO(ExamPaperDAO remoteExamPaperDAO) {
		this.remoteExamPaperDAO = remoteExamPaperDAO;
	}

	public ExamPaperFasterTacticDAO getRemoteExamPaperFasterTacticDAO() {
		return remoteExamPaperFasterTacticDAO;
	}

	public void setRemoteExamPaperFasterTacticDAO(
			ExamPaperFasterTacticDAO remoteExamPaperFasterTacticDAO) {
		this.remoteExamPaperFasterTacticDAO = remoteExamPaperFasterTacticDAO;
	}

	@Override
	public Long addExamPaper(String key, ExamPaper paper) throws Exception {
		//保存策略
		if((null!=paper.getIsnot_save_tactic()) && (paper.getIsnot_save_tactic()==1)){
			//快捷策略
			if(paper.getPaper_mode() == Constants.PAPER_MODE_FT){
				remoteExamPaperFasterTacticDAO.addExamPaperFasterTactic(key,paper.getPaperFasterTactic());
			}
		}
		return remoteExamPaperDAO.addExamPaper(key,paper);
	}

	@Override
	public void deleteExamPaper(String key, Long[] id) throws Exception {
		remoteExamPaperDAO.deleteExamPaper(key, id);
	}

	@Override
	public void deleteExamPaperFasterTactic(String key, Long id)
			throws Exception {
		remoteExamPaperFasterTacticDAO.deleteExamPaperFasterTactic(key, id);
	}

	@Override
	public List<ExamPaper> getExamPaperAndChildPaper(String key, Long[] idArr)
			throws Exception {
		return remoteExamPaperDAO.getExamPaperAndChildPaper(key, idArr);
	}

	@Override
	public ExamPaper getExamPaperById(String key, Long id) throws Exception {
		return remoteExamPaperDAO.getExamPaperById(key, id);
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,
			Long id) throws Exception {
		return remoteExamPaperFasterTacticDAO.getExamPaperFasterTacticById(key, id);
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			String key, ExamPaperFasterTactic tactic) throws Exception {
		return remoteExamPaperFasterTacticDAO.getExamPaperFasterTacticByPaperTypeId(key, tactic);
	}

	@Override
	public Long getExamPaperId(String key) throws Exception {
		return remoteExamPaperDAO.getExamPaperId(key);
	}

	@Override
	public List<ExamPaper> getExamPaperList(String key,
			ExamPaperQuery examPaperQuery) throws Exception {
		return remoteExamPaperDAO.getExamPaperList(key, examPaperQuery);
	}

	@Override
	public List<ExamPaper> getExamPaperListByExamId(String key, Long examId)
			throws Exception {
		return remoteExamPaperDAO.getExamPaperListByExamId(key, examId);
	}

	@Override
	public int getExamPaperListSize(String key, ExamPaperQuery examPaperQuery)
			throws Exception {
		return remoteExamPaperDAO.getExamPaperListSize(key, examPaperQuery);
	}

	@Override
	public void updateBatchExamPaper(String key, ExamPaper paper, Long[] id)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateExamPaper(String key, ExamPaper paper) throws Exception {
		remoteExamPaperDAO.updateExamPaper(key, paper);
	}

	@Override
	public void updateExamPaperQuestion(String key, Long paperId,
			Long oldQuestionID, Long newQuestionId, Double score)
			throws Exception {
		remoteExamPaperDAO.updateExamPaperQuestion(key, paperId, oldQuestionID, newQuestionId, score);
	}

	@Override
	public void updateExamePaperTypeByPaperId(String key, Long paperTypeId,
			Long paperId) throws Exception {
		remoteExamPaperDAO.updateExamePaperTypeByPaperId(key, paperTypeId, paperId);
	}

}
