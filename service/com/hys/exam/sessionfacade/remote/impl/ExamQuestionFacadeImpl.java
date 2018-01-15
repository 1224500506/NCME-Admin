package com.hys.exam.sessionfacade.remote.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.service.remote.ExamQuestionManage;
import com.hys.exam.sessionfacade.remote.ExamQuestionFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;
import com.hys.framework.sessionfacade.impl.BaseSessionFacadeImpl;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 13, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamQuestionFacadeImpl extends BaseSessionFacadeImpl implements
		ExamQuestionFacade {

	private ExamQuestionManage remoteExamQuestionManage;
	
	public ExamQuestionManage getRemoteExamQuestionManage() {
		return remoteExamQuestionManage;
	}

	public void setRemoteExamQuestionManage(
			ExamQuestionManage remoteExamQuestionManage) {
		this.remoteExamQuestionManage = remoteExamQuestionManage;
	}



	@Override
	public ExamQuestion addQuestion(String key, ExamQuestion quest)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.addQuestion(key,quest);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void addQuestionProp(String key,
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal, int status)
			throws FrameworkRuntimeException {
		try {
			remoteExamQuestionManage.addQuestionProp(key,questionPropMap, id,
					questPropVal,status);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public boolean deleteQuesitons(String key, List<Long> id)
			throws FrameworkRuntimeException {
		boolean flag = false;
		try {
			remoteExamQuestionManage.deleteQuesitons(key,id);
			flag = true;
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			//throw new FrameworkRuntimeException(ErrorCode.ec00000);
			flag = false;
		} finally {
			return flag;
		}
	}

	@Override
	public List<ExamQuestion> getCompareQuestion(String key,
			List<ExamQuestion> questList) throws FrameworkRuntimeException {
		try {
			List<ExamQuestion> resultList = new ArrayList<ExamQuestion>();
			for(ExamQuestion quest : questList){
				List<ExamQuestion> list = remoteExamQuestionManage.getQuestionByContentAndLabel(key,quest);
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

	@Override
	public ExamQuestion getQuestionById(String key, Long id)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionById(key,id);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(String key, Long[] idArr)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionIdByIdArr(key,idArr);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIds(String key, List<Long> ids)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionIdByIds(key,ids);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionList(String key,
			ExamQuestionQuery questQuery) throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionList(key,questQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionList(String key,
			ExamPaperTactic paperTactic, int pageSize, int currentPage)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionList(key,paperTactic, pageSize, currentPage);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionListByPaperId(String key, Long paperId)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionListByPaperId(key,paperId);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public int getQuestionListSize(String key, ExamPaperTactic paperTactic)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionListSize(key,paperTactic);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public int getQuestionSize(String key, ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionSize(key,questQuery);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(String key,
			ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException {
		try {
			return remoteExamQuestionManage.getQuestionSizeByFasterTactic(key,paperTactic);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public void updateQuestionById(String key, ExamQuestion quest)
			throws FrameworkRuntimeException {
		try {
			remoteExamQuestionManage.updateQuestionById(key,quest);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}

	}

	@Override
	public void updateQuestionStateByIdArr(String key, List<Long> id, int state)
			throws FrameworkRuntimeException {
		try {
			remoteExamQuestionManage.updateQuestionStateByIdArr(key,id, state);
		} catch (Exception e) {
			logger.error(ErrorCode.ec00000, e);
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

}
