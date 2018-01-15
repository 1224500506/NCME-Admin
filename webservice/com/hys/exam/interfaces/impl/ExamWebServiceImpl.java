package com.hys.exam.interfaces.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hys.exam.interfaces.ExamWebService;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.queryObj.ExamExaminTypeQuery;
import com.hys.exam.queryObj.ExamExaminationQuery;
import com.hys.exam.queryObj.ExamPaperQuery;
import com.hys.exam.queryObj.ExamPaperTypeQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.queryObj.ExamQuestionTypeQuery;
import com.hys.exam.returnObj.ExamReturnExaminType;
import com.hys.exam.returnObj.ExamReturnExamination;
import com.hys.exam.returnObj.ExamReturnPaper;
import com.hys.exam.returnObj.ExamReturnPaperType;
import com.hys.exam.returnObj.ExamReturnQuestion;
import com.hys.exam.returnObj.ExamReturnQuestionType;
import com.hys.exam.sessionfacade.remote.ExamExaminTypeFacade;
import com.hys.exam.sessionfacade.remote.ExamExaminationFacade;
import com.hys.exam.sessionfacade.remote.ExamPaperFacade;
import com.hys.exam.sessionfacade.remote.ExamPaperTypeFacade;
import com.hys.exam.sessionfacade.remote.ExamPropValFacade;
import com.hys.exam.sessionfacade.remote.ExamQuestionFacade;
import com.hys.exam.sessionfacade.remote.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.remote.ExamQuestionTypeFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;

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
public class ExamWebServiceImpl extends BaseWebServiceImpl implements
		ExamWebService {
	
	
	private ExamPropValFacade remoteExamPropValFacade;
	private ExamQuestionLabelFacade remoteExamQuestionLabelFacade;
	private ExamQuestionTypeFacade remoteExamQuestionTypeFacade;
	private ExamQuestionFacade remoteExamQuestionFacade;
	private ExamPaperTypeFacade remoteExamPaperTypeFacade;
	private ExamPaperFacade remoteExamPaperFacade;
	private ExamExaminTypeFacade remoteExamExaminTypeFacade;
	private ExamExaminationFacade remoteExamExaminationFacade;
	
	public ExamPropValFacade getRemoteExamPropValFacade() {
		return remoteExamPropValFacade;
	}

	public void setRemoteExamPropValFacade(ExamPropValFacade remoteExamPropValFacade) {
		this.remoteExamPropValFacade = remoteExamPropValFacade;
	}

	public ExamQuestionLabelFacade getRemoteExamQuestionLabelFacade() {
		return remoteExamQuestionLabelFacade;
	}

	public void setRemoteExamQuestionLabelFacade(
			ExamQuestionLabelFacade remoteExamQuestionLabelFacade) {
		this.remoteExamQuestionLabelFacade = remoteExamQuestionLabelFacade;
	}

	public ExamQuestionTypeFacade getRemoteExamQuestionTypeFacade() {
		return remoteExamQuestionTypeFacade;
	}

	public void setRemoteExamQuestionTypeFacade(
			ExamQuestionTypeFacade remoteExamQuestionTypeFacade) {
		this.remoteExamQuestionTypeFacade = remoteExamQuestionTypeFacade;
	}

	public ExamQuestionFacade getRemoteExamQuestionFacade() {
		return remoteExamQuestionFacade;
	}

	public void setRemoteExamQuestionFacade(
			ExamQuestionFacade remoteExamQuestionFacade) {
		this.remoteExamQuestionFacade = remoteExamQuestionFacade;
	}

	public ExamPaperTypeFacade getRemoteExamPaperTypeFacade() {
		return remoteExamPaperTypeFacade;
	}

	public void setRemoteExamPaperTypeFacade(
			ExamPaperTypeFacade remoteExamPaperTypeFacade) {
		this.remoteExamPaperTypeFacade = remoteExamPaperTypeFacade;
	}

	public ExamPaperFacade getRemoteExamPaperFacade() {
		return remoteExamPaperFacade;
	}

	public void setRemoteExamPaperFacade(ExamPaperFacade remoteExamPaperFacade) {
		this.remoteExamPaperFacade = remoteExamPaperFacade;
	}

	public ExamExaminTypeFacade getRemoteExamExaminTypeFacade() {
		return remoteExamExaminTypeFacade;
	}

	public void setRemoteExamExaminTypeFacade(
			ExamExaminTypeFacade remoteExamExaminTypeFacade) {
		this.remoteExamExaminTypeFacade = remoteExamExaminTypeFacade;
	}

	public ExamExaminationFacade getRemoteExamExaminationFacade() {
		return remoteExamExaminationFacade;
	}

	public void setRemoteExamExaminationFacade(
			ExamExaminationFacade remoteExamExaminationFacade) {
		this.remoteExamExaminationFacade = remoteExamExaminationFacade;
	}

	@Override
	public ExamExaminType addExamExaminType(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.addExamExaminType(key, etype);
	}

	@Override
	public Long addExamExamination(String key, ExamExamination exam)
			throws FrameworkRuntimeException {
		return remoteExamExaminationFacade.addExamination(key, exam);
	}

	@Override
	public Long addExamPaper(String key, ExamPaper paper)
			throws FrameworkRuntimeException {
		return remoteExamPaperFacade.addExamPaper(key, paper);
	}

	@Override
	public ExamPaperType addExamPaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.addExamPaperType(key, ptype);
	}

	@Override
	public ExamQuestionType addExamQuestType(String key, ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.addExamQuestType(key, qtype);
	}

	@Override
	public ExamQuestion addExamQuestion(String key, ExamQuestion quest)
			throws FrameworkRuntimeException {
		return remoteExamQuestionFacade.addQuestion(key, quest);
	}

	@Override
	public boolean deleteExamExaminTypeById(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.deleteExamExaminTypeById(key, etype);
	}

	@Override
	public void deleteExamPaper(String key, Long[] id)
			throws FrameworkRuntimeException {
		remoteExamPaperFacade.deleteExamPaper(key, id);
	}

	@Override
	public void deleteExamPaperFasterTactic(String key, Long id)
			throws FrameworkRuntimeException {
		remoteExamPaperFacade.deleteExamPaperFasterTactic(key, id);
	}

	@Override
	public boolean deleteExamPaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.deleteExamPaperType(key, ptype);
	}

	@Override
	public boolean deleteExamQuestTypeById(String key, ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.deleteExamQuestTypeById(key, qtype);
	}

	@Override
	public void deleteExamination(String key, List<Long> id)
			throws FrameworkRuntimeException {
		remoteExamExaminationFacade.deleteExamination(key, id);
	}

	@Override
	public Map<String, List<ExamPropVal>> getBasePropVal(String key)
			throws FrameworkRuntimeException {
		return remoteExamPropValFacade.getSysPropValBySysId(key);
	}

	@Override
	public List<ExamPropValTemp> getBasePropValRal(String key, Integer type)
			throws FrameworkRuntimeException {
		return remoteExamPropValFacade.getBasePropVal(key, 0, type);
	}

	@Override
	public ExamExaminType getExamExaminTypeById(String key, Long id)
			throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.getExamExaminTypeById(key, id);
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(String key,
			ExamExaminTypeQuery query) throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.getExamExaminTypeListByParentId(key, query);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(String key,
			Integer[] id) throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.getExamExaminTypeRootById(key, id);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(String key,
			Integer[] id) throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.getExamExaminTypeRootBySysId(key, id);
	}

	@Override
	public ExamExamination getExamExaminationById(String key, Long id)
			throws FrameworkRuntimeException {
		return remoteExamExaminationFacade.getExamExaminationById(key, id);
	}

	@Override
	public ExamReturnExamination getExamExaminationList(String key,
			ExamExaminationQuery query) throws FrameworkRuntimeException {
		ExamReturnExamination re = new ExamReturnExamination();
		re.setReturnList(remoteExamExaminationFacade.getExaminationList(key,query));
		re.setTotal_count(remoteExamExaminationFacade.getExaminationListSize(key,query));
		return re;
	}

	@Override
	public ExamPaper getExamPaperById(String key, Long id)
			throws FrameworkRuntimeException {
		return remoteExamPaperFacade.getExamPaperById(key, id);
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(String key,
			Long id) throws FrameworkRuntimeException {
		return remoteExamPaperFacade.getExamPaperFasterTacticById(key, id);
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			String key, ExamPaperFasterTactic tactic)
			throws FrameworkRuntimeException {
		return remoteExamPaperFacade.getExamPaperFasterTacticByPaperTypeId(key, tactic);
	}

	@Override
	public ExamReturnPaper getExamPaperList(String key, ExamPaperQuery query)
			throws FrameworkRuntimeException {
		ExamReturnPaper rPaper = new ExamReturnPaper();
		rPaper.setReturnList(remoteExamPaperFacade.getExamPaperList(key,query));
		rPaper.setTotal_count(remoteExamPaperFacade.getExamPaperListSize(key,query));
		return rPaper;
	}

	@Override
	public ExamReturnPaper getExamPaperListByExamId(String key, Long examId,
			Long paperId) throws FrameworkRuntimeException {
		try{
			ExamReturnPaper examReturnPaper = new ExamReturnPaper();
			List<ExamPaper> returnList = new ArrayList<ExamPaper>();
			// 考场的试卷列表
			List<ExamPaper> paperList = remoteExamPaperFacade
					.getExamPaperListByExamId(key,examId);
	
			// 考场是否有已有考过的试卷
			boolean is_papper = false;
	
			if (paperList!=null && !paperList.isEmpty()) {
				int size = paperList.size();
				if (paperId != null && paperId > 0) {
					for (int i = 0; i < size; i++) {
						if (paperList.get(i).getId().toString().equals(
								paperId.toString())) {
							if (i == (size - 1)) {
								returnList.add(paperList.get(0));
								is_papper = true;
								break;
							} else {
								returnList.add(paperList.get(i + 1));
								is_papper = true;
								break;
							}
						}
					}
	
					if (is_papper) { // 考场有已有考过的试卷
						examReturnPaper.setReturnList(returnList);
					} else { // 考场没有已有考过的试卷
						returnList.add(paperList.get(0));
						examReturnPaper.setReturnList(returnList);
					}
				} else {
					returnList.add(paperList.get(0));
					examReturnPaper.setReturnList(returnList);
				}
			} else {
				return null;
			}
			
			return examReturnPaper;
			
		}catch(Exception e){
			throw new FrameworkRuntimeException("试卷出现异常!");
		}
	}

	@Override
	public ExamPaperType getExamPaperTypeById(String key, Long id)
			throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.getExamPaperTypeById(key, id);
	}

	@Override
	public ExamReturnPaperType getExamPaperTypeListByParentId(String key,
			ExamPaperTypeQuery query) throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.getExamPaperTypeListByParentId(key, query);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.getExamPaperTypeRootListById(key, idArr);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.getExamPaperTypeRootListBySysId(key, idArr);
	}

	@Override
	public ExamQuestion getExamQuestionById(String key, Long id)
			throws FrameworkRuntimeException {
		return remoteExamQuestionFacade.getQuestionById(key, id);
	}

	@Override
	public ExamReturnQuestion getExamQuestionList(String key,
			ExamQuestionQuery query) throws FrameworkRuntimeException {
		ExamReturnQuestion rq = new ExamReturnQuestion();
		rq.setList(remoteExamQuestionFacade.getQuestionList(key,query));
		rq.setTotal_count(remoteExamQuestionFacade.getQuestionSize(key,query));
		return rq;
	}

	@Override
	public ExamQuestionType getExamQuestionTypeById(String key, Long id)
			throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.getExamQuestionTypeById(key, id);
	}

	@Override
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(String key,
			ExamQuestionTypeQuery query) throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.getExamQuestionTypeListByParentId(key, query);
	}

	@Override
	public List<ExamQuestionType> getExamQuestionTypeRootBySysId(String key,
			Integer[] id) throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.getQuestionTypeRootBySysId(key, id);
	}

	@Override
	public ExamReturnExamination getExaminationListByIds(String key,
			ExamExaminationQuery query) throws FrameworkRuntimeException {
		ExamReturnExamination re = new ExamReturnExamination();
		re.setReturnList(remoteExamExaminationFacade.getExaminationListByIds(key,query));
		re.setTotal_count(remoteExamExaminationFacade.getExaminationListByIdsSize(key,query));
		return re;
	}

	@Override
	public ExamPaper getQuestSizeByFasterTactic(String key, ExamPaper paper)
			throws FrameworkRuntimeException {
		return remoteExamPaperFacade.getQuestSizeByFasterTactic(key, paper);
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(String key, Long[] idArr)
			throws FrameworkRuntimeException {
		return remoteExamQuestionFacade.getQuestionIdByIdArr(key, idArr);
	}

	@Override
	public List<ExamQuestionLabel> getQuestionLabel(String key, int type)
			throws FrameworkRuntimeException {
		return remoteExamQuestionLabelFacade.getQuestionLabel(key, type);
	}

	@Override
	public ExamReturnQuestion getQuestionListByPaperId(String key,
			Long paperId, int order) throws FrameworkRuntimeException {
		ExamReturnQuestion returnQuestion = new ExamReturnQuestion();
		List<ExamQuestion> returnList = remoteExamQuestionFacade
				.getQuestionListByPaperId(key,paperId);
		if (0 == order) {
			returnQuestion.setReturnList(returnList);
		} else {
			returnQuestion.setReturnList(order(returnList));
		}
		return returnQuestion;
	}

	@Override
	public int getQuestionListSize(String key, ExamPaperTactic paperTactic)
			throws FrameworkRuntimeException {
		return remoteExamQuestionFacade.getQuestionListSize(key, paperTactic);
	}

	@Override
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(String key,
			ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException {
		return remoteExamQuestionFacade.getQuestionSizeByFasterTactic(key, paperTactic);
	}

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(String key,
			Integer[] idArr) throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.getQuestionTypeRootById(key, idArr);
	}

	@Override
	public void updateExaminationTypeByExamId(String key, Long typeId, Long id)
			throws FrameworkRuntimeException {
		remoteExamExaminationFacade.updateExaminationTypeByExamId(key, typeId, id);
	}

	@Override
	public ExamExaminType updateExamExaminType(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.updateExamExaminType(key, etype);
	}

	@Override
	public void updateExamExamination(String key, ExamExamination exam)
			throws FrameworkRuntimeException {
		remoteExamExaminationFacade.updateExaminationById(key, exam);
	}

	@Override
	public void updateExamPaper(String key, ExamPaper paper)
			throws FrameworkRuntimeException {
		remoteExamPaperFacade.updateExamPaper(key, paper);
	}

	@Override
	public ExamPaperType updateExamPaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.updateExamPaperType(key, ptype);
	}

	@Override
	public ExamQuestionType updateExamQuestTypeById(String key,
			ExamQuestionType qtype) throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.updateExamQuestTypeById(key, qtype);
	}

	@Override
	public void updateExamQuestionById(String key, ExamQuestion quest)
			throws FrameworkRuntimeException {
		remoteExamQuestionFacade.updateQuestionById(key, quest);
	}

	@Override
	public void updateExamQuestionStateByIds(String key, List<Long> idArr,
			int state) throws FrameworkRuntimeException {
		remoteExamQuestionFacade.updateQuestionStateByIdArr(key, idArr, state);
	}

	@Override
	public void updateExamePaperTypeByPaperId(String key, Long paperTypeId,
			Long paperId) throws FrameworkRuntimeException {
		remoteExamPaperFacade.updateExamePaperTypeByPaperId(key, paperTypeId, paperId);
	}

	@Override
	public boolean updateMoveExaminType(String key, ExamExaminType etype)
			throws FrameworkRuntimeException {
		return remoteExamExaminTypeFacade.updateMoveExaminType(key, etype);
	}

	@Override
	public boolean updateMovePaperType(String key, ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return remoteExamPaperTypeFacade.updateMovePaperType(key, ptype);
	}

	@Override
	public boolean updateMoveQuestionType(String key, ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return remoteExamQuestionTypeFacade.updateMoveQuestionType(key, qtype);
	}
	
	@Override
	public void updateExamPaperQuestion(String key, Long paperId,
			Long oldQuestionID, Long newQuestionId, Double score)
			throws FrameworkRuntimeException {
		remoteExamPaperFacade.updateExamPaperQuestion(key, paperId, oldQuestionID, newQuestionId, score);
	}
	
	/**
	 * 打乱顺序
	 * @param list
	 * @return
	 */
	private List<ExamQuestion> order(List<ExamQuestion> list) {
		List<ExamQuestion> orderList = new ArrayList<ExamQuestion>();
		try {
			for (int i = list.size(); i > 0; i--) {
				int index = (int) (Math.random() * i);
				orderList.add(list.get(index));
				list.remove(index);
			}
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000);
		}
		return orderList;
	}

}
