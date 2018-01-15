package com.hys.exam.sessionfacade.local.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionImportTemplate;
import com.hys.exam.model.ExamQuestionLabelNum;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.service.local.ExamQuestionManage;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony 2010-10-18
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionFacadeImpl extends BaseSessionFacadeImpl implements
		ExamQuestionFacade {

	private ExamQuestionManage localExamQuestionManage;

	public ExamQuestionManage getLocalExamQuestionManage() {
		return localExamQuestionManage;
	}

	public void setLocalExamQuestionManage(
			ExamQuestionManage localExamQuestionManage) {
		this.localExamQuestionManage = localExamQuestionManage;
	}

	@Override
	public void addImportQuestion(List<ExamQuestion> questList)
			throws FrameworkRuntimeException {
		try {
			localExamQuestionManage.addImportQuestion(questList);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}

	}

	@Override
	public ExamQuestion addQuestion(ExamQuestion quest)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.addQuestion(quest);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@SuppressWarnings("finally")
	public boolean deleteQuesitons(List<Long> id)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			localExamQuestionManage.deleteQuesitons(id);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public ExamQuestion getQuestionById(Long id)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionById(id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionList(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionList(questQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}
	
	@Override
	public List<ExamQuestion> getQuestionListForZyy(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionListForZyy(questQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}	

	public void updateQuestionById(ExamQuestion quest)
			throws FrameworkRuntimeException {
		try {
			localExamQuestionManage.updateQuestionById(quest);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}

	}

	public int updateQuestionStateByIdArr(List<Long> id, int state, String author, String opinion)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.updateQuestionStateByIdArr(id, state, author, opinion);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public int getQuestionSize(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionSize(questQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public List<ExamQuestion> getQuestionIdByIds(List<Long> ids)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionIdByIds(ids);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void addQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal,int status)
			throws FrameworkRuntimeException {
		try {
			localExamQuestionManage.addQuestionProp(questionPropMap, id,
					questPropVal,status);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getCompareQuestion(List<ExamQuestion> questList)
			throws FrameworkRuntimeException {
		try {
			List<ExamQuestion> resultList = new ArrayList<ExamQuestion>();
			for(ExamQuestion quest : questList){
				List<ExamQuestion> list = localExamQuestionManage.getQuestionByContentAndLabel(quest);
				if(null!=list && !list.isEmpty()){
					resultList.add(quest);
					resultList.addAll(list);
				}
			}
			return resultList;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionIdByIdArr(idArr);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionListByPaperId(Long paperId)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionListByPaperId(paperId);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public List<ExamQuestion> getQuestionList(ExamPaperTactic paperTactic,
			int pageSize, int currentPage) throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionList(paperTactic, pageSize, currentPage);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	public int getQuestionListSize(ExamPaperTactic paperTactic)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionListSize(paperTactic);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(
			ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionSizeByFasterTactic(paperTactic);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void addImportQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal, int status)
			throws FrameworkRuntimeException {
		try {
			localExamQuestionManage.addImportQuestionProp(questionPropMap, id,
					questPropVal,status);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamPaperAndPaper> getQuestionLableNumByPaperIDArr(
			Long[] paperIdArr) throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionLableNumByPaperIDArr(paperIdArr);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestionImportTemplate> getCompareQuestion2(List<ExamQuestionImportTemplate> questList)
			throws FrameworkRuntimeException {
		try {
			for(ExamQuestionImportTemplate quest : questList){
				if(quest.getParent_id()==0){
					ExamQuestion q = new ExamQuestion();
					q.setQuestion_label_id(quest.getQuestionLabel());
					q.setContent(quest.getContent());
					List<ExamQuestion> list = localExamQuestionManage.getQuestionByContentAndLabel(q);
					if(null!=list && !list.isEmpty()){
						quest.setStatus(1);
					}
				}
			}
			return questList;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionAllList(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionAllList(questQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionListErrorPropId(Integer type)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionListErrorPropId(type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionByContentAndLabel(ExamQuestion quest)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionByContentAndLabel(quest);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void addQuestionSubSysType(
			List<ExamSubSysQuestType> subSysQuestTypeList)
			throws FrameworkRuntimeException {
		try {
			localExamQuestionManage.addQuestionSubSysType(subSysQuestTypeList);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionListByPropVal(Long propId, Integer type)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionListByPropVal(propId, type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean getQuestionSubSysTypeBySysId(Long SysId, Long questionId)
			throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionSubSysTypeBySysId(SysId, questionId);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestionLabelNum> getQuestionLabelNumByPropId(Long propId,
			Integer type) throws FrameworkRuntimeException {
		try {
			return localExamQuestionManage.getQuestionLabelNumByPropId(propId, type);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void updateQuestionSourceId(Long oldId, Long newId) {
		try {
			localExamQuestionManage.updateQuestionSourceId(oldId, newId);
		} catch (Exception e) {
		}
	}
	
	
	
}
