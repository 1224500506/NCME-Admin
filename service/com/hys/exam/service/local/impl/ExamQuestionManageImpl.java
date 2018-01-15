package com.hys.exam.service.local.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hys.exam.dao.local.ExamQuestionDAO;
import com.hys.exam.dao.local.ExamQuestionKeyDAO;
import com.hys.exam.dao.local.ExamQuestionPropDAO;
import com.hys.exam.dao.local.ExamQuestionPropValCascadeDAO;
import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionLabelNum;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.service.local.ExamQuestionManage;
import com.hys.framework.service.impl.BaseMangerImpl;

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
public class ExamQuestionManageImpl extends BaseMangerImpl implements
		ExamQuestionManage {

	private ExamQuestionDAO localExamQuestionDAO;
	
	private ExamQuestionPropValCascadeDAO localExamQuestionPropValCascadeDAO;
	
	private ExamQuestionKeyDAO localExamQuestionKeyDAO;
	
	private ExamQuestionPropDAO localExamQuestionPropDAO;

	public ExamQuestionDAO getLocalExamQuestionDAO() {
		return localExamQuestionDAO;
	}

	public void setLocalExamQuestionDAO(ExamQuestionDAO localExamQuestionDAO) {
		this.localExamQuestionDAO = localExamQuestionDAO;
	}

	public ExamQuestionPropValCascadeDAO getLocalExamQuestionPropValCascadeDAO() {
		return localExamQuestionPropValCascadeDAO;
	}

	public void setLocalExamQuestionPropValCascadeDAO(
			ExamQuestionPropValCascadeDAO localExamQuestionPropValCascadeDAO) {
		this.localExamQuestionPropValCascadeDAO = localExamQuestionPropValCascadeDAO;
	}

	public ExamQuestionKeyDAO getLocalExamQuestionKeyDAO() {
		return localExamQuestionKeyDAO;
	}

	public void setLocalExamQuestionKeyDAO(
			ExamQuestionKeyDAO localExamQuestionKeyDAO) {
		this.localExamQuestionKeyDAO = localExamQuestionKeyDAO;
	}

	public ExamQuestionPropDAO getLocalExamQuestionPropDAO() {
		return localExamQuestionPropDAO;
	}

	public void setLocalExamQuestionPropDAO(
			ExamQuestionPropDAO localExamQuestionPropDAO) {
		this.localExamQuestionPropDAO = localExamQuestionPropDAO;
	}

	public void addImportQuestion(List<ExamQuestion> questList) {
		ExamQuestion q  = localExamQuestionDAO.addImportQuestion(questList);
		localExamQuestionKeyDAO.addQuestionKey(q.getQuestionKeyList());
		localExamQuestionPropDAO.addQuestionProp(q.getQuestionPropMap());	
		localExamQuestionDAO.addQuestionSubSysType(q.getSubSysQuestTypeList());
		if(q.getQuestPropValCascadeList()!=null && !q.getQuestPropValCascadeList().isEmpty()){
			localExamQuestionPropValCascadeDAO.addQuestionPropValCascade(q.getQuestPropValCascadeList());
		}
	}

	public ExamQuestion addQuestion(ExamQuestion quest) {
		//查试题是否存在
		if(localExamQuestionDAO.getQuestionByContentAndLabel(quest).isEmpty()){
			ExamQuestion q = localExamQuestionDAO.addQuestion(quest,1);
			localExamQuestionDAO.addQuestionSubSysType(quest.getSubSysQuestTypeList());
			localExamQuestionKeyDAO.addQuestionKey(q.getQuestionKeyList());
			//localExamQuestionPropDAO.addQuestionProp(q.getQuestionPropMap());	
			List<ExamQuestionPropValCascade> questPropValCascade = new ArrayList<ExamQuestionPropValCascade>();
			questPropValCascade.add(q.getQuestPropValCascade());
			localExamQuestionPropValCascadeDAO.addQuestionPropValCascade(questPropValCascade);
			return q;
		}else{
			return null;
		}
	}
	

	public void deleteQuesitons(List<Long> id) {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); i++) {
			Object[] values = new Object[] { id.get(i),
					id.get(i) };
			batch.add(values);
		}
		localExamQuestionKeyDAO.deleteQuestionKey(batch);
		localExamQuestionPropValCascadeDAO.deleteQuestionPropValCascadeByQuestionId(batch);
		localExamQuestionPropDAO.deleteQuestionPropByQuestionId(batch);
		localExamQuestionDAO.deleteQuestionSubSysType(batch);
		localExamQuestionDAO.deleteQuesitons(batch);
	}

	public List<ExamQuestion> getChildQuestionByParentId(Long parentId) {
		List<ExamQuestion> childList = localExamQuestionDAO.getChildQuestionByParentId(parentId);
		for (Iterator<ExamQuestion> iterator = childList
				.iterator(); iterator.hasNext();) {
			ExamQuestion q = (ExamQuestion) iterator.next();
			q.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(q.getId()));
			q.setQuestionPropMap(localExamQuestionPropDAO
					.getQuestionPropByQuestionId(q.getId()));
			q.setSubSysQuestTypeList(localExamQuestionDAO.getExamSubSysQuestTypeById(q.getId()));
		}
		return childList;
	}

	public ExamQuestion getQuestionById(Long id) {
		ExamQuestion quest = localExamQuestionDAO.getQuestionById(id);
		quest.setSubSysQuestTypeList(localExamQuestionDAO.getExamSubSysQuestTypeById(id));
		quest.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(id));
		quest.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(id));
		quest.setChildQuestionList(getChildQuestionByParentId(id));
		quest.setQuestPropValCascade(localExamQuestionPropValCascadeDAO.getQuestionPropValCascadeById(id));
		return quest;
	}

	public List<ExamQuestion> getQuestionList(ExamQuestionQuery questQuery) {
		List<ExamQuestion> questList =  localExamQuestionDAO.getQuestionList(questQuery);
		for(ExamQuestion quest:questList){
			quest.setSubSysQuestTypeList(localExamQuestionDAO.getExamSubSysQuestTypeById(quest.getId()));
			quest.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(quest.getId()));
			//quest.setSubSysQuestTypeList(localExamQuestionDAO.getExamSubSysQuestTypeById(quest.getId()));
		}
		return questList;
	}
	
	
	public List<ExamQuestion> getQuestionListForZyy(ExamQuestionQuery questQuery) {
		
		List<ExamQuestion> questList =  localExamQuestionDAO.getQuestionList(questQuery);
		for(ExamQuestion quest:questList){
			quest.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(quest.getId()));
			quest.setSubSysQuestTypeList(localExamQuestionDAO.getExamSubSysQuestTypeById(quest.getId()));
			quest.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(quest.getId()));
			quest.setSubSysQuestTypeList(localExamQuestionDAO.getExamSubSysQuestTypeById(quest.getId()));
		}
		return questList;
	}	

	public void updateQuestionById(ExamQuestion quest) {
		
		List<Object[]> batch = new ArrayList<Object[]>();
		Object[] values = new Object[] { quest.getId(), quest.getId() };
		batch.add(values);
		
		//删除答案
		localExamQuestionKeyDAO.deleteQuestionKey(batch);
		//删除属性
		localExamQuestionPropDAO.deleteQuestionPropByQuestionId(batch);
		//删除分类
		localExamQuestionDAO.deleteQuestionSubSysType(batch);
		
		//删除子试题
		if (quest.getChildQuestionList() != null
				&& quest.getChildQuestionList().size() > 0) {
			localExamQuestionPropValCascadeDAO.deleteQuestionPropValCascadeByQuestionId(batch);
			localExamQuestionDAO.deleteChildQuestionByParentId(quest.getId());
			
		}
		
		//添加子试题
		if (quest.getChildQuestionList() != null
				&& quest.getChildQuestionList().size() > 0) {
			localExamQuestionDAO.addQuestion(quest, 0);
		}	
		
		//添加答案
		if (quest.getQuestionKeyList() != null
				&& quest.getQuestionKeyList().size() > 0) {
			localExamQuestionKeyDAO.addQuestionKey(quest.getQuestionKeyList());
		}	
		//添加属性
		localExamQuestionPropDAO.addQuestionProp(quest.getQuestionPropMap());
		
		//添加分类
		localExamQuestionDAO.addQuestionSubSysType(quest.getSubSysQuestTypeList());
		
		//修改试题属性级联关系
		localExamQuestionPropValCascadeDAO.updateQuestionPropValCascade(quest.getQuestPropValCascade());
		
		//修改主试题
		localExamQuestionDAO.updateQuestionById(quest);
	}

	public int updateQuestionStateByIdArr(List<Long> id, int state,String author, String opinion) {
		return localExamQuestionDAO.updateQuestionStateByIdArr(id, state, author, opinion);
	}

	public int getQuestionSize(ExamQuestionQuery questQuery) {
		return localExamQuestionDAO.getQuestionSize(questQuery);
	}

	public List<ExamQuestion> getQuestionIdByIds(List<Long> ids) {
		return localExamQuestionDAO.getQuestionIdByIds(ids);
	}

	public void addQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap,List<Long> id,List<ExamQuestionPropValCascade> questPropVal,int status) {
		
		List<Object[]> batch = new ArrayList<Object[]>();
		List<ExamQuestionPropValCascade> questPropValList = new ArrayList<ExamQuestionPropValCascade>();
		for(Long idv : id){
			Object[] values = new Object[] { idv, idv };
			ExamQuestionPropValCascade cascade = new ExamQuestionPropValCascade();
			cascade.setQuestion_id(idv);
			questPropValList.add(cascade);
			batch.add(values);
		}
		//删除关联属性试题属性
		localExamQuestionPropDAO.deleteQuestionProprByQuestionId(batch);
		//删除试题属性对应关系
		localExamQuestionPropValCascadeDAO.deleteQuestionPropValCascade(questPropValList);		
		//添加试题属性
		localExamQuestionPropDAO.addQuestionProp(questionPropMap);
		//保存试题属性对应关系
		if (questPropVal != null) localExamQuestionPropValCascadeDAO.addQuestionPropValCascade(questPropVal);	
		
/*		if(status!=0){
			//更新试题状态
			localExamQuestionDAO.updateQuestionStateByIdArr(id, status, null, null);
		}
		//更新试题创建时间
		localExamQuestionDAO.updateQuestionCreateDate(id);
*/	}

	public List<ExamQuestion> getQuestionByContentAndLabel(ExamQuestion quest) {
		return localExamQuestionDAO.getQuestionByContentAndLabel(quest);
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr) {
		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionIdByIdArr(idArr);
		for(ExamQuestion q : questionList){
			q.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(q.getId()));
			q.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()));
			q.setQuestPropValCascade(localExamQuestionPropValCascadeDAO.getQuestionPropValCascadeById(q.getId()));
			if(q.getChildQuestionNum()>0){
				for(ExamQuestion child : q.getChildQuestionList()){
					child.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(child.getId()));
				}
			}
		}
		return questionList;
	}

	@Override
	public List<ExamQuestion> getQuestionListByPaperId(Long paperId) {
		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionListByPaperId(paperId);
		for(ExamQuestion q : questionList){
			q.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(q.getId()));
			q.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()));
			if(q.getChildQuestionNum()>0){
				for(ExamQuestion child : q.getChildQuestionList()){
					child.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(child.getId()));
				}
			}
		}
		return questionList;
	}

	public List<ExamQuestion> getQuestionList(ExamPaperTactic paperTactic,
			int pageSize, int currentPage) {
		return localExamQuestionDAO.getQuestionList(paperTactic, pageSize, currentPage);
	}

	public int getQuestionListSize(ExamPaperTactic paperTactic) {
		return localExamQuestionDAO.getQuestionListSize(paperTactic);
	}

	@Override
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(
			ExamPaperFasterTactic paperTactic) {
		return localExamQuestionDAO.getQuestionSizeByFasterTactic(paperTactic);
	}

	@Override
	public void getQuestSizeByFasterTactic(
			List<ExamPaperFasterTacticX> tXList) {
		localExamQuestionDAO.getQuestSizeByFasterTactic(tXList);
	}

	@Override
	public List<ExamQuestion> getQuestionListByByFasterTactic(
			int questionLableId, String questionTypeId,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia) {
		return localExamQuestionDAO.getQuestionListByByFasterTactic(questionLableId, questionTypeId, t2, questionPropMap,isnot_multimedia);
	}

	@Override
	public void addImportQuestionProp(
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal, int status) {
		
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); i++) {
			Object[] values = new Object[] { id.get(i),
					id.get(i) };
			batch.add(values);
		}
		
		//删除试题级联属性
		localExamQuestionPropDAO.deleteQuestionProprByQuestionId(batch);
		
		//删除试题属性对应关系
		localExamQuestionPropValCascadeDAO.deleteQuestionPropValCascadeByQuestionId(batch);
		
		//添加试题属性
		localExamQuestionPropDAO.addQuestionProp(questionPropMap);
		//保存试题属性对应关系
		localExamQuestionPropValCascadeDAO.addQuestionPropValCascade(questPropVal);	
		
		if(status!=0){
			//更新试题状态
			localExamQuestionDAO.updateQuestionStateByIdArr(id, status, null, null);
		}

	}

	@Override
	public List<ExamPaperAndPaper> getQuestionLableNumByPaperIDArr(
			Long[] paperIdArr) {
		return localExamQuestionDAO.getQuestionLableNumByPaperIDArr(paperIdArr);
	}

	@Override
	public List<ExamQuestion> getQuestionByPaperIDArr(Long[] paperIdArr,
			Integer questionLabelId) {
		return localExamQuestionDAO.getQuestionByPaperIDArr(paperIdArr, questionLabelId);
	}

	@Override
	public List<ExamQuestion> getQuestionAllList(ExamQuestionQuery questQuery) {
		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionAllList(questQuery);
		for(ExamQuestion q : questionList){
			q.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(q.getId()));
			q.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()));
			//q.setQuestPropValCascade(localExamQuestionPropValCascadeDAO.getQuestionPropValCascadeById(q.getId()));
			if(q.getChildQuestionNum()>0){
				for(ExamQuestion child : q.getChildQuestionList()){
					child.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(child.getId()));
				}
			}
		}
		return questionList;
	}

	@Override
	public List<ExamQuestion> getQuestionListErrorPropId(Integer type) {
		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionListErrorPropId(type);
		for(ExamQuestion q : questionList){
			q.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(q.getId()));
			q.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()));
			q.setQuestPropValCascade(localExamQuestionPropValCascadeDAO.getQuestionPropValCascadeById(q.getId()));
			if(q.getChildQuestionNum()>0){
				for(ExamQuestion child : q.getChildQuestionList()){
					child.setQuestionKeyList(localExamQuestionKeyDAO.getQuestionKeys(child.getId()));
				}
			}
		}
		return questionList;
	}

	@Override
	public List<ExamPaperFasterTactic2> getQuestSizeByFasterTactic(
			int questionLableId, String questionTypeId,
			List<ExamPaperFasterTactic2> t2List,
			Map<String, List<ExamQuestionProp>> questionPropMap,
			Integer isnotMultimedia) {
		return localExamQuestionDAO.getQuestSizeByFasterTactic(questionLableId,
				questionTypeId, t2List, questionPropMap, isnotMultimedia);
	}

	@Override
	public void addQuestionSubSysType(
			List<ExamSubSysQuestType> subSysQuestTypeList) {
		localExamQuestionDAO.addQuestionSubSysType(subSysQuestTypeList);
	}

	@Override
	public List<ExamQuestion> getQuestionListByPropVal(Long propId, Integer type) {
		List<ExamQuestion> questionList = localExamQuestionDAO.getQuestionListByPropVal(propId, type);
		for(ExamQuestion q : questionList){
			q.setQuestionPropMap(localExamQuestionPropDAO.getQuestionPropByQuestionId(q.getId()));
		}
		return questionList;
	}

	@Override
	public boolean getQuestionSubSysTypeBySysId(Long SysId, Long questionId) {
		return localExamQuestionDAO.getQuestionSubSysTypeBySysId(SysId, questionId);
	}

	@Override
	public List<ExamQuestionLabelNum> getQuestionLabelNumByPropId(Long propId,
			Integer type) {
		return localExamQuestionDAO.getQuestionLabelNumByPropId(propId, type);
	}

	@Override
	public void updateQuestionSourceId(Long oldId, Long newId) {
		localExamQuestionDAO.updateQuestionSourceId(oldId, newId);
	}

}
