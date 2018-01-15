package com.hys.exam.service.local.impl;

import java.util.List;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.local.ExamPaperDAO;
import com.hys.exam.dao.local.ExamPaperFasterTacticDAO;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.UserImage;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.service.local.ExamPaperManage;
import com.hys.exam.utils.PageList;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.service.impl.BaseMangerImpl;

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
public class ExamPaperManageImpl extends BaseMangerImpl implements ExamPaperManage {
	private ExamPaperDAO localExamPaperDAO;	
	private ExamPaperFasterTacticDAO localExamPaperFasterTacticDAO;
	
	/**
	 * 试卷是否使用，YHQ，2017-05-17
	 * @param usingExamPaper examPaper
	 * @return 试卷id
	 */
	@Override
	public boolean  usingExamPaper(ExamPaper examPaper) throws FrameworkRuntimeException {
		return localExamPaperDAO.usingExamPaper(examPaper) ;
	}

	@Override
	public Long addExamPaper(ExamPaper paper) {
		Long result = localExamPaperDAO.addExamPaper(paper);
		if (result != -1L && result != 0L) {
			//保存策略
			
			if((null!=paper.getIsnot_save_tactic()) && (paper.getIsnot_save_tactic()==1)) {
				//
				if (paper.getPaper_mode().equals(Constants.PAPER_MODE_JX) || paper.getPaper_mode().equals(Constants.PAPER_MODE_KJ1)){
					if (paper.getChild_paper_num() != 0) {
						for (ExamPaper childPaper : paper.getChildExamPaperList()) {
							localExamPaperFasterTacticDAO.addExamPaperFasterTactic(childPaper);		
						}
					} else {
						localExamPaperFasterTacticDAO.addExamPaperFasterTactic(paper);
					}
				}
				else if (paper.getPaper_mode().equals(Constants.PAPER_MODE_JZJ)) {	// 4  : 卷中卷
					localExamPaperFasterTacticDAO.addExamPaperFasterTactic(paper);
				}
			}	
		}
		return result;		
	}

	@Override
	public void deleteExamPaper(Long[] id) {
		localExamPaperDAO.deleteExamPaper(id);
	}

	@Override
	public ExamPaper getExamPaperById(Long id) {
		return localExamPaperDAO.getExamPaperById(id);
	}

	@Override
	public Long getExamPaperId() {
		return localExamPaperDAO.getExamPaperId();
	}

	@Override
	public List<ExamPaper> getExamPaperList(PageList pl, ExamPaperQuery examPaperQuery, String createDateFrom, String createDateTo) {
		return localExamPaperDAO.getExamPaperList(pl, examPaperQuery, createDateFrom, createDateTo);
	}

	@Override
	public void updateBatchExamPaper(ExamPaper paper, Long[] id) {
	}

	@Override
	public String updateExamPaper(ExamPaper paper) {
		return localExamPaperDAO.updateExamPaper(paper);
	}

	@Override
	public int getExamPaperListSize(ExamPaperQuery examPaperQuery) {
		return localExamPaperDAO.getExamPaperListSize(examPaperQuery);
	}

	@Override
	public List<ExamPaper> getExamPaperListByExamId(Long examId) {
		return localExamPaperDAO.getExamPaperListByExamId(examId);
	}

	@Override
	public List<ExamPaper> getExamPaperAndChildPaper(Long[] idArr) {
		return localExamPaperDAO.getExamPaperAndChildPaper(idArr);
	}

	@Override
	public void deleteExamPaperFasterTactic(Long id) {
		localExamPaperFasterTacticDAO.deleteExamPaperFasterTactic(id);		
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long id) {
		return localExamPaperFasterTacticDAO.getExamPaperFasterTacticById(id);
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			ExamPaperFasterTactic tactic) {
		return localExamPaperFasterTacticDAO.getExamPaperFasterTacticByPaperTypeId(tactic);
	}

	@Override
	public void updateExamePaperTypeByPaperId(Long paperTypeId, Long paperId) {
		localExamPaperDAO.updateExamePaperTypeByPaperId(paperTypeId, paperId);
	}

	@Override
	public void updateExamPaperQuestion(Long paperId, Long oldQuestionID,
			Long newQuestionId, Double score) {
		localExamPaperDAO.updateExamPaperQuestion(paperId, oldQuestionID, newQuestionId, score);
	}

	@Override
	public int getExamCountByPaperIds(Integer labelId, String paperIds) {
		return localExamPaperDAO.getExamCountByPaperIds(labelId, paperIds);
	}

	@Override
	public boolean updateContral(ExamPaperQuery paper) {
		return localExamPaperDAO.updateContral(paper);		 
	}
	
	public ExamPaperDAO getLocalExamPaperDAO() {
		return localExamPaperDAO;
	}
	public void setLocalExamPaperDAO(ExamPaperDAO localExamPaperDAO) {
		this.localExamPaperDAO = localExamPaperDAO;
	}
	public ExamPaperFasterTacticDAO getLocalExamPaperFasterTacticDAO() {
		return localExamPaperFasterTacticDAO;
	}
	public void setLocalExamPaperFasterTacticDAO(ExamPaperFasterTacticDAO localExamPaperFasterTacticDAO) {
		this.localExamPaperFasterTacticDAO = localExamPaperFasterTacticDAO;
	}
	

}
