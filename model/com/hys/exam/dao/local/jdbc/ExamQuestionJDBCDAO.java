package com.hys.exam.dao.local.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.hys.exam.constants.Constants;
import com.hys.exam.dao.BaseDao;
import com.hys.exam.dao.local.ExamPropValDAO;
import com.hys.exam.dao.local.ExamQuestionDAO;
import com.hys.exam.model.ExamPaperAndPaper;
import com.hys.exam.model.ExamPaperFasterTactic;
import com.hys.exam.model.ExamPaperFasterTactic1;
import com.hys.exam.model.ExamPaperFasterTactic2;
import com.hys.exam.model.ExamPaperFasterTactic3;
import com.hys.exam.model.ExamPaperFasterTacticX;
import com.hys.exam.model.ExamPaperTactic;
import com.hys.exam.model.ExamProp;
import com.hys.exam.model.ExamPropVal;
import com.hys.exam.model.ExamQuestion;
import com.hys.exam.model.ExamQuestionKey;
import com.hys.exam.model.ExamQuestionLabelNum;
import com.hys.exam.model.ExamQuestionProp;
import com.hys.exam.model.ExamQuestionPropValCascade;
import com.hys.exam.model.ExamSubSysQuestType;
import com.hys.exam.queryObj.ExamPropQuery;
import com.hys.exam.queryObj.ExamQuestionQuery;
import com.hys.exam.returnObj.ExamReturnProp;
import com.hys.exam.util.FuncMySQL;
import com.hys.exam.utils.PageUtil;
import com.hys.exam.utils.StringUtils;

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
public class ExamQuestionJDBCDAO extends BaseDao implements
		ExamQuestionDAO {

	ExamPropValDAO localExamPropValDAO;
	
	//private static final String SQL_ADD_QUESTION = "insert into exam_question (id, question_label_id, parent_id, content, state, grade, differ, analyse, source, seq, author, checker, online_date, isnot_multimedia, surname) values (:id, :question_label_id, :parent_id, :content, :state, :grade, :differ, :analyse, :source, :seq, :author, :checker, to_date(:online_date,'yyyy-mm-dd hh24:mi:ss'), :isnot_multimedia, :surname)";
	//private static final String SQL_GET_QUESTION_BY_ID = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date,  t.author, t.checker, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date, isnot_multimedia, t.surname from exam_question t where t.id = ?";
	//private static final String SQL_GET_CHILD_QUESTION_BY_PARENT_ID = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date,  t.author, t.checker, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date, isnot_multimedia, t.surname from exam_question t where t.parent_id = ? order by id desc";
	//private static final String SQL_GET_QUESTION_LIST = "select distinct t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date, t.author, t.checker, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date, t.surname, t.opinion from exam_question t, exam_question_type t1, exam_sys_quest_type_quest t2 where t.id = t2.question_id and t1.id = t2.sub_type_id and t.parent_id = 0 and t2.state = 1 ";
	//private static final String SQL_ISNOT_EXIST_QUESTION = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, to_char(t.create_date,'yyyy-mm-dd hh24:mi:ss') as create_date, t.author, t.checker, to_char(t.online_date,'yyyy-mm-dd hh24:mi:ss') as online_date from exam_question t where t.question_label_id=? and t.content=? and t.parent_id = 0";
	
	
	//YHQ,2017-06-22,函数替换，迁移到分布式数据库	
	private static final String SQL_ADD_QUESTION = "insert into exam_question (id, question_label_id, parent_id, content, state, grade, differ, analyse, source, seq, author, checker, online_date, isnot_multimedia, surname) values (:id, :question_label_id, :parent_id, :content, :state, :grade, :differ, :analyse, :source, :seq, :author, :checker, " + FuncMySQL.longDateForInsert("online_date") +", :isnot_multimedia, :surname)";
	private static final String SQL_GET_QUESTION_BY_ID = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, DATE_FORMAT(t.create_date,'%Y-%m-%d %H:%i:%S') as create_date,  t.author, t.checker, DATE_FORMAT(t.online_date,'%Y-%m-%d %H:%i:%S') as online_date, isnot_multimedia, t.surname from exam_question t where t.id = ?";
	private static final String SQL_GET_CHILD_QUESTION_BY_PARENT_ID = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, DATE_FORMAT(t.create_date,'%Y-%m-%d %H:%i:%S') as create_date,  t.author, t.checker, DATE_FORMAT(t.online_date,'%Y-%m-%d %H:%i:%S') as online_date, isnot_multimedia, t.surname from exam_question t where t.parent_id = ? order by id desc";
	private static final String SQL_GET_QUESTION_LIST = "select distinct t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, DATE_FORMAT(t.create_date,'%Y-%m-%d %H:%i:%S') as create_date, t.author, t.checker, DATE_FORMAT(t.online_date,'%Y-%m-%d %H:%i:%S') as online_date, t.surname, t.opinion from exam_question t, exam_question_type t1, exam_sys_quest_type_quest t2 where t.id = t2.question_id and t1.id = t2.sub_type_id and t.parent_id = 0 and t2.state = 1 ";
	private static final String SQL_ISNOT_EXIST_QUESTION = "select t.id, t.question_label_id, t.parent_id, t.content, t.state, t.grade, t.differ, t.analyse, t.source, t.seq, DATE_FORMAT(t.create_date,'%Y-%m-%d %H:%i:%S') as create_date, t.author, t.checker, DATE_FORMAT(t.online_date,'%Y-%m-%d %H:%i:%S') as online_date from exam_question t where t.question_label_id=? and t.content=? and t.parent_id = 0";
	
	
	private static final String SQL_DEL_QUESTION = "delete from exam_question where id = ? or parent_id = ?";
					
	private static final String SQL_DEL_CHILD_QUESTION = "delete from exam_question where parent_id = ?";
	
	private static final String SQL_UPDATE_QUESTION_CREATE_DATE = "update exam_question set create_date = sysdate() where id = ? or parent_id = ?";		
	
	private static final String SQL_GET_QUESTION_LIST_SIZE = "select count(distinct t.id) from exam_question t, exam_question_type t1, exam_sys_quest_type_quest t2 where t.id = t2.question_id and t1.id = t2.sub_type_id and t.parent_id = 0 and t2.state = 1 ";		
	
	private static final String SQL_ADD_QUESTION_TYPE = "insert into exam_sys_quest_type_quest (sub_type_id, question_id, sub_sys_id, state) values (:sub_type_id, :question_id, :sub_sys_id, :state)";
	
	private static final String SQL_DEL_QUESTION_TYPE = "delete from exam_sys_quest_type_quest where exists (select * from exam_question q where q.id = exam_sys_quest_type_quest.question_id and (q.parent_id = ? or q.id = ?))";
	
	private static final String SQL_GET_QUESTION_TYPE = "select t.question_id,t.sub_type_id,t.sub_sys_id,t1.name as sub_type_name,t2.name as sub_sys_name from exam_sys_quest_type_quest t, exam_question_type t1, system t2 where t1.id = t.sub_type_id and t2.id = t.sub_sys_id and t.question_id = ?";
	
	private static final String SQL_GET_QUESTIONLIST_BY_TACTIC =  "select (select count(*) from EXAM_QUESTION where parent_id = q.id) as childQuestionNum, q.* from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 2 and t.state = 1 and q.question_label_id = ? ";
	
	private static final String SQL_GET_QUESTIONLIST_BY_TACTIC_SIZE =  "select count(1) from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 2 and t.state = 1 and q.question_label_id = ? ";
	
	private static final String SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC = "select count(distinct q.id) from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 2 and t.state = 1 ";

	private static final String SQL_GET_QUESTION_BY_FASTER_TACTIC = "select distinct q.id from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 2 and t.state = 1 ";
	
	private static final String SQL_GET_QUESTION_LIST_BY_FASTER_TACTIC = "select distinct q.* from EXAM_QUESTION q, exam_sys_quest_type_quest t where q.id = t.question_id and q.parent_id = 0 and q.state = 2 and t.state = 1 ";
	
	
	@SuppressWarnings("unchecked")
	public ExamQuestion addQuestion(ExamQuestion quest,int saveParent) {
		
		//试题答案列表
		List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();
		
		//试题分类列表
		List<ExamSubSysQuestType> typeList = new ArrayList<ExamSubSysQuestType>();
		
		//试题属性列表
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		
		//试题列表
		List<ExamQuestion> batch = new ArrayList<ExamQuestion>();
		
		if (saveParent == 1) {
			quest.setId(getNextId(Constants.TABLE_EXAM_QUESTION));
			//quest.getQuestPropValCascade().setQuestion_id(quest.getId());
			ExamQuestionPropValCascade a = quest.getQuestPropValCascade();
			a.setQuestion_id(quest.getId());
		}
		
		//主试题分类处理
		if(quest.getSubSysQuestTypeList()!= null && !quest.getSubSysQuestTypeList().isEmpty()){
			for(ExamSubSysQuestType type : quest.getSubSysQuestTypeList()){
				type.setQuestion_id(quest.getId());
				typeList.add(type);
			}
		}
		
		
		//主试题答案处理
		if (quest.getQuestionKeyList() != null
				&& !quest.getQuestionKeyList().isEmpty()) {
			for (int i = 0; i < quest.getQuestionKeyList().size(); i++) {
				ExamQuestionKey key = quest.getQuestionKeyList().get(i);
				key.setQuestion_id(quest.getId());
				keyList.add(key);
			}
		}
		
		//主试题属性
		Map<String,List<ExamQuestionProp>> propMap = quest.getQuestionPropMap();
		
		for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			List<ExamQuestionProp> propList = (List<ExamQuestionProp>) entry.getValue();
			for(int i=0;i<propList.size();i++){
				ExamQuestionProp questProp = propList.get(i);
				questProp.setQuestion_id(quest.getId());
			}
			questionPropMap.put(entry.getKey().toString(), propList);
		}
		
		if (saveParent == 1) {
			batch.add(quest);
		}
		
		List<ExamQuestion> child = quest.getChildQuestionList();
		
		//子试题
		if(child!=null){
			
			for(int i=0;i<child.size();i++){
				ExamQuestion childQuestion = child.get(i);
				childQuestion.setParent_id(quest.getId());
				childQuestion.setId(quest.getId()+i+1);
				batch.add(childQuestion);
				
				
				if(childQuestion.getSubSysQuestTypeList() != null && !childQuestion.getSubSysQuestTypeList().isEmpty()){
					for(ExamSubSysQuestType childType : childQuestion.getSubSysQuestTypeList()){
						childType.setQuestion_id(childQuestion.getId());
						typeList.add(childType);
					}
				}
				
				//子试题答案处理
				if (childQuestion.getQuestionKeyList() != null
						&& !childQuestion.getQuestionKeyList().isEmpty()) {
					for (int j = 0; j < childQuestion.getQuestionKeyList()
							.size(); j++) {
						ExamQuestionKey childkey = childQuestion
								.getQuestionKeyList().get(j);
						childkey.setQuestion_id(childQuestion.getId());
						keyList.add(childkey);
					}
				}
				
				//子试题属性处理
				Map<String,List<ExamQuestionProp>> childPropMap = childQuestion.getQuestionPropMap();
				
				for (Iterator iter = childPropMap.entrySet().iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					List<ExamQuestionProp> propList = (List<ExamQuestionProp>) entry.getValue();
					for(int j=0;j<propList.size();j++){
						ExamQuestionProp questProp = propList.get(j);
						questProp.setQuestion_id(childQuestion.getId());
					}
					
					if(questionPropMap.containsKey(entry.getKey().toString())){
						questionPropMap.get(entry.getKey().toString()).addAll(propList);
					}else{
						questionPropMap.put(entry.getKey().toString(), propList);
					}
				}
				
			}
		}
		
		quest.setQuestionKeyList(keyList);
		quest.setQuestionPropMap(questionPropMap);
		quest.setSubSysQuestTypeList(typeList);
		saveList(SQL_ADD_QUESTION, batch);
		return quest;
	}

	public void deleteQuesitons(List<Object[]> id) {
		updateBatch(SQL_DEL_QUESTION, id);
	}

	public ExamQuestion getQuestionById(Long id) {
		return getJdbcTemplate().queryForObject(SQL_GET_QUESTION_BY_ID,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class), id);
	}

	@SuppressWarnings("unchecked")
	public void updateQuestionById(ExamQuestion quest) {
		List values = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append("update exam_question set ");

		if (!StringUtils.checkNull(quest.getContent())) {
			sql.append("content = ?, ");
			values.add(quest.getContent());
		}
		if (null != quest.getState()) {
			sql.append("state = ?, ");
			if (quest.getState() == 0 || quest.getState() == 1)
				sql.append("online_date = null, checker='', opinion='', ");

			values.add(quest.getState());
		}
		if (null != quest.getGrade()) {
			sql.append("grade = ?, ");
			values.add(quest.getGrade());
		}
		if (null != quest.getDiffer()) {
			sql.append("differ = ?, ");
			values.add(quest.getDiffer());
		}
		if (!StringUtils.checkNull(quest.getAnalyse())) {
			sql.append("analyse = ?, ");
			values.add(quest.getAnalyse());
		}
		if (!StringUtils.checkNull(quest.getAuthor())) {
			sql.append("author = ?, ");
			values.add(quest.getAuthor());
		}
		if (!StringUtils.checkNull(quest.getChecker())) {
			sql.append("checker = ?, ");
			values.add(quest.getChecker());
		}
		if (!StringUtils.checkNull(quest.getSource())) {
			sql.append("source = ?, ");
			values.add(quest.getSource());
		}
		
		if (!StringUtils.checkNull(quest.getSurname())) {
			sql.append("surname = ?, ");
			values.add(quest.getSurname());
		}
		
		if(null != quest.getIsnot_multimedia()){
			sql.append("isnot_multimedia = ?, ");
			values.add(quest.getIsnot_multimedia());
		}

		sql.append("id=id where id = ?");
		values.add(quest.getId());

		getSimpleJdbcTemplate().update(sql.toString(), values.toArray());
	}

	public List<ExamQuestion> getChildQuestionByParentId(Long parentId) {
		return getJdbcTemplate().query(SQL_GET_CHILD_QUESTION_BY_PARENT_ID,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class), parentId);
	}

	public void deleteChildQuestionByParentId(Long parentId) {
		getJdbcTemplate().update(SQL_DEL_CHILD_QUESTION, parentId);
		
	}

	public int updateQuestionStateByIdArr(List<Long> id, int state,String author, String opinion) {

		if (id.size() == 0) return 0;
		String arr = id.toString();
		String idarr = arr.substring(1, arr.length()-1);
		
		String sql = "update exam_question set state=?, checker=?, opinion=? ";
		String whereStr = " where id in("+idarr.toString()+")";
		if (state==2 || state==3){ //合格， 不合格
			sql+= ", online_date=sysdate()";
			whereStr += " and state!=4";
		}
		else{ //禁用， 启用
			author = "";
			opinion = "";
			sql+= ", online_date=null";
		}
		sql+=whereStr;
		
		return getJdbcTemplate().update(sql, state, author, opinion);
		//return count;
	}

	public List<ExamQuestion> getQuestionList(ExamQuestionQuery questQuery) {
		List<Object> list = new ArrayList<Object>();
		String sql = setParams(SQL_GET_QUESTION_LIST, questQuery, list);
		return getList(PageUtil.getPageSql(sql, questQuery.getPageNo(), questQuery
				.getPageSize()), list, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class));
		
	}
	
	public int getQuestionSize(ExamQuestionQuery questQuery) {
		List<Object> list = new ArrayList<Object>();

		String sql = setParams(SQL_GET_QUESTION_LIST_SIZE, questQuery, list);
		return getJdbcTemplate().queryForInt(sql, list.toArray());
	}

	
	@SuppressWarnings("unchecked")
	public ExamQuestion addImportQuestion(List<ExamQuestion> questList) {
		
		//试题答案列表
		List<ExamQuestionKey> keyList = new ArrayList<ExamQuestionKey>();
		
		//试题答案列表
		List<ExamQuestionPropValCascade> examQuestionPropValCascadeList = new ArrayList<ExamQuestionPropValCascade>();
		
		//试题分类列表
		List<ExamSubSysQuestType> typeList = new ArrayList<ExamSubSysQuestType>();
		//试题属性列表
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String,List<ExamQuestionProp>>();
		//试题列表
		List<ExamQuestion> batch = new ArrayList<ExamQuestion>();
		
		for(int i=0;i<questList.size();i++){
			
			ExamQuestion quest = questList.get(i);
			
			quest.setId(getNextId(Constants.TABLE_EXAM_QUESTION));
			
			batch.add(quest);
			
			
			
			//主试题分类处理
			if(quest.getSubSysQuestTypeList()!= null && !quest.getSubSysQuestTypeList().isEmpty()){
				for(ExamSubSysQuestType type : quest.getSubSysQuestTypeList()){
					type.setQuestion_id(quest.getId());
					typeList.add(type);
				}
			}
			
			//主试题答案处理
			if((quest.getQuestionKeyList()!=null) && (!quest.getQuestionKeyList().isEmpty())){
				for (int j = 0; j < quest.getQuestionKeyList().size(); j++) {
					ExamQuestionKey key = quest.getQuestionKeyList().get(j);
					key.setQuestion_id(quest.getId());
					keyList.add(key);
				}				
			}
			
			//主试题属性
			Map<String,List<ExamQuestionProp>> propMap = quest.getQuestionPropMap();
			for (Iterator iter = propMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propList = (List<ExamQuestionProp>) entry.getValue();
				List<ExamQuestionProp> propTmpList = new ArrayList<ExamQuestionProp>();
				for(int p=0;p<propList.size();p++){
					ExamQuestionProp questProp = new ExamQuestionProp();
					questProp.setProp_val_id(propList.get(p).getProp_val_id());
					questProp.setQuestion_id(quest.getId());
					propTmpList.add(questProp);
				}
				
				if(questionPropMap.containsKey(entry.getKey().toString())){
					questionPropMap.get(entry.getKey().toString()).addAll(propTmpList);
				}else{
					questionPropMap.put(entry.getKey().toString(), propTmpList);
				}
			}	
			
			//主试题级联属性处理
			if(quest.getQuestPropValCascade()!= null){
				quest.getQuestPropValCascade().setQuestion_id(quest.getId());
				examQuestionPropValCascadeList.add(quest.getQuestPropValCascade());
			}

			// 子试题列表
			List<ExamQuestion> childQuestList = quest.getChildQuestionList();
			
			if(childQuestList!=null){
							
				for(int k=0;k<childQuestList.size();k++){
					ExamQuestion childQuest = childQuestList.get(k);
					childQuest.setId(quest.getId()+i+1);
					childQuest.setParent_id(quest.getId());
					batch.add(childQuest);
					
					//子试题分类处理
					if(childQuest.getSubSysQuestTypeList() != null && !childQuest.getSubSysQuestTypeList().isEmpty()){
						for(ExamSubSysQuestType childType : childQuest.getSubSysQuestTypeList()){
							childType.setQuestion_id(childQuest.getId());
							typeList.add(childType);
						}
					}
					
					//子试题答案处理
					if (childQuest.getQuestionKeyList() != null
							&& !childQuest.getQuestionKeyList().isEmpty()) {
						for (int j = 0; j < childQuest.getQuestionKeyList()
								.size(); j++) {
							ExamQuestionKey childkey = childQuest
									.getQuestionKeyList().get(j);
							childkey.setQuestion_id(childQuest.getId());
							keyList.add(childkey);
						}
					}
					
					//子试题属性处理
					Map<String,List<ExamQuestionProp>> childPropMap = childQuest.getQuestionPropMap();
					
					for (Iterator iter = childPropMap.entrySet().iterator(); iter.hasNext();) {
						Map.Entry entry = (Map.Entry) iter.next();
						List<ExamQuestionProp> propList = (List<ExamQuestionProp>) entry.getValue();
						List<ExamQuestionProp> propTmpList = new ArrayList<ExamQuestionProp>();
						for(int j=0;j<propList.size();j++){
							ExamQuestionProp questProp = new ExamQuestionProp();
							questProp.setQuestion_id(childQuest.getId());
							questProp.setProp_val_id(propList.get(j).getProp_val_id());
							propTmpList.add(questProp);
						}
						if(questionPropMap.containsKey(entry.getKey().toString())){
							questionPropMap.get(entry.getKey().toString()).addAll(propTmpList);
						}else{
							questionPropMap.put(entry.getKey().toString(), propTmpList);
						}
					}
					
					//子试题级联属性处理
					if(childQuest.getQuestPropValCascade()!= null){
						childQuest.getQuestPropValCascade().setQuestion_id(childQuest.getId());
						examQuestionPropValCascadeList.add(childQuest.getQuestPropValCascade());
					}
							
				}
			}
		}
		
		saveList(SQL_ADD_QUESTION, batch);
		
		ExamQuestion quest = new ExamQuestion();
		quest.setQuestionKeyList(keyList);
		quest.setQuestionPropMap(questionPropMap);
		quest.setSubSysQuestTypeList(typeList);
		quest.setQuestPropValCascadeList(examQuestionPropValCascadeList);
		return quest;
	}
	
	@SuppressWarnings("unchecked")
	private String setParams(String sql_, ExamQuestionQuery query,	List<Object> list){
		StringBuffer sql = new StringBuffer();
		sql.append(sql_);
		// 题型
		if (!StringUtils.checkNull(query.getQuestion_label_ids())) {
			sql.append(" and t.question_label_id in ("+ query.getQuestion_label_ids() + ")");
		}
		// 题型
		if (query.getQuestion_label_id() != null) {
			sql.append(" and t.question_label_id ="	+ query.getQuestion_label_id());
		}
		//区分度
		if (null != query.getDiffer()) {
			list.add(query.getDiffer());
			sql.append(" and t.differ = ? ");
		}

		//难度
		if (null != query.getGrade()) {
			list.add(query.getGrade());
			sql.append(" and t.grade = ? ");
		}
		
		//状态
		if(null != query.getState()){
			if(query.getState() != null){
				sql.append(" and t.state = ?");
				list.add(query.getState());
			}
		/*	if(query.getState() >-1 && query.getState() < 5){
				list.add(query.getState());				
				sql.append(" and t.state = ? ");
			}
			if(query.getState() == 9){
				sql.append(" and t.state in (2, 3, 4)");
			}else if (query.getState() == 1){
				sql.append(" and t.state =1");
			}*/	
		}
		//list.add(2); // 2为删除/禁用
		//sql.append(" and t.state != ? ");

		//内容
		if (!StringUtils.checkNull(query.getContent())) {
			list.add("%"+ query.getContent() + "%");
			sql.append(" and t.content like ? ");
		}

		//来源
		if (!StringUtils.checkNull(query.getSource())) {
			list.add("%"+ query.getSource() + "%");
			sql.append(" and t.source like ? ");
		}

		//创建者
		if (!StringUtils.checkNull(query.getAuthor())) {
			list.add(query.getAuthor());
			sql.append(" and t.author = ?");
		}
		
		if (!StringUtils.checkNull(query.getChecker())) {
			list.add(query.getChecker());
			sql.append(" and t.checker = ?");
		}
		
		//解析
		if (null != query.getIsExistAnalyse()) {
			if (1 == query.getIsExistAnalyse()) {
				sql.append(" and t.analyse is not null ");
			} else {
				sql.append(" and t.analyse is null ");
			}
		}
		/*
		//创建时间
		if(null != query.getCreate_date()){
			list.add(query.getCreate_date());
			sql.append(" and to_char(t.create_date,'yyyy-MM-dd') >= ? ");
		}
		if(null != query.getCreate_date2()){
			list.add(query.getCreate_date2());
			sql.append(" and to_char(t.create_date,'yyyy-MM-dd') <= ? ");
		}
		
		//审核时间
		if(null != query.getOnline_date()){
			list.add(query.getOnline_date());
			sql.append("and to_char(t.online_date,'yyyy-MM-dd') >= ? ");
		}
		if(null != query.getOnline_date2()){
			list.add(query.getOnline_date2());
			sql.append("and to_char(t.online_date,'yyyy-MM-dd') <= ? ");
		}*/
		
		//YHQ,2017-06-22,函数替换，迁移到分布式数据库
		//创建时间
		if (StringUtils.isNotBlank(query.getCreate_date())) {
			sql.append(" and t.create_date >= " + FuncMySQL.shortDateForUpdate(query.getCreate_date()) + " ");
		}
		if (StringUtils.isNotBlank(query.getCreate_date2())) {
			sql.append(" and t.create_date <= " + FuncMySQL.shortDateForUpdate(query.getCreate_date2()) + " ");
		}
		//审核时间
		if (StringUtils.isNotBlank(query.getOnline_date())) {
			sql.append(" and t.online_date >= " + FuncMySQL.shortDateForUpdate(query.getOnline_date()) + " ");
		}
		if (StringUtils.isNotBlank(query.getOnline_date2())) {
			sql.append(" and t.online_date <= " + FuncMySQL.shortDateForUpdate(query.getOnline_date2()) + " ");
		}
		
		
		if(null != query.getIsnot_multimedia()){
			list.add(query.getIsnot_multimedia());
			sql.append(" and t.isnot_multimedia = ? ");
		}		
		
		if(null != query.getIdArr()){
			String ids = "";
			for(Long id : query.getIdArr()){
				ids += id+",";
				ids = ids.substring(0, ids.length()-1);				
			}
			sql.append("and t.id not in ("+ids+") ");
		}
		
		Map<String,List<ExamQuestionProp>> questionPropMap = query.getQuestionPropMap();
		
		if(null != questionPropMap){
			int count = 0;
			if(questionPropMap.size() >0){
				sql.append(" and (");
			}
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					if(count>0){
						sql.append(" and ");
					}
					sql.append(" t.id in (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where prop_val_id in ("+tmp_sql+"))");
					count++;
				}
				
			}
			if(questionPropMap.size()>0){
				sql.append(" )");
			}
		}
		
		if(query.getSubTypeIds()!=null && !StringUtils.checkNull(query.getSubTypeIds())){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t2.sub_type_id AND tx.ID in ("+query.getSubTypeIds()+"))");
		} else if(query.getSubTypeId()!=null){
			list.add(query.getSubTypeId());
			sql.append("and exists (SELECT 1 FROM exam_question_type tx where tx.id=t2.sub_type_id AND tx.ID =?)");
		}
		
		if (query.getSort()!=null && !query.getSort().equals(""))
		{
			sql.append(" order by ").append(query.getSort()).append(" ").append(query.getDir());
		}
		return sql.toString();
	}

	public List<ExamQuestion> getQuestionIdByIds(List<Long> ids) {
		String tmp_sql = "";
		if(null!=ids){
			for(int i=0;i<ids.size();i++){
				tmp_sql += ids.get(i)+",";
			}
			tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
		}
		String sql = "select t.id from exam_question t where t.parent_id in ("+tmp_sql+") or t.id in ("+tmp_sql+")";
		return getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class));
	}

	@Override
	public String[] getQuestionPropList(Long id) {
		
		String tmp[] = new String[2];
		StringBuffer sql = new StringBuffer();
		sql.append("select v.id||'-'||v1.id||'-'||v2.id||'-'||v3.id||'-'||v4.id as code,v.name||'-'||v1.name||'-'||v2.name||'-'||v3.name||'-'||v4.name as name ");	
		sql.append("from sub_sys_prop_val l ");	
		sql.append("join prop_val_ref f on l.id = f.prop_val1 ");	
		sql.append("join sub_sys_prop_val l1 on f.prop_val2 = l1.id ");	
		sql.append("join prop_val_ref f1 on  l1.id = f1.prop_val1 ");	
		sql.append("join sub_sys_prop_val l2 on f1.prop_val2 = l2.id ");	
		sql.append("join prop_val_ref f2 on l2.id = f2.prop_val1 ");	
		sql.append("join sub_sys_prop_val l3 on f2.prop_val2 = l3.id ");	
		sql.append("join prop_val_ref f3 on l3.id = f3.prop_val1 ");	
		sql.append("join sub_sys_prop_val l4 on f3.prop_val2 = l4.id ");	
		sql.append("join exam_prop_val v on l.prop_val_id = v.id ");	
		sql.append("join exam_prop_val v1 on l1.prop_val_id = v1.id ");
		sql.append("join exam_prop_val v2 on l2.prop_val_id = v2.id ");
		sql.append("join exam_prop_val v3 on l3.prop_val_id = v3.id ");
		sql.append("join exam_prop_val v4 on l4.prop_val_id = v4.id ");
		sql.append("where l4.prop_val_id in (select p.prop_val_id from exam_question_point p where p.question_id = ?)");
		
		List<ExamPropVal> list = getJdbcTemplate().query(sql.toString(),
				ParameterizedBeanPropertyRowMapper.newInstance(ExamPropVal.class),  id);
		String _id="";
		String name="";
		
		for (ExamPropVal v : list) {
			if(_id!="") {
				_id += "+" + v.getCode();
				name += "+" + v.getName();
			}else {
				_id += v.getCode();
				name += v.getName();
			}
			
		}
		tmp[0] = _id;
		tmp[1] = name;
		return tmp;
	}

	public void updateQuestionCreateDate(List<Long> id) {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (int i = 0; i < id.size(); i++) {
			Object[] values = new Object[] {id.get(i),id.get(i) };
			batch.add(values);
		}
		getSimpleJdbcTemplate().batchUpdate(SQL_UPDATE_QUESTION_CREATE_DATE, batch);
	}

	public List<ExamQuestion> getQuestionByContentAndLabel(ExamQuestion quest) {
		return getJdbcTemplate().query(SQL_ISNOT_EXIST_QUESTION,
				ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class),quest.getQuestion_label_id(),quest.getContent());
	}

	public void addQuestionSubSysType(List<ExamSubSysQuestType> subSysQuestTypeList) {
		saveList(SQL_ADD_QUESTION_TYPE, subSysQuestTypeList);
	}

	@Override
	public void deleteQuestionSubSysType(List<Object[]> id) {
		updateBatch(SQL_DEL_QUESTION_TYPE, id);
	}

	@Override
	public List<ExamSubSysQuestType> getExamSubSysQuestTypeById(Long id) {
		return getJdbcTemplate().query(SQL_GET_QUESTION_TYPE, ParameterizedBeanPropertyRowMapper.newInstance(ExamSubSysQuestType.class), id);
	}

	@Override
	public List<ExamQuestion> getQuestionIdByIdArr(Long[] idArr) {
		
		String idStr = "";
		if (idArr == null || idArr.length <= 0) {
			return null;
		} else {
			for (int i = 0; i < idArr.length; i++) {
				if ("".equals(idStr)) {
					idStr = String.valueOf(idArr[i]);
				} else {
					idStr += "," + String.valueOf(idArr[i]);
				}
			}
		}
		
		String sql = "select t.*, (select count(1) from exam_question t1 where t1.parent_id = t.id) as childQuestionNum from exam_question t where t.id  in ("+idStr+") order by t.id desc";
		
		List<ExamQuestion> quesitonList = getJdbcTemplate().query(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestion.class));
		
		if (quesitonList != null && quesitonList.size() > 0) {
			for(ExamQuestion q : quesitonList){
				if(q.getChildQuestionNum()>0){
					q.setChildQuestionList(getChildQuestionByParentId(q.getId()));
				}
			}
		}
		
		return quesitonList;
	}

	@Override
	public List<ExamQuestion> getQuestionListByPaperId(Long paperId) {
		String sql = "select t1.*,t2.question_score,(select count(1) from exam_question x where x.parent_id = t1.id) as childQuestionNum from exam_testpaper t,exam_question t1,exam_paper_question t2 where t.id = t2.paper_id and t1.id = t2.question_id and t.id = ? order by t1.question_label_id,t2.seq";
		
		List<ExamQuestion> quesitonList = getJdbcTemplate().query(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestion.class), paperId);
		
		if (quesitonList != null && quesitonList.size() > 0) {
			for(ExamQuestion q : quesitonList){
				if(q.getChildQuestionNum()>0){
					q.setChildQuestionList(getChildQuestionByParentId(q.getId()));
				}
			}
		}
		
		return quesitonList;
	}

	public List<ExamQuestion> getQuestionList(ExamPaperTactic paperTactic,
			int pageSize, int currentPage) {
		StringBuffer sql = new StringBuffer();
		sql.append(SQL_GET_QUESTIONLIST_BY_TACTIC);
		List<Object> list = getParamList(paperTactic, sql);
		
		int countNum = getQuestionListSize(paperTactic);
		
		if(countNum<paperTactic.getAmount()){
			return null;
		}
		
		
		if(countNum==paperTactic.getAmount()){
			return getJdbcTemplate().query(
					sql.toString(),
					ParameterizedBeanPropertyRowMapper
							.newInstance(ExamQuestion.class), list.toArray());
		}
		
		int current = getCurrentPage(countNum,paperTactic.getAmount());
		
		StringBuffer sql_ = new StringBuffer();
		sql_.append("select * from(select row_.*, rownum rownum_ from(");
		sql_.append(sql);
		sql_.append(") row_ where rownum<");
		sql_.append(current+paperTactic.getAmount());
		sql_.append(") where rownum_>=");
		sql_.append(current);
		
		return getJdbcTemplate().query(
				sql_.toString(),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestion.class), list.toArray());
		
	}

	public int getQuestionListSize(ExamPaperTactic paperTactic) {
		StringBuffer sql = new StringBuffer();

		sql.append(SQL_GET_QUESTIONLIST_BY_TACTIC_SIZE);

		List<Object> list = getParamList(paperTactic, sql);

		return getSimpleJdbcTemplate().queryForInt(sql.toString(),
				list.toArray());
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Object> getParamList(ExamPaperTactic paperTactic,
			StringBuffer sql) {
		List<Object> list = new ArrayList<Object>();
		
		list.add(paperTactic.getQuestion_label_id());
		
		if(paperTactic.getQuesiton_type_id()!=null && !StringUtils.checkNull(paperTactic.getQuesiton_type_id())){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+paperTactic.getQuesiton_type_id()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		
		Map<String,List<ExamQuestionProp>> questionPropMap = paperTactic.getQuestionPropMap();
		
		if(null != questionPropMap){
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where prop_val_id in ("+tmp_sql+"))");
				}
			}
		}
		
		
		return list;
	}
	
	/**
	 * 
	 * @param num 当前策略的试题总数
	 * @param size 所抽取的试题数量
	 * @return
	 */
	private static int getCurrentPage(int num,int size){
		Random random = new Random();
		int c = random.nextInt((num-size)+1);
		if(c<1){
			return getCurrentPage(num,size);
		}else{
			return c;
		}		
	}
	
	
	
	public ExamPaperFasterTactic getQuestionSizeByFasterTactic(ExamPaperFasterTactic paperTactic) {
		
		StringBuffer sql = new  StringBuffer();
		sql.append(SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC);
		
		Map<String,List<ExamQuestionProp>> questionPropMap = new HashMap<String, List<ExamQuestionProp>>();
		//题型,数量,分数
		List<ExamPaperFasterTactic1> examPaperFasterTactic1 = paperTactic.getExamPaperFasterTactic1();
		//级联属性
		List<ExamPaperFasterTactic2> examPaperFasterTactic2 = paperTactic.getExamPaperFasterTactic2();
//		setProp(examPaperFasterTactic2,null,questionPropMap,2);
		//非级联属性
		List<ExamPaperFasterTactic3> examPaperFasterTactic3 = paperTactic.getExamPaperFasterTactic3();
		setProp(null,examPaperFasterTactic3,questionPropMap,3);
		
		if(paperTactic.getQuestion_type_id()!=null && !StringUtils.checkNull(paperTactic.getQuestion_type_id())){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+paperTactic.getQuestion_type_id()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		
		if(paperTactic.getIsnot_multimedia()!=null){
			sql.append(" and q.isnot_multimedia = "+paperTactic.getIsnot_multimedia());
		}		
		setParam(questionPropMap,sql);
		
		for(ExamPaperFasterTactic1 t : examPaperFasterTactic1){
			List<Object> query = new ArrayList<Object>();
			StringBuffer sql_ = new StringBuffer();
			sql_.append(setExamPaperFasterTactic2Sql(examPaperFasterTactic2, t.getQuestion_label_id(), query));
			sql_.append(sql);
			sql_.append("and exists (select id from  x where q.id = x.id)");
			t.setNum(getSimpleJdbcTemplate().queryForInt(sql_.toString(), query.toArray()));
		}
		
//		for(ExamPaperFasterTactic1 t : examPaperFasterTactic1){
//			StringBuffer sql_ = new StringBuffer();
//			sql_.append(sql);
//			sql_.append(" and q.question_label_id = ? ");
//			t.setNum(getSimpleJdbcTemplate().queryForInt(sql_.toString(), t.getQuestion_label_id()));
//		}
		
		return paperTactic;
	}
	
	private String setExamPaperFasterTactic2Sql(List<ExamPaperFasterTactic2> t2List,int question_label_id,List<Object> query){
		StringBuffer sb = new StringBuffer();
		for(ExamPaperFasterTactic2 t2 : t2List){
			StringBuffer sql = new StringBuffer("select t.id from EXAM_QUESTION t");
			if(t2.getStudy1_id()!=null){
				sql.append(",EXAM_QUESTION_STUDY1 t1");
			}
			if(t2.getStudy2_id()!=null){
				sql.append(",EXAM_QUESTION_STUDY2 t2");
			}
			if(t2.getStudy3_id()!=null){
				sql.append(",EXAM_QUESTION_STUDY3 t3");
			}
			if(t2.getUnit_id()!=null){
				sql.append(",EXAM_QUESTION_UNIT t4");
			}
			if(t2.getPoint_id()!=null){
				sql.append(",EXAM_QUESTION_POINT t5");
			}
			sql.append(" where t.question_label_id = ?");
			query.add(question_label_id);
			
			if(t2.getStudy1_id()!=null){
				sql.append(" and t.id = t1.question_id and t1.prop_val_id = ?");
				query.add(t2.getStudy1_id());
			}
			if(t2.getStudy2_id()!=null){
				sql.append(" and t.id = t2.question_id and t2.prop_val_id = ?");
				query.add(t2.getStudy2_id());
			}
			if(t2.getStudy3_id()!=null){
				sql.append(" and t.id = t3.question_id and t3.prop_val_id = ?");
				query.add(t2.getStudy3_id());
			}
			if(t2.getUnit_id()!=null){
				sql.append(" and t.id = t4.question_id and t4.prop_val_id = ?");
				query.add(t2.getUnit_id());
			}
			if(t2.getPoint_id()!=null){
				sql.append(" and t.id = t5.question_id and t5.prop_val_id = ?");
				query.add(t2.getPoint_id());
			}
			
			if(sb.toString().equals("")){
				sb.append(sql);
			}else{
				sb.append(" union ");
				sb.append(sql);
			}
		}
		StringBuffer sql_ = new StringBuffer();
		sql_.append("with x as(");
		sql_.append(sb);
		sql_.append(")");
		return sql_.toString();
	}
	
	@SuppressWarnings("unchecked")
	private void setParam(Map<String,List<ExamQuestionProp>> questionPropMap,StringBuffer sql){
		
		if(null != questionPropMap){
			for (Iterator iter = questionPropMap.entrySet().iterator(); iter.hasNext();) {
				String tmp_sql = "";
				Map.Entry entry = (Map.Entry) iter.next();
				List<ExamQuestionProp> propValList = (List<ExamQuestionProp>) entry.getValue();
				if(null != propValList && (!propValList.isEmpty())){
					for(int i=0;i<propValList.size();i++){
						ExamQuestionProp questProp = propValList.get(i);
						tmp_sql += questProp.getProp_val_id()+",";
						if(entry.getKey().toString()  == Constants.EXAM_PROP_UNIT)
						{
							ExamPropQuery propQuery  = new ExamPropQuery();
							propQuery.setSysPropId(questProp.getProp_val_id());
							
							ExamReturnProp rprop = localExamPropValDAO.getNextLevelProp(propQuery);
							List<ExamProp> nextPropList = rprop.getReturnList();
							
							if (nextPropList != null && nextPropList.size() >0){
								for (ExamProp prop : nextPropList){
									tmp_sql += prop.getId().toString() + ",";
									
									propQuery.setSysPropId(prop.getId());
									rprop = localExamPropValDAO.getNextLevelProp(propQuery);
									List<ExamProp> nextNextPropList = rprop.getReturnList();
									if (nextNextPropList != null){
										for (ExamProp nextProp : nextNextPropList){
											tmp_sql += nextProp.getId().toString() + ",";
										}
									}						
								}
							}
						}
					}
					tmp_sql = tmp_sql.substring(0, tmp_sql.length()-1);
					sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get(entry.getKey().toString())+" where prop_val_id in ("+tmp_sql+")) ");
				}
			}
		}
	}
	
	private void setProp(List<ExamPaperFasterTactic2> tactic2,
			List<ExamPaperFasterTactic3> tactic3,
			Map<String, List<ExamQuestionProp>> questionPropMap, int type) {

		if (type == 2) {
			if (tactic2 != null && !tactic2.isEmpty()) {
				// 一级学科
				List<ExamQuestionProp> prop1 = new ArrayList<ExamQuestionProp>();
				// 二级学科
				List<ExamQuestionProp> prop2 = new ArrayList<ExamQuestionProp>();
				// 三级学科
				List<ExamQuestionProp> prop3 = new ArrayList<ExamQuestionProp>();
				// 单元
				List<ExamQuestionProp> prop4 = new ArrayList<ExamQuestionProp>();
				// 知识点
				List<ExamQuestionProp> prop5 = new ArrayList<ExamQuestionProp>();

				for (ExamPaperFasterTactic2 t : tactic2) {
					if (t.getStudy1_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getStudy1_id());
						if (!prop1.contains(p)) {
							prop1.add(p);
						}
					}
					if (t.getStudy2_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getStudy2_id());
						if (!prop2.contains(p)) {
							prop2.add(p);
						}
					}
					if (t.getStudy3_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getStudy3_id());
						if (!prop3.contains(p)) {
							prop3.add(p);
						}
					}
					if (t.getUnit_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getUnit_id());
						if (!prop4.contains(p)) {
							prop4.add(p);
						}
					}
					if (t.getPoint_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getPoint_id());
						if (!prop5.contains(p)) {
							prop5.add(p);
						}
					}
				}

				if (prop1 != null && !prop1.isEmpty()) {
					questionPropMap.put("1", prop1);
				}
				if (prop2 != null && !prop2.isEmpty()) {
					questionPropMap.put("2", prop2);
				}
				if (prop3 != null && !prop3.isEmpty()) {
					questionPropMap.put("3", prop3);
				}
				if (prop4 != null && !prop4.isEmpty()) {
					questionPropMap.put("4", prop4);
				}
				if (prop5 != null && !prop5.isEmpty()) {
					questionPropMap.put("5", prop5);
				}
			}
			
		} else {
			if (tactic3 != null  && !tactic3.isEmpty()) {
				// 副知识点
				List<ExamQuestionProp> prop1 = new ArrayList<ExamQuestionProp>();
				// 认知水平
				List<ExamQuestionProp> prop2 = new ArrayList<ExamQuestionProp>();
				// 职称
				List<ExamQuestionProp> prop3 = new ArrayList<ExamQuestionProp>();

				for (ExamPaperFasterTactic3 t : tactic3) {

					if (t.getPoint2_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getPoint2_id());
						prop1.add(p);
					}

					if (t.getCognize_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getCognize_id());
						prop2.add(p);
					}

					if (t.getTitle_id() != null) {
						ExamQuestionProp p = new ExamQuestionProp();
						p.setProp_val_id(t.getTitle_id());
						prop3.add(p);
					}
				}

				if (prop1 != null && !prop1.isEmpty()) {
					questionPropMap.put("7", prop1);
				}
				if (prop2 != null && !prop2.isEmpty()) {
					questionPropMap.put("8", prop2);
				}
				if (prop3 != null && !prop3.isEmpty()) {
					questionPropMap.put("9", prop3);
				}
			}
		}

	}

	
	/**
	 * 快捷策略2取策略试题总数
	 */
	public void getQuestSizeByFasterTactic(List<ExamPaperFasterTacticX> tXList) {
		
		for(ExamPaperFasterTacticX tx : tXList){
			
			List<Object> list = new ArrayList<Object>();
			StringBuffer sql = new  StringBuffer();
			sql.append(SQL_GET_QUESTION_BY_FASTER_TACTIC);
			
			if(tx.getQuestion_type_id()!=null && !StringUtils.checkNull(tx.getQuestion_type_id())){
				//sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+tx.getQuestion_type_id()+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
				sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id and tx.ID in ("+tx.getQuestion_type_id()+")) ");
			}
			sql.append(" and q.question_label_id = ? ");
			list.add(tx.getQuestion_label_id());
			
			if(tx.getIsnot_multimedia()!=null){
				sql.append(" and q.isnot_multimedia = ? ");
				list.add(tx.getIsnot_multimedia());
			}
			
			if(tx.getGrade()!=null && tx.getGrade() > 0){
				sql.append(" and q.grade = ? ");
				list.add(tx.getGrade());
			}
			
			Map<String,List<ExamQuestionProp>> questionPropMap = tx.getQuestionPropMap();
			
			setParam(questionPropMap,sql);
			
			
			List<ExamPaperFasterTactic2> tactic2List = tx.getExamPaperFasterTactic2();
			
			List<ExamPaperFasterTactic2> tactic2TmpList = new ArrayList<ExamPaperFasterTactic2>();
			
			for(int i=0;i<tactic2List.size();i++){
				List<Long> questList = new ArrayList<Long>();	
				ExamPaperFasterTactic2 tmpObja = new ExamPaperFasterTactic2();
				List<Object> query = new ArrayList<Object>();
				StringBuffer sql_  = new StringBuffer();
				
				ExamPaperFasterTactic2 tmpObj = tactic2List.get(i);
				
				query.addAll(list);
				sql_.append(sql);
				setProp(tmpObj,sql_,query);
	
//				questList = getJdbcTemplate().(sql_.toString(),ParameterizedBeanPropertyRowMapper.newInstance(Long.class), query.toArray());
				questList = getJdbcTemplate().queryForList(sql_.toString(),query.toArray(),Long.class);
				tmpObj.setExamQuestionIds(questList);
				tmpObj.setAllQuestionNum(questList.size());
				BeanUtils.copyProperties(tmpObj,tmpObja);
				tactic2TmpList.add(tmpObja);
				
			}
			tx.setExamPaperFasterTactic2(tactic2TmpList);
						
		}
		
	}
	
	private void setProp(ExamPaperFasterTactic2 t2,StringBuffer sql,List<Object> list){
		
		if (t2.getStudy1_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("1")+" where prop_val_id in (?)) ");
			list.add(t2.getStudy1_id());
		}
		if (t2.getStudy2_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("2")+" where prop_val_id in (?)) ");
			list.add(t2.getStudy2_id());
		}
		if (t2.getStudy3_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("3")+" where prop_val_id in (?)) ");
			list.add(t2.getStudy3_id());
		}
		if (t2.getUnit_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("4")+" where prop_val_id in (");
			sql.append("?");
			list.add(t2.getUnit_id());
			
			ExamPropQuery propQuery  = new ExamPropQuery();
			propQuery.setSysPropId(t2.getUnit_id());
			
			ExamReturnProp rprop = localExamPropValDAO.getNextLevelProp(propQuery);
			List<ExamProp> nextPropList = rprop.getReturnList();
			
			if (nextPropList != null && nextPropList.size() >0){
				for (ExamProp prop : nextPropList){
					list.add(prop.getId());
					sql.append(",?");
					propQuery.setSysPropId(prop.getId());
					rprop = localExamPropValDAO.getNextLevelProp(propQuery);
					List<ExamProp> nextNextPropList = rprop.getReturnList();
					if (nextNextPropList != null){
						for (ExamProp nextProp : nextNextPropList){
							list.add(nextProp.getId());
							sql.append(",?");
						}
					}						
				}
			}
			sql.append("))");
		}
		if(t2.getPoint_id()!=null){
			sql.append(" and q.id in (select question_id from "+Constants.getPropNameAll().get("5")+" where prop_val_id in (?) ");
			list.add(t2.getPoint_id());
		}		
		
	}

	/**
	 * 策略抽取试题
	 */
	public List<ExamQuestion> getQuestionListByByFasterTactic(
			int questionLableId, String questionTypeId,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia) {
		
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new  StringBuffer();
		sql.append(SQL_GET_QUESTION_LIST_BY_FASTER_TACTIC);
		
		if(questionTypeId!=null && !StringUtils.checkNull(questionTypeId)){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+questionTypeId+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		sql.append(" and q.question_label_id = ? ");
		list.add(questionLableId);
		
		if(isnot_multimedia!=null){
			sql.append(" and q.isnot_multimedia = ? ");
			list.add(isnot_multimedia);
		}
		
		setParam(questionPropMap,sql);
		
		setProp(t2,sql,list);

		
		int countNum = getQuestionListByByFasterTacticSize(questionLableId,questionTypeId,t2,questionPropMap,isnot_multimedia);
		
		if(countNum<t2.getSelQuestionNum()){
			return null;
		}
		
		sql.append(" order by DBMS_RANDOM.value");
//		System.out.println(sql.toString());
		if(countNum==t2.getSelQuestionNum()){
			return getJdbcTemplate().query(
					sql.toString(),
					ParameterizedBeanPropertyRowMapper
							.newInstance(ExamQuestion.class), list.toArray());
		}
		
		int current = getCurrentPage(countNum,t2.getSelQuestionNum());
		
		StringBuffer sql_ = new StringBuffer();
		sql_.append("select * from(select row_.*, rownum rownum_ from(");
		sql_.append(sql);
		sql_.append(") row_ where rownum<");
		sql_.append(current+t2.getSelQuestionNum());
		sql_.append(") where rownum_>=");
		sql_.append(current);
		
		
		return getJdbcTemplate().query(
				sql_.toString(),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestion.class), list.toArray());
	}
	
	
	private int getQuestionListByByFasterTacticSize(
			int questionLableId, String questionTypeId,
			ExamPaperFasterTactic2 t2,
			Map<String, List<ExamQuestionProp>> questionPropMap,Integer isnot_multimedia){
		
		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new  StringBuffer();
		sql.append(SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC);
		
		if(questionTypeId!=null && !StringUtils.checkNull(questionTypeId)){
			sql.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("+questionTypeId+") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		sql.append(" and q.question_label_id = ? ");
		list.add(questionLableId);
		
		if(isnot_multimedia!=null){
			sql.append(" and q.isnot_multimedia = ? ");
			list.add(isnot_multimedia);
		}	
		
		setParam(questionPropMap,sql);
		
		setProp(t2,sql,list);

		return getSimpleJdbcTemplate().queryForInt(sql.toString(), list.toArray());
	}

	@Override
	public List<ExamPaperAndPaper> getQuestionLableNumByPaperIDArr(
			Long[] paperIdArr) {

		StringBuffer sql = new StringBuffer();
		String idStr = "";
		if (paperIdArr == null || paperIdArr.length <= 0) {
			return null;
		} else {
			for (int i = 0; i < paperIdArr.length; i++) {
				if ("".equals(idStr)) {
					idStr = String.valueOf(paperIdArr[i]);
				} else {
					idStr += "," + String.valueOf(paperIdArr[i]);
				}
			}
		}
		sql.append("select q.question_label_id,count(1) as allnum from exam_question q where exists (select 1 from (select distinct (t.question_id) from exam_paper_question t where t.paper_id in (");
		sql.append(idStr);
		sql.append(")) x where x.question_id = q.id) group by q.question_label_id");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamPaperAndPaper.class));
	}

	@Override
	public List<ExamQuestion> getQuestionByPaperIDArr(Long[] paperIdArr,
			Integer questionLabelId) {
		StringBuffer sql = new StringBuffer();
		String idStr = "";
		if (paperIdArr == null || paperIdArr.length <= 0) {
			return null;
		} else {
			for (int i = 0; i < paperIdArr.length; i++) {
				if ("".equals(idStr)) {
					idStr = String.valueOf(paperIdArr[i]);
				} else {
					idStr += "," + String.valueOf(paperIdArr[i]);
				}
			}
		}
		sql.append("select q.id from exam_question q where exists (select 1 from (select distinct (t.question_id) from exam_paper_question t where t.paper_id in (");
		sql.append(idStr);
		sql.append(")) x where x.question_id = q.id) and q.question_label_id = ?");
		return getJdbcTemplate().query(sql.toString(), ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class),questionLabelId);
	}

	@Override
	public List<ExamQuestion> getQuestionAllList(ExamQuestionQuery questQuery) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select distinct t.*,(select count(1) from exam_question t1 where t1.parent_id = t.id) as childQuestionNum from exam_question t, exam_question_type t1, exam_sys_quest_type_quest t2 where t.id = t2.question_id and t1.id = t2.sub_type_id and t.parent_id = 0 and t2.state = 1";
		String sql_ = setParams(sql, questQuery, list);
		
		List<ExamQuestion> quesitonList = getList(PageUtil.getPageSql(sql_, questQuery.getPageNo(), questQuery
				.getPageSize()), list, ParameterizedBeanPropertyRowMapper.newInstance(ExamQuestion.class));
		
		if (quesitonList != null && quesitonList.size() > 0) {
			for(ExamQuestion q : quesitonList){
				if(q.getChildQuestionNum()>0){
					q.setChildQuestionList(getChildQuestionByParentId(q.getId()));
				}
			}
		}
		
		return quesitonList;
	}
	
	private static final String SQL_ERROR_PROP_1 = "select q.*,(select count(1) from exam_question t1 where t1.parent_id = q.id) as childQuestionNum from exam_question_study1 t, exam_question q where q.id = t.question_id and t.prop_val_id not in (select v.id from exam_prop_val v where v.type = ?)";
	private static final String SQL_ERROR_PROP_2 = "select q.*,(select count(1) from exam_question t1 where t1.parent_id = q.id) as childQuestionNum from exam_question_study2 t, exam_question q where q.id = t.question_id and t.prop_val_id not in (select v.id from exam_prop_val v where v.type = ?)";
	private static final String SQL_ERROR_PROP_3 = "select q.*,(select count(1) from exam_question t1 where t1.parent_id = q.id) as childQuestionNum from exam_question_study3 t, exam_question q where q.id = t.question_id and t.prop_val_id not in (select v.id from exam_prop_val v where v.type = ?)";
	private static final String SQL_ERROR_PROP_4 = "select q.*,(select count(1) from exam_question t1 where t1.parent_id = q.id) as childQuestionNum from exam_question_unit t, exam_question q where q.id = t.question_id and t.prop_val_id not in (select v.id from exam_prop_val v where v.type = ?)";
	private static final String SQL_ERROR_PROP_5 = "select q.*,(select count(1) from exam_question t1 where t1.parent_id = q.id) as childQuestionNum from exam_question_point t, exam_question q where q.id = t.question_id and t.prop_val_id not in (select v.id from exam_prop_val v where v.type = ?)";

	@Override
	public List<ExamQuestion> getQuestionListErrorPropId(Integer type) {
		String sql = "";

		switch (type) {
		case 1:
			sql = SQL_ERROR_PROP_1;
			break;
		case 2:
			sql = SQL_ERROR_PROP_2;
			break;
		case 3:
			sql = SQL_ERROR_PROP_3;
			break;
		case 4:
			sql = SQL_ERROR_PROP_4;
			break;
		case 5:
			sql = SQL_ERROR_PROP_5;
			break;
		}
		List<ExamQuestion> quesitonList = getJdbcTemplate().query(
				sql,
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestion.class), type);
		
		if (quesitonList != null && quesitonList.size() > 0) {
			for(ExamQuestion q : quesitonList){
				if(q.getChildQuestionNum()>0){
					q.setChildQuestionList(getChildQuestionByParentId(q.getId()));
				}
			}
		}
		
		return quesitonList;
	}

	@Override
	public List<ExamPaperFasterTactic2> getQuestSizeByFasterTactic(
			int questionLableId, String questionTypeId,
			List<ExamPaperFasterTactic2> t2List,
			Map<String, List<ExamQuestionProp>> questionPropMap,
			Integer isnotMultimedia) {

		List<Object> list = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append(SQL_GET_QUESTION_SIZE_BY_FASTER_TACTIC);

		if (!StringUtils.checkNull(questionTypeId)) {
			sql
					.append(" and exists (SELECT 1 FROM exam_question_type tx where tx.id=t.sub_type_id START WITH tx.ID in ("
							+ questionTypeId
							+ ") CONNECT BY PRIOR tx.ID = tx.PARENT_ID) ");
		}
		sql.append(" and q.question_label_id = ? ");
		list.add(questionLableId);

		if (isnotMultimedia != null) {
			sql.append(" and q.isnot_multimedia = ? ");
			list.add(isnotMultimedia);
		}

		setParam(questionPropMap, sql);

		List<ExamPaperFasterTactic2> tactic2TmpList = new ArrayList<ExamPaperFasterTactic2>();

		for (int i = 0; i < t2List.size(); i++) {

			ExamPaperFasterTactic2 tmpObja = new ExamPaperFasterTactic2();
			List<Object> query = new ArrayList<Object>();
			StringBuffer sql_ = new StringBuffer();

			ExamPaperFasterTactic2 tmpObj = t2List.get(i);

			query.addAll(list);
			sql_.append(sql);
			setProp(tmpObj, sql_, query);
			tmpObj.setAllQuestionNum(getSimpleJdbcTemplate().queryForInt(
					sql_.toString(), query.toArray()));
			BeanUtils.copyProperties(tmpObj, tmpObja);
			tactic2TmpList.add(tmpObja);

		}
		return tactic2TmpList;

	}

	private static final String  SQL_PROP1="select 1 from exam_question_study1 p where p.prop_val_id = ? and t.id = p.question_id";
	private static final String  SQL_PROP2="select 1 from exam_question_study2 p where p.prop_val_id = ? and t.id = p.question_id";
	private static final String  SQL_PROP3="select 1 from exam_question_study3 p where p.prop_val_id = ? and t.id = p.question_id";
	private static final String  SQL_PROP4="select 1 from exam_question_unit p where p.prop_val_id = ? and t.id = p.question_id";
	private static final String  SQL_PROP5="select 1 from exam_question_point p where p.prop_val_id = ? and t.id = p.question_id";
	
	public List<ExamQuestion> getQuestionListByPropVal(Long propId, Integer type) {
		String s_sql = "select t.* from exam_question t where exists (";
		String e_sql = ") and t.parent_id = 0";
		StringBuffer sql = new StringBuffer();
		sql.append(s_sql);
		switch (type) {
			case 1:
				sql.append(SQL_PROP1);
				break;
			case 2:
				sql.append(SQL_PROP2);
				break;
			case 3:
				sql.append(SQL_PROP3);
				break;
			case 4:
				sql.append(SQL_PROP4);
				break;
			case 5:
				sql.append(SQL_PROP5);
				break;
		}
		sql.append(e_sql);
		
		return getJdbcTemplate().query(
				sql.toString(),
				ParameterizedBeanPropertyRowMapper
						.newInstance(ExamQuestion.class), propId);
	}

	public boolean getQuestionSubSysTypeBySysId(Long SysId,Long questionId) {
		String sql = "select count(1) from exam_sys_quest_type_quest t where t.question_id = ? and t.sub_sys_id = ?";
		int c =getJdbcTemplate().queryForInt(sql, questionId,SysId);
		if(c>0){
			return false;
		}else{
			return true;
		}
	}

	
	private static final String  SQL_LABEL_PROP1="SELECT t.id as labelId,count(t1.id) as num FROM exam_question_label t LEFT JOIN exam_question t1 ON T.ID = t1.question_label_id LEFT JOIN EXAM_QUESTION_STUDY1 t2 ON t1.id = t2.question_id WHERE t2.prop_val_id = ? AND t.is_child = 0 GROUP BY t.id order by t.id";
	private static final String  SQL_LABEL_PROP2="SELECT t.id as labelId,count(t1.id) as num FROM exam_question_label t LEFT JOIN exam_question t1 ON T.ID = t1.question_label_id LEFT JOIN EXAM_QUESTION_STUDY2 t2 ON t1.id = t2.question_id WHERE t2.prop_val_id = ? AND t.is_child = 0 GROUP BY t.id order by t.id";
	private static final String  SQL_LABEL_PROP3="SELECT t.id as labelId,count(t1.id) as num FROM exam_question_label t LEFT JOIN exam_question t1 ON T.ID = t1.question_label_id LEFT JOIN EXAM_QUESTION_STUDY3 t2 ON t1.id = t2.question_id WHERE t2.prop_val_id = ? AND t.is_child = 0 GROUP BY t.id order by t.id";
	
	@Override
	public List<ExamQuestionLabelNum> getQuestionLabelNumByPropId(Long propId,Integer type) {
		String sql = "";
		switch (type) {
		case 1:
			sql = SQL_LABEL_PROP1;
			break;
		case 2:
			sql = SQL_LABEL_PROP2;
			break;
		case 3:
			sql = SQL_LABEL_PROP3;
			break;
		}
		
		if(sql.equals("")){
			return null;
		} else {
			return getJdbcTemplate().query(
					sql,
					ParameterizedBeanPropertyRowMapper
							.newInstance(ExamQuestionLabelNum.class), propId);
		}
	}

	@Override
	public int updatReplaceQuestionPropVal(Long targetId, Long propId,
			Integer type) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update ");
		
		switch (type) {
		case 1:
			sql.append("exam_question_study1");
			break;
		case 2:
			sql.append("exam_question_study2");
			break;
		case 3:
			sql.append("exam_question_study3");
			break;
		case 4:
			sql.append("exam_question_unit");
			break;
		case 5:
			sql.append("exam_question_point");
			break;
		}
		
		sql.append(" set prop_val_id=? where prop_val_id=?");
		return getJdbcTemplate().update(sql.toString(), targetId, propId);
	}

	@Override
	public int updatReplaceMaterialPropVal(Long targetId, Long propId) {
		String sql = "update material_prop_val set prop_id=? where prop_id=?";
		return getJdbcTemplate().update(sql.toString(), targetId, propId);
	}

	@Override
	public void updateQuestionSourceId(Long oldId, Long newId) {
		String sql = "update exam_question_source set prop_val_id=? where prop_val_id=?";
		getJdbcTemplate().update(sql, newId, oldId);
	}

	public ExamPropValDAO getLocalExamPropValDAO() {
		return localExamPropValDAO;
	}

	public void setLocalExamPropValDAO(ExamPropValDAO localExamPropValDAO) {
		this.localExamPropValDAO = localExamPropValDAO;
	}
	
	
}
