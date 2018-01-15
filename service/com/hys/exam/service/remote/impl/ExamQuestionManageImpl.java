package com.hys.exam.service.remote.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hys.exam.dao.remote.ExamQuestionDAO;
import com.hys.exam.dao.remote.ExamQuestionKeyDAO;
import com.hys.exam.dao.remote.ExamQuestionPropDAO;
import com.hys.exam.dao.remote.ExamQuestionPropValCascadeDAO;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.service.remote.ExamQuestionManage;

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
public class ExamQuestionManageImpl implements ExamQuestionManage {

	private ExamQuestionDAO remoteExamQuestionDAO;
	
	private ExamQuestionPropValCascadeDAO remoteExamQuestionPropValCascadeDAO;
	
	private ExamQuestionKeyDAO remoteExamQuestionKeyDAO;
	
	private ExamQuestionPropDAO remoteExamQuestionPropDAO;
	
	public ExamQuestionDAO getRemoteExamQuestionDAO() {
		return remoteExamQuestionDAO;
	}

	public void setRemoteExamQuestionDAO(ExamQuestionDAO remoteExamQuestionDAO) {
		this.remoteExamQuestionDAO = remoteExamQuestionDAO;
	}

	public ExamQuestionPropValCascadeDAO getRemoteExamQuestionPropValCascadeDAO() {
		return remoteExamQuestionPropValCascadeDAO;
	}

	public void setRemoteExamQuestionPropValCascadeDAO(
			ExamQuestionPropValCascadeDAO remoteExamQuestionPropValCascadeDAO) {
		this.remoteExamQuestionPropValCascadeDAO = remoteExamQuestionPropValCascadeDAO;
	}

	public ExamQuestionKeyDAO getRemoteExamQuestionKeyDAO() {
		return remoteExamQuestionKeyDAO;
	}

	public void setRemoteExamQuestionKeyDAO(
			ExamQuestionKeyDAO remoteExamQuestionKeyDAO) {
		this.remoteExamQuestionKeyDAO = remoteExamQuestionKeyDAO;
	}

	public ExamQuestionPropDAO getRemoteExamQuestionPropDAO() {
		return remoteExamQuestionPropDAO;
	}

	public void setRemoteExamQuestionPropDAO(
			ExamQuestionPropDAO remoteExamQuestionPropDAO) {
		this.remoteExamQuestionPropDAO = remoteExamQuestionPropDAO;
	}

	@Override
	public ExamQuestion addQuestion(String key, ExamQuestion quest)
			throws Exception {
		//查试题是否存在
		if(remoteExamQuestionDAO.getQuestionByContentAndLabel(key,quest).isEmpty()){
			ExamQuestion q = remoteExamQuestionDAO.addQuestion(key,quest,1);
			remoteExamQuestionDAO.addQuestionSubSysType(key,quest);
			remoteExamQuestionKeyDAO.addQuestionKey(key,q.getQuestionKeyList());
			remoteExamQuestionPropDAO.addQuestionProp(key,q.getQuestionPropMap());	
			List<ExamQuestionPropValCascade> questPropValCascade = new ArrayList<ExamQuestionPropValCascade>();
			questPropValCascade.add(q.getQuestPropValCascade());
			remoteExamQuestionPropValCascadeDAO.addQuestionPropValCascade(key,questPropValCascade);
			return q;
		}else{
			return null;
		}
	}

	@Override
	public void addQuestionProp(String key,
			Map<String, List<ExamQuestionProp>> questionPropMap, List<Long> id,
			List<ExamQuestionPropValCascade> questPropVal, int status)
			throws Exception {
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
		remoteExamQuestionPropDAO.deleteQuestionProprByQuestionId(key,batch);
		//删除试题属性对应关系
		remoteExamQuestionPropValCascadeDAO.deleteQuestionPropValCascade(key,questPropValList);		
		//添加试题属性
		remoteExamQuestionPropDAO.addQuestionProp(key,questionPropMap);
		//保存试题属性对应关系
		remoteExamQuestionPropValCascadeDAO.addQuestionPropValCascade(key,questPropVal);	
		
		if(status!=0){
			//更新试题状态
			remoteExamQuestionDAO.updateQuestionStateByIdArr(key,id, status);
		}
		//更新试题创建时间
		remoteExamQuestionDAO.updateQuestionCreateDate(key,id);
	}

	@Override
	public void deleteQuesitons(String key, List<Long> id) throws Exception {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); i++) {
			Object[] values = new Object[] { id.get(i),
					id.get(i) };
			batch.add(values);
		}
		remoteExamQuestionKeyDAO.deleteQuestionKey(key,batch);
		remoteExamQuestionPropValCascadeDAO.deleteQuestionPropValCascadeByQuestionId(key,batch);
		remoteExamQuestionPropDAO.deleteQuestionPropByQuestionId(key,batch);
		remoteExamQuestionDAO.deleteQuestionSubSysType(key,batch);
		remoteExamQuestionDAO.deleteQuesitons(key,batch);
	}

	@Override
	public List<ExamQuestion> getChildQuestionByParentId(String key,
			Long parentId) throws Exception {
		
		List<ExamQuestion> childList = remoteExamQuestionDAO.getChildQuestionByParentId(key,parentId);
		for (Iterator<ExamQuestion> iterator = childList
				.iterator(); iterator.hasNext();) {
			ExamQuestion q = (ExamQuestion) iterator.next();
			q.setQuestionKeyList(remoteExamQuestionKeyDAO.getQuestionKeys(key,q.getId()));
			q.setQuestionPropMap(remoteExamQuestionPropDAO
					.getQuestionPropByQuestionId(key,q.getId()));
			q.setSubSysQuestTypeList(remoteExamQuestionDAO.getExamSubSysQuestTypeById(key,q.getId()));
		}
		return childList;
	}

	@Override
	public void getQuestSizeByFasterTactic(String key,
			List<ExamPaperFasterTacticX> tXList) throws Exception {
		remoteExamQuestionDAO.getQuestSizeByFasterTactic(key,tXList);
	}

	@Override
	public List<ExamQuestion> getQuestionByContentAndLabel(String key,
			ExamQuestion quest) throws Exception {
		return remoteExamQuestionDAO.getQuestionByContentAndLabel(key,quest);
	}

	@Override
	public ExamQuestion getQuestionById(String key, Long id) throws Exception {
		ExamQuestion quest = remoteExamQuestionDAO.getQuestionById(key,id);
		quest.setSubSysQuestTypeList(remoteExamQuestionDAO.getExamSubSysQuestTypeById(key,id));
		quest.setQuestionKeyList(remoteExamQuestionKeyDAO.getQuestionKeys(key,id));
		quest.setQuestionPropMap(remoteExamQuestionPropDAO.getQuestionPropByQuestionId(key,id));
		quest.setChildQuestionList(getChildQuestionByParentId(key,id));
		quest.setQuestPropValCascade(remoteExamQuestionPropValCascadeDAO.getQuestionPropValCascadeById(key,id));
		return quest;
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(String key, Long[] idArr)
			throws Exception {
		List<ExamQuestion> questionList = remoteExamQuestionDAO.getQuestionIdByIdArr(key,idArr);
		for(ExamQuestion q : questionList){
			q.setQuestionKeyList(remoteExamQuestionKeyDAO.getQuestionKeys(key,q.getId()));
			if(q.getChildQuestionNum()>0){
				for(ExamQuestion child : q.getChildQuestionList()){
					child.setQuestionKeyList(remoteExamQuestionKeyDAO.getQuestionKeys(key,child.getId()));
				}
			}
		}
		return questionList;
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIds(String key, List<Long> ids)
			throws Exception {
		return remoteExamQuestionDAO.getQuestionIdByIds(key,ids);
	}

	@Override
	public List<ExamQuestion> getQuestionList(String key,
			ExamQuestionQuery questQuery) throws Exception {
		List<ExamQuestion> questList =  remoteExamQuestionDAO.getQuestionList(key,questQuery);
		for(ExamQuestion quest:questList){
			quest.setSubSysQuestTypeList(remoteExamQuestionDAO.getExamSubSysQuestTypeById(key,quest.getId()));
			quest.setQuestionPropMap(remoteExamQuestionPropDAO.getQuestionPropByQuestionId(key,quest.getId()));
			quest.setSubSysQuestTypeList(remoteExamQuestionDAO.getExamSubSysQuestTypeById(key,quest.getId()));
		}
		return questList;
	}

	@Override
	public List<ExamQuestion> getQuestionList(String key,
			ExamPaperTactic paperTactic, int pageSize, int currentPage)
			throws Exception {
		return remoteExamQuestionDAO.getQuestionList(key,paperTactic, pageSize, currentPage);
	}

	@Override
	public List<ExamQuestion> getQuestionListByByFasterTactic(String key,
			int questionLableId, String questionTypeId,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap)
			throws Exception {
		return remoteExamQuestionDAO.getQuestionListByByFasterTactic(key,
				questionLableId, questionTypeId, t2, questionPropMap);
	}

	@Override
	public List<ExamQuestion> getQuestionListByPaperId(String key, Long paperId)
			throws Exception {
		List<ExamQuestion> questionList = remoteExamQuestionDAO.getQuestionListByPaperId(key,paperId);
		for(ExamQuestion q : questionList){
			q.setQuestionKeyList(remoteExamQuestionKeyDAO.getQuestionKeys(key,q.getId()));
			q.setQuestionPropMap(remoteExamQuestionPropDAO.getQuestionPropByQuestionId(key,q.getId()));
			if(q.getChildQuestionNum()>0){
				for(ExamQuestion child : q.getChildQuestionList()){
					child.setQuestionKeyList(remoteExamQuestionKeyDAO.getQuestionKeys(key,child.getId()));
				}
			}
		}
		return questionList;
	}

	@Override
	public int getQuestionListSize(String key, ExamPaperTactic paperTactic)
			throws Exception {
		return remoteExamQuestionDAO.getQuestionListSize(key,paperTactic);
	}

	@Override
	public int getQuestionSize(String key, ExamQuestionQuery questQuery)
			throws Exception {
		return remoteExamQuestionDAO.getQuestionSize(key,questQuery);
	}

	@Override
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(String key,
			ExamPaperFasterTactic paperTactic) throws Exception {
		return remoteExamQuestionDAO.getQuestionSizeByFasterTactic(key,paperTactic);
	}

	@Override
	public void updateQuestionById(String key, ExamQuestion quest)
			throws Exception {
		
		List<Object[]> batch = new ArrayList<Object[]>();
		Object[] values = new Object[] { quest.getId(), quest.getId() };
		batch.add(values);
		
		//删除答案
		remoteExamQuestionKeyDAO.deleteQuestionKey(key,batch);
		//删除属性
		remoteExamQuestionPropDAO.deleteQuestionPropByQuestionId(key,batch);
		//删除分类
		remoteExamQuestionDAO.deleteQuestionSubSysType(key,batch);
		
		//删除子试题
		if (quest.getChildQuestionList() != null
				&& quest.getChildQuestionList().size() > 0) {
			remoteExamQuestionDAO.deleteChildQuestionByParentId(key,quest.getId());
		}
		
		//添加子试题
		if (quest.getChildQuestionList() != null
				&& quest.getChildQuestionList().size() > 0) {
			remoteExamQuestionDAO.addQuestion(key,quest, 0);
		}	
		
		//添加答案
		if (quest.getQuestionKeyList() != null
				&& quest.getQuestionKeyList().size() > 0) {
			remoteExamQuestionKeyDAO.addQuestionKey(key,quest.getQuestionKeyList());
		}	
		//添加属性
		remoteExamQuestionPropDAO.addQuestionProp(key,quest.getQuestionPropMap());
		
		//添加分类
		remoteExamQuestionDAO.addQuestionSubSysType(key,quest);
		
		//修改试题属性级联关系
		remoteExamQuestionPropValCascadeDAO.updateQuestionPropValCascade(key,quest.getQuestPropValCascade());
		
		//修改主试题
		remoteExamQuestionDAO.updateQuestionById(key,quest);

	}

	@Override
	public void updateQuestionStateByIdArr(String key, List<Long> id, int state)
			throws Exception {
		remoteExamQuestionDAO.updateQuestionStateByIdArr(key,id, state);

	}

}
