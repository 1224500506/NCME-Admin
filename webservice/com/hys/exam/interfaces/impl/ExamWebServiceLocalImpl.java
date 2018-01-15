package com.hys.exam.interfaces.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hys.exam.interfaces.ExamWebServiceLocal;
import com.hys.exam.model.ExamExaminType;
import com.hys.exam.model.ExamExamination;
import com.hys.exam.model.ExamPaper;
import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamPaperType;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamPropValTemp;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabel;
import com.hys.exam.model.ExamQuestionType;
import com.hys.exam.model.ExamSubSysQuestType;
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
import com.hys.exam.sessionfacade.local.ExamExaminTypeFacade;
import com.hys.exam.sessionfacade.local.ExamExaminationFacade;
import com.hys.exam.sessionfacade.local.ExamPaperFacade;
import com.hys.exam.sessionfacade.local.ExamPaperTypeFacade;
import com.hys.exam.sessionfacade.local.ExamPropValFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionLabelFacade;
import com.hys.exam.sessionfacade.local.ExamQuestionTypeFacade;
import com.hys.framework.exception.ErrorCode;
import com.hys.framework.exception.FrameworkRuntimeException;

/**
 * 
 * 标题：考试支撑平台
 * 
 * 作者：Tony Jun 16, 2011
 * 
 * 描述：
 * 
 * 说明:
 */
public class ExamWebServiceLocalImpl implements ExamWebServiceLocal {
	
	/**
	 * 属性
	 */
	private ExamPropValFacade localExamPropValFacade;
	
	/**
	 * 试题题型
	 */
	private ExamQuestionLabelFacade localExamQuestionLabelFacade;
	
	/**
	 * 考试
	 */
	private ExamExaminationFacade localExamExaminationFacade;
	
	/**
	 * 考试分类
	 */
	private ExamExaminTypeFacade localExamExaminTypeFacade;
	
	/**
	 * 试卷分类
	 */
	private ExamPaperTypeFacade localExamPaperTypeFacade;
	
	/**
	 * 试卷
	 */
	private ExamPaperFacade localExamPaperFacade;
	
	/**
	 * 试题分类
	 */
	private ExamQuestionTypeFacade localExamQuestionTypeFacade;
	
	/**
	 * 试题
	 */
	private ExamQuestionFacade localExamQuestionFacade;	
	

	public ExamPropValFacade getLocalExamPropValFacade() {
		return localExamPropValFacade;
	}

	public void setLocalExamPropValFacade(ExamPropValFacade localExamPropValFacade) {
		this.localExamPropValFacade = localExamPropValFacade;
	}

	public ExamQuestionLabelFacade getLocalExamQuestionLabelFacade() {
		return localExamQuestionLabelFacade;
	}

	public void setLocalExamQuestionLabelFacade(
			ExamQuestionLabelFacade localExamQuestionLabelFacade) {
		this.localExamQuestionLabelFacade = localExamQuestionLabelFacade;
	}

	public ExamExaminationFacade getLocalExamExaminationFacade() {
		return localExamExaminationFacade;
	}

	public void setLocalExamExaminationFacade(
			ExamExaminationFacade localExamExaminationFacade) {
		this.localExamExaminationFacade = localExamExaminationFacade;
	}

	public ExamExaminTypeFacade getLocalExamExaminTypeFacade() {
		return localExamExaminTypeFacade;
	}

	public void setLocalExamExaminTypeFacade(
			ExamExaminTypeFacade localExamExaminTypeFacade) {
		this.localExamExaminTypeFacade = localExamExaminTypeFacade;
	}

	public ExamPaperTypeFacade getLocalExamPaperTypeFacade() {
		return localExamPaperTypeFacade;
	}

	public void setLocalExamPaperTypeFacade(
			ExamPaperTypeFacade localExamPaperTypeFacade) {
		this.localExamPaperTypeFacade = localExamPaperTypeFacade;
	}

	public ExamPaperFacade getLocalExamPaperFacade() {
		return localExamPaperFacade;
	}

	public void setLocalExamPaperFacade(ExamPaperFacade localExamPaperFacade) {
		this.localExamPaperFacade = localExamPaperFacade;
	}

	public ExamQuestionTypeFacade getLocalExamQuestionTypeFacade() {
		return localExamQuestionTypeFacade;
	}

	public void setLocalExamQuestionTypeFacade(
			ExamQuestionTypeFacade localExamQuestionTypeFacade) {
		this.localExamQuestionTypeFacade = localExamQuestionTypeFacade;
	}

	public ExamQuestionFacade getLocalExamQuestionFacade() {
		return localExamQuestionFacade;
	}

	public void setLocalExamQuestionFacade(
			ExamQuestionFacade localExamQuestionFacade) {
		this.localExamQuestionFacade = localExamQuestionFacade;
	}


	//==============================================================================
	
	@Override
	public ExamExaminType addExamExaminType(ExamExaminType etype)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.addExamExaminType(etype);
	}

	@Override
	public Long addExamExamination(ExamExamination exam)
			throws FrameworkRuntimeException {
		return localExamExaminationFacade.addExamination(exam);
	}

	@Override
	public Long addExamPaper(ExamPaper paper) throws FrameworkRuntimeException {
		return localExamPaperFacade.addExamPaper(paper);
	}

	@Override
	public ExamPaperType addExamPaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.addExamPaperType(ptype);
	}

	@Override
	public ExamQuestionType addExamQuestType(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.addExamQuestType(qtype);
	}

	@Override
	public ExamQuestion addExamQuestion(ExamQuestion quest)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.addQuestion(quest);
	}

	@Override
	public boolean deleteExamExaminTypeById(ExamExaminType etype)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.deleteExamExaminTypeById(etype);
	}

	@Override
	public void deleteExamPaper(Long[] id) throws FrameworkRuntimeException {
		localExamPaperFacade.deleteExamPaper(id);
	}

	@Override
	public void deleteExamPaperFasterTactic(Long id)
			throws FrameworkRuntimeException {
		localExamPaperFacade.deleteExamPaperFasterTactic(id);
	}

	@Override
	public boolean deleteExamPaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.deleteExamPaperType(ptype);
	}

	@Override
	public boolean deleteExamQuestTypeById(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.deleteExamQuestTypeById(qtype);
	}

	@Override
	public void deleteExamination(List<Long> id)
			throws FrameworkRuntimeException {
		localExamExaminationFacade.deleteExamination(id);
	}

	@Override
	public Map<String, List<ExamPropVal>> getBasePropVal()
			throws FrameworkRuntimeException {
		return localExamPropValFacade.getSysPropValBySysId();
	}

	@Override
	public List<ExamPropValTemp> getBasePropValRal(Integer type)
			throws FrameworkRuntimeException {
		return localExamPropValFacade.getBasePropVal(type);
	}

	@Override
	public ExamExaminType getExamExaminTypeById(Long id)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.getExamExaminTypeById(id);
	}

	@Override
	public ExamReturnExaminType getExamExaminTypeListByParentId(
			ExamExaminTypeQuery query) throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.getExamExaminTypeListByParentId(query);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootById(Integer[] id)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.getExamExaminTypeRootById(id);
	}

	@Override
	public List<ExamExaminType> getExamExaminTypeRootBySysId(Integer[] id)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.getExamExaminTypeRootBySysId(id);
	}

	@Override
	public ExamExamination getExamExaminationById(Long id)
			throws FrameworkRuntimeException {
		return localExamExaminationFacade.getExamExaminationById(id);
	}

	@Override
	public ExamReturnExamination getExamExaminationList(
			ExamExaminationQuery query) throws FrameworkRuntimeException {
		
		ExamReturnExamination re = new ExamReturnExamination();
		re.setReturnList(localExamExaminationFacade.getExaminationList(query));
		re.setTotal_count(localExamExaminationFacade.getExaminationListSize(query));
		
		return re;
	}

	@Override
	public ExamPaper getExamPaperById(Long id) throws FrameworkRuntimeException {
		return localExamPaperFacade.getExamPaperById(id);
	}

	@Override
	public ExamPaperFasterTactic getExamPaperFasterTacticById(Long id)
			throws FrameworkRuntimeException {
		return localExamPaperFacade.getExamPaperFasterTacticById(id);
	}

	@Override
	public List<ExamPaperFasterTactic> getExamPaperFasterTacticByPaperTypeId(
			ExamPaperFasterTactic tactic) throws FrameworkRuntimeException {
		return localExamPaperFacade.getExamPaperFasterTacticByPaperTypeId(tactic);
	}

	@Override
	public ExamReturnPaper getExamPaperList(ExamPaperQuery query)
			throws FrameworkRuntimeException {
		
		ExamReturnPaper rPaper = new ExamReturnPaper();
		rPaper.setReturnList(localExamPaperFacade.getExamPaperList(null, query, null, null));
		rPaper.setTotal_count(localExamPaperFacade.getExamPaperListSize(query));
		
		return rPaper;
	}

	@Override
	public ExamReturnPaper getExamPaperByExamId(Long examId, Long paperId)
			throws FrameworkRuntimeException {
		
		try{
			ExamReturnPaper examReturnPaper = new ExamReturnPaper();
			List<ExamPaper> returnList = new ArrayList<ExamPaper>();
			// 考场的试卷列表
			List<ExamPaper> paperList = localExamPaperFacade
					.getExamPaperListByExamId(examId);
	
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
			throw new FrameworkRuntimeException("试卷出现异常!",e);
		}
	}

	@Override
	public ExamPaperType getExamPaperTypeById(Long id)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.getExamPaperTypeById(id);
	}

	@Override
	public ExamReturnPaperType getExamPaperTypeListByParentId(
			ExamPaperTypeQuery query) throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.getExamPaperTypeListByParentId(query);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListById(Integer[] idArr)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.getExamPaperTypeRootListById(idArr);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListBySysId(Integer[] idArr)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.getExamPaperTypeRootListBySysId(idArr);
	}

	@Override
	public ExamQuestion getExamQuestionById(Long id)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionById(id);
	}

	@Override
	public ExamReturnQuestion getExamQuestionList(ExamQuestionQuery query)
			throws FrameworkRuntimeException {
		
		ExamReturnQuestion rq = new ExamReturnQuestion();
		rq.setList(localExamQuestionFacade.getQuestionList(query));
		rq.setTotal_count(localExamQuestionFacade.getQuestionSize(query));
		
		return rq;
	}

	@Override
	public ExamQuestionType getExamQuestionTypeById(Long id)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.getExamQuestionTypeById(id);
	}

	@Override
	public ExamReturnQuestionType getExamQuestionTypeListByParentId(
			ExamQuestionTypeQuery query) throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.getExamQuestionTypeListByParentId(query);
	}

	@Override
	public List<ExamQuestionType> getExamQuestionTypeRootBySysId(Integer[] id)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.getQuestionTypeRootBySysId(id);
	}

	@Override
	public ExamReturnExamination getExaminationListByIds(
			ExamExaminationQuery query) throws FrameworkRuntimeException {
		
		ExamReturnExamination re = new ExamReturnExamination();
		re.setReturnList(localExamExaminationFacade.getExaminationListByIds(query));
		re.setTotal_count(localExamExaminationFacade.getExaminationListByIdsSize(query));
		
		return re;
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionIdByIdArr(idArr);
	}

	@Override
	public List<ExamQuestionLabel> getQuestionLabel(int type)
			throws FrameworkRuntimeException {
		return localExamQuestionLabelFacade.getQuestionLabel(type);
	}

	@Override
	public ExamReturnQuestion getQuestionListByPaperId(Long paperId, int order)
			throws FrameworkRuntimeException {
		
		try {
			ExamReturnQuestion returnQuestion = new ExamReturnQuestion();
			List<ExamQuestion> returnList = localExamQuestionFacade
					.getQuestionListByPaperId(paperId);
			if (0 == order) {
				returnQuestion.setReturnList(returnList);
			} else {
				returnQuestion.setReturnList(order(returnList));
			}
			return returnQuestion;
		} catch (Exception e) {
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
		
	}

	@Override
	public int getQuestionListSize(ExamPaperTactic paperTactic)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionListSize(paperTactic);
	}

	@Override
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(
			ExamPaperFasterTactic paperTactic) throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionSizeByFasterTactic(paperTactic);
	}

	@Override
	public List<ExamQuestionType> getQuestionTypeRootById(Integer[] idArr)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.getQuestionTypeRootById(idArr);
	}

	@Override
	public ExamExaminType updateExamExaminType(ExamExaminType etype)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.updateExamExaminType(etype);
	}

	@Override
	public void updateExamExamination(ExamExamination exam)
			throws FrameworkRuntimeException {
		localExamExaminationFacade.updateExaminationById(exam);
	}

	@Override
	public void updateExamPaper(ExamPaper paper)
			throws FrameworkRuntimeException {
		localExamPaperFacade.updateExamPaper(paper);
	}

	@Override
	public ExamPaperType updateExamPaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.updateExamPaperType(ptype);
	}

	@Override
	public ExamQuestionType updateExamQuestTypeById(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.updateExamQuestTypeById(qtype);
	}

	@Override
	public void updateExamQuestionById(ExamQuestion quest)
			throws FrameworkRuntimeException {
		localExamQuestionFacade.updateQuestionById(quest);
	}

	@Override
	public void updateExamQuestionStateByIds(List<Long> idArr, int state,String author, String opinion)
			throws FrameworkRuntimeException {
		localExamQuestionFacade.updateQuestionStateByIdArr(idArr, state, author, opinion);
	}

	@Override
	public boolean updateMoveExaminType(ExamExaminType etype)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.updateMoveExaminType(etype);
	}

	@Override
	public boolean updateMovePaperType(ExamPaperType ptype)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.updateMovePaperType(ptype);
	}

	@Override
	public boolean updateMoveQuestionType(ExamQuestionType qtype)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade.updateMoveQuestionType(qtype);
	}
	
	@Override
	public void updateExaminationTypeByExamId(Long typeId, Long id)
			throws FrameworkRuntimeException {
		localExamExaminationFacade.updateExaminationTypeByExamId(typeId, id);
	}

	@Override
	public void updateExamePaperTypeByPaperId(Long paperTypeId, Long paperId)
			throws FrameworkRuntimeException {
		localExamPaperFacade.updateExamePaperTypeByPaperId(paperTypeId, paperId);
	}

	@Override
	public ExamPaper getQuestSizeByFasterTactic(ExamPaper paper)
			throws FrameworkRuntimeException {
		return localExamPaperFacade.getQuestSizeByFasterTactic(paper);
	}
	
	@Override
	public List<ExamPaperAndPaper> getQuestionLableNumByPaperIDArr(
			Long[] paperIdArr) throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionLableNumByPaperIDArr(paperIdArr);
	}
	
	
	public List<ExamQuestionType> getExamQuestionTypeRootListByChildId(Long id)
			throws FrameworkRuntimeException {
		return localExamQuestionTypeFacade
				.getExamQuestionTypeRootListByChildId(id);
	}
	@Override
	public List<ExamExaminType> getExamExaminTypeRootListByChildId(Long id)
			throws FrameworkRuntimeException {
		return localExamExaminTypeFacade.getExamExaminTypeRootListByChildId(id);
	}

	@Override
	public List<ExamPaperType> getExamPaperTypeRootListByChildId(Long id)
			throws FrameworkRuntimeException {
		return localExamPaperTypeFacade.getExamPaperTypeRootListByChildId(id);
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
			throw new FrameworkRuntimeException("试题排序错误！",e);
		}
		return orderList;
	}

	@Override
	public List<ExamQuestion> getQuestionAllList(ExamQuestionQuery questQuery)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionAllList(questQuery);
	}

	@Override
	public ExamReturnPaper getExamPaperListByExamId(Long examId)
			throws FrameworkRuntimeException {
		try {
			ExamReturnPaper examReturnPaper = new ExamReturnPaper();
			// 考场的试卷列表
			List<ExamPaper> paperList = localExamPaperFacade
					.getExamPaperListByExamId(examId);

			examReturnPaper.setReturnList(paperList);

			return examReturnPaper;

		} catch (Exception e) {
			throw new FrameworkRuntimeException("试卷出现异常!", e);
		}
	}

	@Override
	public List<ExamQuestion> getQuestionListErrorPropId(Integer type)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionListErrorPropId(type);
	}

	@Override
	public List<ExamQuestion> getQuestionByContentAndLabel(ExamQuestion quest)
			throws FrameworkRuntimeException {
		return localExamQuestionFacade.getQuestionByContentAndLabel(quest);
	}

	public boolean addQuestionSubSysType(ExamSubSysQuestType ExamSubSysQuestType)
			throws FrameworkRuntimeException {
		List<ExamSubSysQuestType> list = new ArrayList<ExamSubSysQuestType>();
		try{
			Long[] idArr = {ExamSubSysQuestType.getSub_type_id()};
			List<ExamQuestionType> typeList = localExamQuestionTypeFacade.getSubSysTypeByTypeId(idArr);
			
			if(typeList.isEmpty()){
				return false;
			}else{
				ExamSubSysQuestType.setSub_sys_id(typeList.get(0).getSub_sys_id());
				ExamSubSysQuestType.setState(1);
				list.add(ExamSubSysQuestType);
				if(localExamQuestionFacade.getQuestionSubSysTypeBySysId(typeList.get(0).getSub_sys_id(), ExamSubSysQuestType.getQuestion_id())){
					localExamQuestionFacade.addQuestionSubSysType(list);
					return true;
				}else{
					return false;
				}
				
			}
		}catch(Exception e){
			throw new FrameworkRuntimeException(ErrorCode.ec00000,e);
		}
	}

	@Override
	public ExamReturnQuestion getExamQuestionForZyyList(ExamQuestionQuery query)
			throws FrameworkRuntimeException {
		ExamReturnQuestion rq = new ExamReturnQuestion();
		rq.setList(localExamQuestionFacade.getQuestionListForZyy(query));
		rq.setTotal_count(localExamQuestionFacade.getQuestionSize(query));
		
		return rq;
	}

}
